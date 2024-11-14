package kg.devcats.compose.jetpack_chili.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

object ChiliThemeAttributes {
    val roundRadius = 12.dp
    val shadowElevation = 4.dp
}

val LocalChiliThemeAttributes = compositionLocalOf { ChiliThemeAttributes }