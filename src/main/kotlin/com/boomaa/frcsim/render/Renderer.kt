package com.boomaa.frcsim.render

import com.boomaa.frcsim.utils.OpenGLUtil
import org.lwjgl.opengl.GL46

class Renderer(val shader: Shader) {
    fun renderMesh(mesh: Mesh) {
        GL46.glBindVertexArray(mesh.vao)
        OpenGLUtil.vertexAttribArray(true, 0..2)
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, mesh.ibo)

        GL46.glActiveTexture(GL46.GL_TEXTURE0)
        GL46.glBindTexture(GL46.GL_TEXTURE_2D, 0)

        shader.bind()
        GL46.glDrawElements(GL46.GL_TRIANGLES, mesh.indices.size, GL46.GL_UNSIGNED_INT, 0)
        shader.unbind()

        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, 0)
        OpenGLUtil.vertexAttribArray(false, 0, 1, 2)
        GL46.glBindVertexArray(0)
    }
}