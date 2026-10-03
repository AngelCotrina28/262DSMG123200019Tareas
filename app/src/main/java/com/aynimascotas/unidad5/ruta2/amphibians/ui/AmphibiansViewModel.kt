package com.aynimascotas.unidad5.ruta2.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aynimascotas.unidad5.ruta2.amphibians.model.Amphibian
import com.aynimascotas.unidad5.ruta2.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}

class AmphibiansViewModel : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(AmphibiansApi.retrofitService.getAmphibians())
            } catch (e: Throwable) {
                AmphibiansUiState.Error
            }
        }
    }
}
