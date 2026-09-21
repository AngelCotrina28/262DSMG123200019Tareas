package com.aynimascotas.unidad4.ruta3.ui

import androidx.lifecycle.ViewModel
import com.aynimascotas.unidad4.ruta3.data.LocalSportsDataProvider
import com.aynimascotas.unidad4.ruta3.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SportsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        SportsUiState(
            sportsList = LocalSportsDataProvider.getSportsData(),
            currentSport = LocalSportsDataProvider.getSportsData().getOrElse(0) {
                LocalSportsDataProvider.defaultSport
            },
        ),
    )
    val uiState: StateFlow<SportsUiState> = _uiState.asStateFlow()

    fun updateCurrentSport(selectedSport: Sport) {
        _uiState.update { currentState ->
            currentState.copy(
                currentSport = selectedSport,
                isShowingListPage = false,
            )
        }
    }

    fun navigateToListPage() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingListPage = true,
            )
        }
    }
}
