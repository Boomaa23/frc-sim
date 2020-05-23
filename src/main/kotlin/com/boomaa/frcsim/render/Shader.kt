package com.boomaa.frcsim.render

import com.boomaa.frcsim.lib.Bindable
import com.boomaa.frcsim.utils.FileUtil
import org.lwjgl.opengl.GL46

class Shader(vertexPath: String, fragmentPath: String) : Bindable {
    var vertexFile = FileUtil.loadAsString(vertexPath)
    var fragmentFile = FileUtil.loadAsString(fragmentPath)

    var programId = GL46.glCreateProgram()
    var vertexId = GL46.glCreateShader(GL46.GL_VERTEX_SHADER)
    var fragmentId = GL46.glCreateShader(GL46.GL_FRAGMENT_SHADER)

    override fun create() {
        GL46.glShaderSource(vertexId, vertexFile)
        GL46.glCompileShader(vertexId)
        GL46.glAttachShader(programId, vertexId)

        GL46.glShaderSource(fragmentId, fragmentFile)
        GL46.glCompileShader(fragmentId)
        GL46.glAttachShader(programId, fragmentId)

        GL46.glLinkProgram(programId)
        GL46.glValidateProgram(programId)

        println(GL46.glGetShaderInfoLog(vertexId))
        println(GL46.glGetShaderInfoLog(fragmentId))
        println(GL46.glGetProgramInfoLog(programId))
    }

    override fun bind() {
        GL46.glUseProgram(programId)
    }

    override fun unbind() {
        GL46.glUseProgram(0)
    }

    override fun destroy() {
        GL46.glDetachShader(programId, vertexId)
        GL46.glDetachShader(programId, fragmentId)
        GL46.glDeleteShader(vertexId)
        GL46.glDeleteShader(fragmentId)
        GL46.glDeleteProgram(programId)
    }
}