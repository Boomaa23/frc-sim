package com.boomaa.frcsim

import com.boomaa.frcsim.callback.CallbackHandler
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil
import java.awt.Color

class Window(width: Int, height: Int, title: String) {
    val window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
    val callbacks = CallbackHandler(window)
    var bgColor: ColorVector = ColorVector.fromAwtColor(Color.WHITE)
    var forceEnd = false

    companion object GLFWInit {
        init {
            if (!GLFW.glfwInit()) {
                System.err.print("ERROR: GLFW not initialized")
            }
        }
    }

    constructor(x: Int, y: Int, width: Int, height: Int, title: String) : this(width, height, title) {
        GLFW.glfwSetWindowPos(window, x, y)
    }

    init {
        if (window == 0L) {
            System.err.print("ERROR: Window not created")
        }
        GLFW.glfwMakeContextCurrent(window)
        GL.createCapabilities()
        GLFW.glfwSwapInterval(1)
        GLFW.glfwShowWindow(window)
    }

    fun update() {
        GLFW.glfwMakeContextCurrent(window)
        GL46.glClearColor(bgColor.red, bgColor.green, bgColor.blue, bgColor.alpha)
        GL46.glClear(GL46.GL_COLOR_BUFFER_BIT)
        GLFW.glfwSwapBuffers(window)
        GLFW.glfwPollEvents()
    }

    fun destroy() {
        forceEnd = true
        GLFW.glfwDestroyWindow(this.window)
    }
}