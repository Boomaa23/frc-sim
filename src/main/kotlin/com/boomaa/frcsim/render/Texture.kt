package com.boomaa.frcsim.render

import com.boomaa.frcsim.lib.Bindable
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.io.FileInputStream
import java.nio.ByteBuffer
import javax.imageio.ImageIO


class Texture(private val path: String) : Bindable {
    val id = GL46.glGenTextures()
    lateinit var data: ByteBuffer
    var width: Int = -1
    var height: Int = -1

    override fun create() {
        val readImage = ImageIO.read(FileInputStream(path))
        val transform = AffineTransform.getScaleInstance(1.0, -1.0)
        transform.translate(0.0, -readImage.height.toDouble())
        val operation = AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
        val image = operation.filter(readImage, null)

        width = image.width
        height = image.height

        val pixels = IntArray(width * height)
        image.getRGB(0, 0, width, height, pixels, 0, width)
        val buffer: ByteBuffer = MemoryUtil.memAlloc(width * height * 4)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = pixels[y * width + x]
                buffer.put((pixel shr 16 and 0xFF).toByte())
                buffer.put((pixel shr 8 and 0xFF).toByte())
                buffer.put((pixel and 0xFF).toByte())
                buffer.put((pixel shr 24 and 0xFF).toByte())
            }
        }
        data = buffer.flip()
    }

    override fun bind() {
        GL46.glBindTexture(GL46.GL_TEXTURE_2D, id)
    }

    override fun unbind() {
        destroy()
    }

    override fun destroy() {
        GL46.glDeleteTextures(id)
    }
}