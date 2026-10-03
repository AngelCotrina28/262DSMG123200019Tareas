package com.aynimascotas.unidad5.ruta2.ui

import com.aynimascotas.unidad5.ruta2.model.MarsPhoto

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}
