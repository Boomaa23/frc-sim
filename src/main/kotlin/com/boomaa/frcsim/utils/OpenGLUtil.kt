package com.boomaa.frcsim.utils

import com.boomaa.frcsim.math.Vertex
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer

object OpenGLUtil {
    fun vertexAttribArray(enable: Boolean, locationRange: IntRange) {
        var hi = locationRange.toList()
    }

    fun vertexAttribArray(enable: Boolean, vararg locations: Int) {
        for (loc in locations) {
            if (enable) {
                GL46.glEnableVertexAttribArray(loc)
            } else {
                GL46.glDisableVertexAttribArray(loc)
            }
        }
    }

    fun bindAndStore(buffer: FloatBuffer, index: Int, size: Int): Int {
        val bufferId = GL46.glGenBuffers()
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, bufferId)
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, buffer, GL46.GL_STATIC_DRAW)
        GL46.glVertexAttribPointer(index, size, GL46.GL_FLOAT, false, 0, 0)
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0)
        return bufferId
    }

    fun toFloatBuffer(vertices: Array<Vertex>, type: FloatBufferType): FloatBuffer {
        val arrFloat = ArrayList<Float>(vertices.size * type.size)
        for (vertex in vertices) {
            when (type) {
                FloatBufferType.COLOR -> {
                    arrFloat.add(vertex.pos.x)
                    arrFloat.add(vertex.pos.y)
                    arrFloat.add(vertex.pos.z)
                }
                FloatBufferType.POSITION -> {
                    arrFloat.add(vertex.color.red)
                    arrFloat.add(vertex.color.green)
                    arrFloat.add(vertex.color.blue)
                }
                FloatBufferType.TEXTURE_COORD -> {
                    arrFloat.add(vertex.textureCoord.x)
                    arrFloat.add(vertex.textureCoord.y)
                }
            }


        }

        val buffer = MemoryUtil.memAllocFloat(vertices.size * type.size)
        buffer.put(arrFloat.toFloatArray()).flip()
        return buffer
    }

    enum class FloatBufferType(val size: Int) {
        COLOR(3), POSITION(3), TEXTURE_COORD(2)
    }
}