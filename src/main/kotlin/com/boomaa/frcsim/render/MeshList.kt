package com.boomaa.frcsim.render

import com.boomaa.frcsim.lib.Creatable
import com.boomaa.frcsim.math.Vector2f
import com.boomaa.frcsim.math.Vector3f
import com.boomaa.frcsim.math.Vertex
import java.awt.Color

object MeshList : Creatable {
    var rectangle = Mesh(
        arrayOf(
            Vertex(Vector3f(-0.75f, 0.75f, 0f), Vector2f(0F, 0F), ColorVector.fromAwtColor(Color.WHITE)),
            Vertex(Vector3f(0.75f, 0.75f, 0f), Vector2f(1F, 0F), ColorVector.fromAwtColor(Color.WHITE)),
            Vertex(Vector3f(0.75f, -0.75f, 0f), Vector2f(1F, 1F), ColorVector.fromAwtColor(Color.BLACK)),
            Vertex(Vector3f(-0.75f, -0.75f, 0f), Vector2f(0F, 1F), ColorVector.fromAwtColor(Color.BLACK))
        ),
        intArrayOf(
            0, 1, 2,
            0, 3, 2
        ),
        Texture("E:\\Programs\\IntelliJ IDEA Community Edition 2019.3.4\\projects\\frc-sim\\src\\main\\resources\\textures\\test.png")
    )

    override fun create() {
        val meshes = this.javaClass.declaredFields
        for (field in meshes) {
            val mesh = field.get(this)
            if (mesh is Mesh) {
                mesh.create()
            }
        }
    }

    override fun destroy() {
        val meshes = this.javaClass.declaredFields
        for (field in meshes) {
            val mesh = field.get(this)
            if (mesh is Mesh) {
                mesh.destroy()
            }
        }
    }
}