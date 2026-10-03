package com.aynimascotas.unidad6.ruta1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME,
)

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
) {
    private object PreferencesKeys {
        val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")
    }

    val isLinearLayout: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_LINEAR_LAYOUT] ?: true
        }

    suspend fun saveLinearLayoutPreference(isLinearLayout: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LINEAR_LAYOUT] = isLinearLayout
        }
    }
}
