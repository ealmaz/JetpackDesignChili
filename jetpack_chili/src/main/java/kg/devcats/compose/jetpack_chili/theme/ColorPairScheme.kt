package kg.devcats.compose.jetpack_chili.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface ChiliColorPairScheme {
    val c_010101_FFFFFF: Color
    val c_FFFFFF_3D3D3D: Color
}

val LocalChiliColorPairScheme = compositionLocalOf<ChiliColorPairScheme> { ChiliLightColorPairScheme() }
val LocalChiliDarkPairThemeMode = compositionLocalOf<Boolean> { false }

data class ChiliLightColorPairScheme(
    override val c_010101_FFFFFF: Color = black_1,
    override val c_FFFFFF_3D3D3D: Color = white_1
) : ChiliColorPairScheme

data class ChiliDarkColorPairScheme(
    override val c_010101_FFFFFF: Color = white_1,
    override val c_FFFFFF_3D3D3D: Color = black_7
) : ChiliColorPairScheme