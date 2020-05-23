package com.boomaa.frcsim.render

import com.boomaa.frcsim.lib.Creatable
import com.boomaa.frcsim.math.Vertex
import com.boomaa.frcsim.utils.OpenGLUtil
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil

class Mesh(val vertices: Array<Vertex>, val indices: IntArray, val texture: Texture) : Creatable {
    var vao: Int = -1
    var pbo: Int = -1
    var ibo: Int = -1
    var cbo: Int = -1
    var tbo: Int = -1

    override fun create() {
        vao = GL46.glGenBuffers()
        GL46.glBindVertexArray(vao)

        pbo = OpenGLUtil.bindAndStore(OpenGLUtil.toFloatBuffer(vertices, OpenGLUtil.FloatBufferType.POSITION), 0, 3)
        cbo = OpenGLUtil.bindAndStore(OpenGLUtil.toFloatBuffer(vertices, OpenGLUtil.FloatBufferType.COLOR), 1, 3)
        tbo = OpenGLUtil.bindAndStore(OpenGLUtil.toFloatBuffer(vertices, OpenGLUtil.FloatBufferType.TEXTURE_COORD), 2, 2)

        ibo = GL46.glGenBuffers()
        val indicesBuffer = MemoryUtil.memAllocInt(indices.size).put(indices).flip()
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, ibo)
        GL46.glBufferData(GL46.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL46.GL_STATIC_DRAW)
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, 0)

        texture.create()
    }

    override fun destroy() {
        for (buffer in intArrayOf(vao, pbo, ibo, cbo, tbo)) {
            GL46.glDeleteBuffers(buffer)
        }
        texture.destroy()
    }
}