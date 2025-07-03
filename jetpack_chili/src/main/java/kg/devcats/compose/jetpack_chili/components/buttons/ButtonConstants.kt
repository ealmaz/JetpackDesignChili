package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili


interface ButtonSize {
    val verticalPadding: Dp
    val horizontalPadding: Dp
    val iconSize: Dp
    val iconPadding: Dp
}

data class RegularButtonSize(
    override val verticalPadding: Dp = 16.dp,
    override val horizontalPadding: Dp = 16.dp,
    override val iconSize: Dp = 24.dp,
    override val iconPadding: Dp = 8.dp
) : ButtonSize


data class SmallButtonSize(
    override val verticalPadding: Dp = 12.dp,
    override val horizontalPadding: Dp = 8.dp,
    override val iconSize: Dp = 20.dp,
    override val iconPadding: Dp = 6.dp
) : ButtonSize

@Composable
fun primaryButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = Chili.color.buttonPrimaryContainer,
        contentColor = Chili.color.buttonPrimaryText,
        disabledContainerColor = Chili.color.buttonPrimaryDisabledContainer,
        disabledContentColor = Chili.color.buttonPrimaryText,
    )
}

@Composable
fun secondaryButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = Chili.color.buttonSecondaryBackground,
        contentColor = Chili.color.primaryText,
        disabledContainerColor = Chili.color.buttonSecondaryBackground,
        disabledContentColor = Chili.color.primaryText,
    )
}