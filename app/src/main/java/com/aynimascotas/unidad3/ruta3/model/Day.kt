package com.aynimascotas.unidad3.ruta3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    val dayNumber: Int,
    @param:StringRes val titleRes: Int,
    @param:DrawableRes val imageRes: Int,
    @param:StringRes val descriptionRes: Int,
)
