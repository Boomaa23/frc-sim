package com.boomaa.frcsim.callback

import com.boomaa.frcsim.ColorVector
import com.boomaa.frcsim.Display.main
import com.boomaa.frcsim.render.MeshList
import org.lwjgl.glfw.GLFW
import org.lwjgl.system.MemoryUtil
import java.awt.*
import kotlin.system.exitProcess

// CallbackHandler reflects into here to call each function named "key" + the key being pressed
// Default action for keypress is nothing, method returns will not do anything
object KeyBindings {
    fun keyW() {
        main.bgColor = ColorVector.fromAwtColor(Color.BLACK)
    }

    fun keyS() {
        main.bgColor = ColorVector.fromAwtColor(Color.BLUE)
    }

    fun keyF1() {
        MeshList.rectangle.destroy()
    }

    // Windowed Fullscreen
    fun keyF10() {
    }

    // Fullscreen
    fun keyF11() {
        if (!main.isFullscreen) {
            val screen: DisplayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.first().displayMode
            GLFW.glfwSetWindowMonitor(main.window, GLFW.glfwGetPrimaryMonitor(), 0, 0, screen.width, screen.height, screen.refreshRate)
            main.isFullscreen = true
        } else {
            GLFW.glfwSetWindowMonitor(main.window, MemoryUtil.NULL, main.x, main.y, main.width, main.height, 60)
            main.isFullscreen = false
        }
    }

    // End program
    fun keyEscape() {
        GLFW.glfwTerminate()
        exitProcess(1)
    }
}