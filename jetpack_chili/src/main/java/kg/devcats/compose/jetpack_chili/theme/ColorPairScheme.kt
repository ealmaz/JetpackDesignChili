package kg.devcats.compose.jetpack_chili.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface ChiliColorPairScheme {
    val c_010101_FFFFFF: Color
    val c_FFFFFF_3D3D3D: Color
    val c_3D3D3D_DDDDDD: Color
    val c_FFF1F9_670037: Color
    val c_E8E8FF_000047: Color
    val c_FFFFFF_001429: Color
    val c_8A8A8E_DDDDDD: Color
}

val LocalChiliColorPairScheme = compositionLocalOf<ChiliColorPairScheme> { ChiliLightColorPairScheme() }
val LocalChiliDarkPairThemeMode = compositionLocalOf<Boolean> { false }

data class ChiliLightColorPairScheme(
    override val c_010101_FFFFFF: Color = black_1,
    override val c_FFFFFF_3D3D3D: Color = white_1,
    override val c_3D3D3D_DDDDDD: Color = Color(0xFF3D3D3D),
    override val c_FFF1F9_670037: Color = Color(0xFFFFF1F9),
    override val c_E8E8FF_000047: Color = Color(0xFFE8E8FF),
    override val c_FFFFFF_001429: Color = Color(0xFFFFFFFF),
    override val c_8A8A8E_DDDDDD: Color = Color(0xFF8A8A8E)
) : ChiliColorPairScheme

data class ChiliDarkColorPairScheme(
    override val c_010101_FFFFFF: Color = white_1,
    override val c_FFFFFF_3D3D3D: Color = black_7,
    override val c_3D3D3D_DDDDDD: Color = Color(0xFFDDDDDD),
    override val c_FFF1F9_670037: Color = Color(0xFF670037),
    override val c_E8E8FF_000047: Color = Color(0xFF000047),
    override val c_FFFFFF_001429: Color = Color(0xFF001429),
    override val c_8A8A8E_DDDDDD: Color = Color(0xFFDDDDDD)
) : ChiliColorPairScheme