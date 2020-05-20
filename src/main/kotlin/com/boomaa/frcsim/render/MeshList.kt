package com.boomaa.frcsim.render

import com.boomaa.frcsim.ColorVector
import com.boomaa.frcsim.render.Mesh
import com.boomaa.frcsim.math.Vector3f
import com.boomaa.frcsim.math.Vertex
import java.awt.Color

object MeshList {
    var rectangle = Mesh(
        arrayOf(
            Vertex(Vector3f(-0.75f, 0.75f, 0f), ColorVector.fromAwtColor(Color.WHITE)),
            Vertex(Vector3f(0.75f, 0.75f, 0f), ColorVector.fromAwtColor(Color.WHITE)),
            Vertex(Vector3f(0.75f, -0.75f, 0f), ColorVector.fromAwtColor(Color.BLACK)),
            Vertex(Vector3f(-0.75f, -0.75f, 0f), ColorVector.fromAwtColor(Color.BLACK))
        ),
        intArrayOf(
            0, 1, 2,
            0, 3, 2
        )
    )
}