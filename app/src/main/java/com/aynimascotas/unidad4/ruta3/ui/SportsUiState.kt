package com.aynimascotas.unidad4.ruta3.ui

import com.aynimascotas.unidad4.ruta3.data.LocalSportsDataProvider
import com.aynimascotas.unidad4.ruta3.model.Sport

data class SportsUiState(
    val sportsList: List<Sport> = emptyList(),
    val currentSport: Sport = LocalSportsDataProvider.defaultSport,
    val isShowingListPage: Boolean = true,
)
