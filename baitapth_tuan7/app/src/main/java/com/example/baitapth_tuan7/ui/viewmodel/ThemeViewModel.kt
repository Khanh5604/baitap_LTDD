package com.example.baitapth_tuan7.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitapth_tuan7.ui.datastore.ThemeDataStore
import com.example.baitapth_tuan7.ui.model.ThemeOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val _selectedTheme = MutableStateFlow(ThemeOption.LIGHT_BLUE)
    val selectedTheme: StateFlow<ThemeOption> = _selectedTheme

    private val context = application.applicationContext

    init {
        viewModelScope.launch {
            ThemeDataStore.getTheme(context).collectLatest {
                _selectedTheme.value = it
            }
        }
    }

    fun selectTheme(theme: ThemeOption) {
        _selectedTheme.value = theme
    }

    fun applyTheme() {
        viewModelScope.launch {
            ThemeDataStore.saveTheme(context, _selectedTheme.value)
        }
    }
}
