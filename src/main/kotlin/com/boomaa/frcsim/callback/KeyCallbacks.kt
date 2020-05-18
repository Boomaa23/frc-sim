package com.boomaa.frcsim.callback

import com.boomaa.frcsim.ColorVector
import com.boomaa.frcsim.Display
import java.awt.Color

// CallbackHandler reflects into here to call each function named "key" + the key being pressed
// Default action for keypress is nothing, method returns will not do anything
object KeyCallbacks {
    fun keyW() {
        Display.main.bgColor = ColorVector.fromAwtColor(Color.BLACK)
    }

    fun keyS() {
        Display.main.bgColor = ColorVector.fromAwtColor(Color.BLUE)
    }
}