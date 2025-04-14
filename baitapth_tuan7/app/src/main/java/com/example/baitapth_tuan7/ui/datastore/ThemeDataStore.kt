package com.example.baitapth_tuan7.ui.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.baitapth_tuan7.ui.model.ThemeOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("theme_prefs")
private val THEME_KEY = stringPreferencesKey("theme_option")

object ThemeDataStore {

    suspend fun saveTheme(context: Context, theme: ThemeOption) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.key
        }
    }

    fun getTheme(context: Context): Flow<ThemeOption> {
        return context.dataStore.data.map { prefs ->
            ThemeOption.fromKey(prefs[THEME_KEY] ?: ThemeOption.LIGHT_BLUE.key)
        }
    }
}
