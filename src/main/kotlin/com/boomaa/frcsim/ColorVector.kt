package com.boomaa.frcsim

import java.awt.Color

class ColorVector(val red: Float, val green: Float, val blue: Float, val alpha: Float = 1F) {
    companion object Convert {
        fun fromAwtColor(color: Color): ColorVector {
            val red = color.red.toFloat()
            val blue = color.blue.toFloat()
            val green = color.green.toFloat()
            val alpha = color.alpha.toFloat()
            return ColorVector(red, green, blue, alpha)
        }
    }

    override fun equals(other: Any?): Boolean {
        val compareOther: ColorVector = when (other) {
            is Color -> fromAwtColor(other)
            is ColorVector -> other
            else -> return false
        }
        return compareOther.red == this.red && compareOther.green == this.green && compareOther.blue == this.blue
    }

    override fun hashCode(): Int {
        var result = red.hashCode()
        result = 31 * result + green.hashCode()
        result = 31 * result + blue.hashCode()
        result = 31 * result + alpha.hashCode()
        return result
    }
}