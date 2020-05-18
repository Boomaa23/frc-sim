package com.boomaa.frcsim.render

import com.boomaa.frcsim.render.Mesh
import com.boomaa.frcsim.math.Vector3f
import com.boomaa.frcsim.math.Vertex

object MeshList {
    var rectangle = Mesh(
        arrayOf(
            Vertex(Vector3f(-0.75f, 0.75f, 0f)),
            Vertex(Vector3f(0.75f, 0.75f, 0f)),
            Vertex(Vector3f(0.75f, -0.75f, 0f)),
            Vertex(Vector3f(-0.75f, -0.75f, 0f))
        ),
        intArrayOf(
            0, 1, 2,
            0, 3, 2
        )
    )
}