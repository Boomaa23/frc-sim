package com.boomaa.frcsim

import com.boomaa.frcsim.render.MeshList
import com.boomaa.frcsim.render.Renderer
import com.boomaa.frcsim.render.Shader
import org.lwjgl.glfw.GLFW

object Display {
    var main = Window("Title", 640, 480)
    var renderer = Renderer(Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl"))

    @JvmStatic
    fun main(args: Array<String>) {
        while (!main.forceEnd && !GLFW.glfwWindowShouldClose(main.window)) {
            main.update()
            renderer.renderMesh(MeshList.rectangle)
            main.render()
        }
    }
}