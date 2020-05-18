package com.boomaa.frcsim.render

import com.boomaa.frcsim.utils.FileUtil
import org.lwjgl.opengl.GL46

class Shader(vertexPath: String, fragmentPath: String) {
    var vertexFile = FileUtil.loadAsString(vertexPath)
    var fragmentFile = FileUtil.loadAsString(fragmentPath)

    var programId = GL46.glCreateProgram()
    var vertexId = GL46.glCreateShader(GL46.GL_VERTEX_SHADER)
    var fragmentId = GL46.glCreateShader(GL46.GL_FRAGMENT_SHADER)

    init {
        GL46.glShaderSource(vertexId, vertexFile)
        GL46.glCompileShader(vertexId)
        GL46.glAttachShader(programId, vertexId)

        GL46.glShaderSource(fragmentId, fragmentFile)
        GL46.glCompileShader(fragmentId)
        GL46.glAttachShader(programId, fragmentId)

        GL46.glLinkProgram(programId)
        GL46.glValidateProgram(programId)

        GL46.glDeleteShader(vertexId)
        GL46.glDeleteShader(fragmentId)

        println(GL46.glGetShaderInfoLog(vertexId))
        println(GL46.glGetShaderInfoLog(fragmentId))
        println(GL46.glGetProgramInfoLog(programId))
    }

    fun bind() {
        GL46.glUseProgram(programId)
    }

    fun unbind() {
        GL46.glUseProgram(0)
    }

    fun destroy() {
        GL46.glDeleteProgram(programId)
    }
}