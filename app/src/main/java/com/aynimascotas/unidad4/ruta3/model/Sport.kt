package com.aynimascotas.unidad4.ruta3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Sport(
    val id: Int,
    @param:StringRes val titleResourceId: Int,
    @param:StringRes val subtitleResourceId: Int,
    @param:DrawableRes val imageResourceId: Int,
    @param:DrawableRes val sportsImageBanner: Int,
    @param:StringRes val newsDetails: Int,
)
