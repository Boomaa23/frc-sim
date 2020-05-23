package com.boomaa.frcsim.lib

interface Bindable : Creatable {
    fun bind(): Any
    fun unbind(): Any
}