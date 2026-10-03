package com.aynimascotas.unidad5.ruta1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aynimascotas.unidad5.ruta1.network.MarsApi
import kotlinx.coroutines.launch

class MarsViewModel : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos()
                MarsUiState.Success("¡Éxito! Se recibieron ${listResult.size} fotos de Marte desde la API.")
            } catch (e: Throwable) {
                MarsUiState.Error
            }
        }
    }
}
