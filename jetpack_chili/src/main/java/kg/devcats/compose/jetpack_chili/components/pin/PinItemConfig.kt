package kg.devcats.compose.jetpack_chili.components.pin

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

data class PinItemConfig(
    val size: Dp,
    val borderWidth: Dp,
    val shape: Shape,
    val borderColor: Color,
    val selectedColor: Color,
    val nonSelectedColor: Color,
    val successColor: Color,
    val errorColor: Color
) {
    companion object {

        @Composable
        fun create(
            size: Dp = 14.dp,
            borderWidth: Dp = 2.dp,
            shape: Shape = CircleShape,
            borderColor: Color = Chili.color.lockBorderColor,
            selectedColor: Color = Chili.color.lockSelectedBg,
            nonSelectedColor: Color = Chili.color.lockNonSelectedBg,
            successColor: Color = Chili.color.lockSuccessBg,
            errorColor: Color = Chili.color.lockErrorBg
        ) = PinItemConfig(
            size = size,
            borderWidth = borderWidth,
            shape = shape,
            borderColor = borderColor,
            selectedColor = selectedColor,
            nonSelectedColor = nonSelectedColor,
            successColor = successColor,
            errorColor = errorColor
        )
    }
}