package org.alexcawl.iot_connector.ui.theme.extended.palettes

import androidx.compose.ui.graphics.Color

internal data object RainbowPalette : Palette() {
    override val colors: List<Color> = run {
        val result: MutableList<Color> = mutableListOf()
        for (k in 0..255) {
            val r = RAINBOW_PALETTE_DATA[3 * k]
            val g = RAINBOW_PALETTE_DATA[3 * k + 1]
            val b = RAINBOW_PALETTE_DATA[3 * k + 2]
            val color = Color(r, g, b)
            result.add(color)
        }
        result.toList()
    }
}