package kg.devcats.compose.jetpack_chili.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface ChiliColorPairScheme {
    val c_010101_FFFFFF: Color
    val c_FFFFFF_3D3D3D: Color
    val c_1560BD_FFFFFF: Color
    val c_041326_FFFFFF: Color
    val c_617796_FFFFFF: Color
    val c_FFFFFF_051127: Color
    val c_1EA31E_7BCD7B: Color
    val c_FF0000_D36767: Color
    val c_36424B_FFFFFF: Color
    val c_727D8D_CCFFFFFF: Color
    val c_FFFFFF_1AFFFFFF: Color
    val c_CC030C18_CC617796: Color
    val c_FEF1C9_493505: Color
    val c_FFFFFF_010101: Color
}

val LocalChiliColorPairScheme = compositionLocalOf<ChiliColorPairScheme> { ChiliLightColorPairScheme() }
val LocalChiliDarkPairThemeMode = compositionLocalOf<Boolean> { false }

data class ChiliLightColorPairScheme(
    override val c_010101_FFFFFF: Color = black_1,
    override val c_FFFFFF_3D3D3D: Color = white_1,
    override val c_1560BD_FFFFFF: Color = Color(0xFF1560BD),
    override val c_041326_FFFFFF: Color = Color(0xFF041326),
    override val c_617796_FFFFFF: Color = Color(0xFF617796),
    override val c_FFFFFF_051127: Color = Color(0xFFFFFFFF),
    override val c_1EA31E_7BCD7B: Color = Color(0xFF1EA31E),
    override val c_FF0000_D36767: Color = Color(0xFFFF0000),
    override val c_36424B_FFFFFF: Color = Color(0xFF36424B),
    override val c_727D8D_CCFFFFFF: Color = Color(0xFF727D8D),
    override val c_FFFFFF_1AFFFFFF: Color = Color(0xFFFFFFFF),
    override val c_CC030C18_CC617796: Color = Color(0xCC030C18),
    override val c_FEF1C9_493505: Color = Color(0xFFFEF1C9),
    override val c_FFFFFF_010101: Color = Color(0xFFFFFFFF),
) : ChiliColorPairScheme

data class ChiliDarkColorPairScheme(
    override val c_010101_FFFFFF: Color = white_1,
    override val c_FFFFFF_3D3D3D: Color = black_7,
    override val c_1560BD_FFFFFF: Color = Color(0xFFFFFFFF),
    override val c_041326_FFFFFF: Color = Color(0xFFFFFFFF),
    override val c_617796_FFFFFF: Color = Color(0xFFFFFFFF),
    override val c_FFFFFF_051127: Color = Color(0xFF051127),
    override val c_1EA31E_7BCD7B: Color = Color(0xFF7BCD7B),
    override val c_FF0000_D36767: Color = Color(0xFFD36767),
    override val c_36424B_FFFFFF: Color = Color(0xFFFFFFFF),
    override val c_727D8D_CCFFFFFF: Color = Color(0xCCFFFFFF),
    override val c_FFFFFF_1AFFFFFF: Color = Color(0x1AFFFFFF),
    override val c_CC030C18_CC617796: Color = Color(0xCC617796),
    override val c_FEF1C9_493505: Color = Color(0xFF493505),
    override val c_FFFFFF_010101: Color = Color(0xFF010101),
) : ChiliColorPairScheme