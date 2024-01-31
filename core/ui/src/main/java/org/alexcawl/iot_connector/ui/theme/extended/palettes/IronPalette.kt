package org.alexcawl.iot_connector.ui.theme.extended.palettes

import androidx.compose.ui.graphics.Color

internal data object IronPalette : Palette() {
    override val colors: List<Color> = IRON_PALETTE_DATA.map {
        Color(it[0], it[1], it[2])
    }
}