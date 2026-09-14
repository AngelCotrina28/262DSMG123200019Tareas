package com.aynimascotas.unidad3.ruta3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @param:DrawableRes val imageResourceId: Int,
    @param:StringRes val name: Int,
    val age: Int,
    @param:StringRes val hobbies: Int,
)
