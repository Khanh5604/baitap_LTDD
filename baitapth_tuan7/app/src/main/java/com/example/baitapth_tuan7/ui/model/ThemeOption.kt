package com.example.baitapth_tuan7.ui.model

import androidx.compose.ui.graphics.Color

enum class ThemeOption(val color: Color, val key: String) {
    LIGHT_BLUE(Color(0xFF90CAF9), "light_blue"),
    PINK(Color(0xFFE91E63), "pink"),
    DARK(Color(0xFF212121), "dark");

    companion object {
        fun fromKey(key: String): ThemeOption {
            return values().find { it.key == key } ?: LIGHT_BLUE
        }
    }
}
