package kg.devcats.compose.jetpack_chili.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


class ChiliShapes(themeAttributes: ChiliThemeAttributes) {

    val RoundedCornerShape =
        RoundedCornerShape(
            topStart = ChiliThemeAttributes.roundRadius,
            topEnd = ChiliThemeAttributes.roundRadius,
            bottomEnd = ChiliThemeAttributes.roundRadius,
            bottomStart = ChiliThemeAttributes.roundRadius
        )

    val RoundedTopCornerShape =
        RoundedCornerShape(
            topStart = ChiliThemeAttributes.roundRadius,
            topEnd = ChiliThemeAttributes.roundRadius,
            bottomEnd = 0.0.dp,
            bottomStart = 0.0.dp
        )

    val RoundedBottomCornerShape =
        RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = ChiliThemeAttributes.roundRadius,
            bottomStart = ChiliThemeAttributes.roundRadius
        )

    val CornerNone = RectangleShape

    val Circle = CircleShape
}

val LocalChiliShapes = compositionLocalOf { ChiliShapes(ChiliThemeAttributes) }