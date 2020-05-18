package com.boomaa.frcsim.callback

import org.lwjgl.glfw.*
import org.lwjgl.opengl.GL46

class CallbackHandler(window: Long) {
    var mouseX = -1.0
    var mouseY = -1.0

    private val keys: GLFWKeyCallback? = GLFW.glfwSetKeyCallback(window, object : GLFWKeyCallback() {
        override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
            if (action != GLFW.GLFW_KEY_DOWN) {
                try {
                    Class.forName(KeyCallbacks.javaClass.name).getDeclaredMethod("key" + key.toChar()).invoke(KeyCallbacks)
                } catch (ignored: NoSuchMethodException) {
                }
            }
        }
    })

    private val mouseButton: GLFWMouseButtonCallback? = GLFW.glfwSetMouseButtonCallback(window, object : GLFWMouseButtonCallback() {
        override fun invoke(window: Long, button: Int, action: Int, mods: Int) {
            if (action != GLFW.GLFW_KEY_DOWN) {
                try {
                    val btnName = when (button) {
                        GLFW.GLFW_MOUSE_BUTTON_LEFT -> "Left"
                        GLFW.GLFW_MOUSE_BUTTON_RIGHT -> "Right"
                        GLFW.GLFW_MOUSE_BUTTON_MIDDLE -> "Middle"
                        else -> return
                    }
                    Class.forName(MouseCallbacks.javaClass.name).getDeclaredMethod("mouse$btnName").invoke(MouseCallbacks)
                } catch (ignored: NoSuchMethodException) {
                }
            }
        }
    })

    private val cursorPos: GLFWCursorPosCallback? = GLFW.glfwSetCursorPosCallback(window, object : GLFWCursorPosCallback() {
        override fun invoke(window: Long, xpos: Double, ypos: Double) {
            mouseX = xpos
            mouseY = ypos
        }
    })

    private val windowResize: GLFWWindowSizeCallback? = GLFW.glfwSetWindowSizeCallback(window, object : GLFWWindowSizeCallback() {
        override fun invoke(window: Long, width: Int, height: Int) {
            GLFW.glfwSetWindowSize(window, width, height)
            GL46.glViewport(0, 0, width, height)
        }
    })
}