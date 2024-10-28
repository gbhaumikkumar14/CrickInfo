package com.example.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimen(
    val smallest:Dp = 4.dp,
    val small:Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val largest: Dp = 64.dp,

    val custom2: Dp = 2.dp,
    val custom24: Dp = 24.dp,
    val custom100: Dp = 100.dp

)

val LocalDimens = compositionLocalOf{ Dimen() }