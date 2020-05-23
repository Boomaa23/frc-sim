package com.boomaa.frcsim.lib

class UFPS(var value: Double = 0.0, private var count: Double = 0.0) {
    fun reset() {
        value = count
        count = 0.0
    }

    fun update() {
        count++
    }
}