package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

enum class ButtonSize(
    val verticalPadding: Dp,
    val horizontalPadding: Dp,
    val iconSize : Dp,
    val iconPadding: Dp
) {
    REGULAR(verticalPadding = 14.dp, horizontalPadding = 16.dp, iconSize = 24.dp, iconPadding = 8.dp),
    SMALL(verticalPadding = 12.dp, horizontalPadding = 8.dp, iconSize = 20.dp, iconPadding = 6.dp),
}

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
fun secondaryButtonColors() : ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = Chili.color.buttonSecondaryBackground,
        contentColor = Chili.color.primaryText,
        disabledContainerColor = Chili.color.buttonSecondaryBackground,
        disabledContentColor = Chili.color.primaryText,
    )
}