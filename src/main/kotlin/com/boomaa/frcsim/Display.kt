package com.boomaa.frcsim

import org.lwjgl.glfw.GLFW

object Display {
    var main = Window(640, 480, "Title")

    @JvmStatic
    fun main(args: Array<String>) {
        while (!main.forceEnd && !GLFW.glfwWindowShouldClose(main.window)) {
            main.update()
        }
    }
}