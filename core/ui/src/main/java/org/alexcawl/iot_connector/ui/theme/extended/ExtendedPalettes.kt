package org.alexcawl.iot_connector.ui.theme.extended

import androidx.compose.runtime.Immutable
import org.alexcawl.iot_connector.ui.theme.extended.palettes.GrayScalePalette
import org.alexcawl.iot_connector.ui.theme.extended.palettes.IronPalette
import org.alexcawl.iot_connector.ui.theme.extended.palettes.Palette
import org.alexcawl.iot_connector.ui.theme.extended.palettes.RainbowPalette

@Immutable
data class ExtendedPalettes(
    val gray: Palette = GrayScalePalette,
    val rainbow: Palette = RainbowPalette,
    val iron: Palette = IronPalette
)