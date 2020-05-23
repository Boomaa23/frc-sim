package com.boomaa.frcsim

import com.boomaa.frcsim.callback.CallbackHandler
import com.boomaa.frcsim.render.ColorVector
import com.boomaa.frcsim.render.MeshList
import com.boomaa.frcsim.render.Renderer
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil
import java.awt.Color

class Window(title: String, val width: Int, val height: Int, var x: Int = -1, var y: Int = -1, var isFullscreen: Boolean = false) {
    val window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
    val callbacks = CallbackHandler(window)
    var bgColor: ColorVector = ColorVector.fromAwtColor(Color.RED)
    var forceEnd = false

    companion object GLFWInit {
        init {
            if (!GLFW.glfwInit()) {
                System.err.print("ERROR: GLFW not initialized")
            }
        }
    }

    init {
        if (window == 0L) {
            System.err.print("ERROR: Window not created")
        }
        GLFW.glfwMakeContextCurrent(window)
        if (x != -1 && y != -1) {
            GLFW.glfwSetWindowPos(window, x, y)
        } else {
            val xTemp = IntArray(1)
            val yTemp = IntArray(1)
            GLFW.glfwGetWindowPos(window, xTemp, yTemp)
            x = xTemp.first()
            y = yTemp.first()
        }
        GL.createCapabilities()
        GL46.glEnable(GL46.GL_DEPTH_TEST)
        GLFW.glfwShowWindow(window)
        GLFW.glfwSwapInterval(1)
    }

    fun update() {
        GLFW.glfwMakeContextCurrent(window)
        GL46.glClearColor(bgColor.red, bgColor.green, bgColor.blue, bgColor.alpha)
        GL46.glClear(GL46.GL_COLOR_BUFFER_BIT or GL46.GL_DEPTH_BUFFER_BIT)
        GLFW.glfwPollEvents()
    }

    fun render(renderer: Renderer) {
        try {
            renderer.renderMesh(MeshList.rectangle)
        } finally {
            GLFW.glfwSwapBuffers(window)
        }
    }

    fun destroy() {
        forceEnd = true
        GLFW.glfwDestroyWindow(this.window)
    }
}