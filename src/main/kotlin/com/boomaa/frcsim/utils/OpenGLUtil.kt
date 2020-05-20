package com.boomaa.frcsim.utils

import com.boomaa.frcsim.math.Vertex
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer

object OpenGLUtil {
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
        val arrFloat = ArrayList<Float>(vertices.size * 3)
        for (vertex in vertices) {
            if (type == FloatBufferType.POSITION) {
                arrFloat.add(vertex.pos.x)
                arrFloat.add(vertex.pos.y)
                arrFloat.add(vertex.pos.z)
            } else {
                arrFloat.add(vertex.color.red)
                arrFloat.add(vertex.color.green)
                arrFloat.add(vertex.color.blue)
            }
        }

        val buffer = MemoryUtil.memAllocFloat(vertices.size * 3)
        buffer.put(arrFloat.toFloatArray()).flip()
        return buffer
    }

    enum class FloatBufferType {
        COLOR, POSITION
    }
}