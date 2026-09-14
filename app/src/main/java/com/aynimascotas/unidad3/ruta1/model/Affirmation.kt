package com.aynimascotas.unidad3.ruta1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @param:StringRes val stringResourceId: Int,
    @param:DrawableRes val imageResourceId: Int,
)
