package com.boomaa.frcsim.utils

import com.boomaa.frcsim.math.Vertex
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer

object VertexUtil {
    fun toFloatBuffer(vertices: Array<Vertex>): FloatBuffer {
        val arrFloat = ArrayList<Float>(vertices.size * 3)
        for (vertex in vertices) {
            arrFloat.add(vertex.pos.x)
            arrFloat.add(vertex.pos.y)
            arrFloat.add(vertex.pos.z)
        }

        val buffer = MemoryUtil.memAllocFloat(vertices.size * 3)
        buffer.put(arrFloat.toFloatArray()).flip()
        return buffer
    }
}