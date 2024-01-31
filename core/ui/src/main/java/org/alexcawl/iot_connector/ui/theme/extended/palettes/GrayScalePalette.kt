package org.alexcawl.iot_connector.ui.theme.extended.palettes

import androidx.compose.ui.graphics.Color

internal data object GrayScalePalette : Palette() {
    override val colors: List<Color> = run {
        val result: MutableList<Color> = mutableListOf()
        for (k in 0..255) {
            val r = GRAY_PALETTE_DATA[3 * k]
            val g = GRAY_PALETTE_DATA[3 * k + 1]
            val b = GRAY_PALETTE_DATA[3 * k + 2]
            val color = Color(r, g, b)
            result.add(color)
        }
        result.toList()
    }
}