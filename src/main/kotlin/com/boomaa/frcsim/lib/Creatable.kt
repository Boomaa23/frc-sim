package com.boomaa.frcsim.lib

interface Creatable {
    fun create(): Any
    fun destroy(): Any
}