package com.boomaa.frcsim.render

import org.lwjgl.opengl.GL46

class Renderer(val shader: Shader) {
    fun renderMesh(mesh: Mesh) {
        GL46.glBindVertexArray(mesh.vao)
        GL46.glEnableVertexAttribArray(0)
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, mesh.ibo)

        shader.bind()
        GL46.glDrawElements(GL46.GL_TRIANGLES, mesh.indices.size, GL46.GL_UNSIGNED_INT, 0)
        shader.unbind()

        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, 0)
        GL46.glDisableVertexAttribArray(0)
        GL46.glBindVertexArray(0)
    }
}