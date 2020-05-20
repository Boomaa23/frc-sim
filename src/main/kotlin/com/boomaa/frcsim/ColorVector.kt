package com.boomaa.frcsim

import com.boomaa.frcsim.math.Vector3f
import java.awt.Color

class ColorVector(val red: Float, val green: Float, val blue: Float, val alpha: Float = 1F): Vector3f(red, green, blue) {
    companion object Convert {
        fun fromAwtColor(color: Color): ColorVector {
            // Must divide by 255 b/c Color values are ints 0-255, floats must be 0-1
            val red = color.red.toFloat() / 255
            val blue = color.blue.toFloat() / 255
            val green = color.green.toFloat() / 255
            val alpha = color.alpha.toFloat() / 255
            return ColorVector(red, green, blue, alpha)
        }
    }

    override fun equals(other: Any?): Boolean {
        val compareOther: ColorVector = when (other) {
            is Color -> fromAwtColor(other)
            is ColorVector -> other
            is Vector3f -> ColorVector(other.x, other.y, other.z)
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