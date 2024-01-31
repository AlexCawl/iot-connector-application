package org.alexcawl.iot_connector.ui.theme.extended.palettes

import androidx.compose.ui.graphics.Color

abstract class Palette {
    abstract val colors: List<Color>

    val length: Int
        get() = colors.size

    val maxColor: Color
        get() = colors[3 * length / 4]

    val minColor: Color
        get() = colors[length / 4]
}

