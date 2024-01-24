package org.alexcawl.iot_connector.ui.components

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val PaddingSmall: Dp = 8.dp
val PaddingMedium: Dp = 16.dp
val PaddingLarge: Dp = 32.dp

val IconLittle: Dp = 24.dp
val IconSmall: Dp = 30.dp
val IconMedium: Dp = 36.dp
val IconBig: Dp = 48.dp
val IconLarge: Dp = 96.dp

val SizeSmall: Dp = 48.dp
val SizeMedium: Dp = 96.dp
val SizeLarge: Dp = 192.dp

internal fun loremIpsum(words: Int): String = LoremIpsum(words).values.joinToString(" ")
