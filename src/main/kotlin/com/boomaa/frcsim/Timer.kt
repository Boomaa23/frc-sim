package com.boomaa.frcsim

import com.boomaa.frcsim.lib.UFPS
import org.lwjgl.glfw.GLFW


class Timer(fpsTarget: Int = 60) {
    var lastLoopTime = getTime()
    var timeCount = 0F
    var accumulator = 0F
    val interval = 1F / fpsTarget
    var fps = UFPS()
    var ups = UFPS()

    val delta: Float
        get() {
            val time = getTime()
            lastLoopTime = time
            val tempD = (time - lastLoopTime).toFloat()
            timeCount += tempD
            return tempD
        }

    val alpha: Float
        get() {
            return accumulator / interval
        }

    companion object TimeUtils {
        fun getTime(tsrc: TimeSource = TimeSource.GLFW): Double {
            return when (tsrc) {
                TimeSource.GLFW -> GLFW.glfwGetTime()
                TimeSource.JAVA -> System.nanoTime() / 1000000000.0
            }
        }

        enum class TimeSource {
            GLFW, JAVA
        }
    }

    fun update() {
        if (timeCount > 1F) {
            fps.reset()
            ups.reset()
            timeCount -= 1F
        }
    }

    fun sync(fps: Int) {
        var now: Double = getTime()
        val targetTime = 1F / fps
        while (now - lastLoopTime < targetTime) {
            Thread.yield()
            Thread.sleep(1)
            now = getTime()
        }
    }
}