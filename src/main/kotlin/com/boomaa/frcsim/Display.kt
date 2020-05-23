package com.boomaa.frcsim

import com.boomaa.frcsim.render.MeshList
import com.boomaa.frcsim.render.Renderer
import com.boomaa.frcsim.render.Shader
import org.lwjgl.glfw.GLFW

object Display {
    var main = Window("FRC Simulator", 640, 480)
    var renderer = Renderer(Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl"))
    var timer = Timer()

    @JvmStatic
    fun main(args: Array<String>) {
        MeshList.create()
        renderer.shader.create()
        try {
            while (!main.forceEnd && !GLFW.glfwWindowShouldClose(main.window)) {
                while (timer.accumulator >= timer.interval) {
                    main.update()
                    timer.ups.update()
                    timer.accumulator -= timer.interval
                }

                main.render(renderer)
                timer.fps.update()

                timer.update()
            }
        } finally {
            MeshList.destroy()
        }
    }
}