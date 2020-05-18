package com.boomaa.frcsim.callback

import com.boomaa.frcsim.utils.KeyUtil
import org.lwjgl.glfw.*
import org.lwjgl.opengl.GL46

class CallbackHandler(window: Long) {
    var mouseX = -1.0
    var mouseY = -1.0

    private val keys: GLFWKeyCallback? = GLFW.glfwSetKeyCallback(window, object : GLFWKeyCallback() {
        override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
            if (action == GLFW.GLFW_PRESS) {
                try {
                    val method: String = "key" + when (key) {
                        in GLFW.GLFW_KEY_F1..GLFW.GLFW_KEY_F9 -> "F" + (key - 241).toChar()
                        in GLFW.GLFW_KEY_KP_0..GLFW.GLFW_KEY_KP_9 -> "KP" + (key - 272).toChar()
                        in GLFW.GLFW_KEY_F10..GLFW.GLFW_KEY_F25,
                            in GLFW.GLFW_KEY_KP_DECIMAL..GLFW.GLFW_KEY_MENU,
                            in GLFW.GLFW_KEY_ESCAPE..GLFW.GLFW_KEY_PAUSE -> KeyUtil.decodeSpecialChars(key)
                        else -> key.toChar()
                    }
                    Class.forName(KeyBindings.javaClass.name).getDeclaredMethod(method).invoke(KeyBindings)
                } catch (ignored: NoSuchMethodException) {
                    // Ignored to avoid throwing errors about missing methods for key bindings
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
                    Class.forName(MouseBindings.javaClass.name).getDeclaredMethod("mouse$btnName").invoke(MouseBindings)
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