package com.boomaa.frcsim.render

import com.boomaa.frcsim.math.Vertex
import com.boomaa.frcsim.utils.VertexUtil
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil

class Mesh(val vertices: Array<Vertex>, val indices: IntArray) {
    var vao: Int = -1
    var pbo: Int = -1
    var ibo: Int = -1

    init {
        vao = GL46.glGenBuffers()
        GL46.glBindVertexArray(vao)

        val posBuffer = VertexUtil.toFloatBuffer(vertices)
        pbo = GL46.glGenBuffers()
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, pbo)
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, posBuffer, GL46.GL_STATIC_DRAW)
        GL46.glVertexAttribPointer(0, 3, GL46.GL_FLOAT, false, 0, 0)
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0)

        val indicesBuffer = MemoryUtil.memAllocInt(indices.size)
        indicesBuffer.put(indices).flip()
        ibo = GL46.glGenBuffers()
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, ibo)
        GL46.glBufferData(GL46.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL46.GL_STATIC_DRAW)
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, 0)
    }
}