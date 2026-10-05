package com.aynimascotas.unidad6.ruta1.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aynimascotas.unidad6.ruta1.DessertReleaseApplication
import com.aynimascotas.unidad6.ruta1.data.UserPreferencesRepository
import com.aynimascotas.unidad6.ruta1.data.dataStore
import com.aynimascotas.unidad6.ruta2.InventoryApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DessertReleaseViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {
    val uiState: StateFlow<DessertReleaseUiState> = userPreferencesRepository.isLinearLayout
        .map { isLinearLayout ->
            DessertReleaseUiState(isLinearLayout)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DessertReleaseUiState(),
        )

    fun selectLayout(isLinearLayout: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.saveLinearLayoutPreference(isLinearLayout)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val userPreferencesRepository = when (application) {
                    is InventoryApplication -> application.userPreferencesRepository
                    is DessertReleaseApplication -> application.userPreferencesRepository
                    else -> UserPreferencesRepository(application.dataStore)
                }
                DessertReleaseViewModel(userPreferencesRepository)
            }
        }
    }
}
