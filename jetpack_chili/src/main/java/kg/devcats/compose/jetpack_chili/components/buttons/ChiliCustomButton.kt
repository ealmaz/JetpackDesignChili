package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCustomButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    colors: ButtonColors = primaryButtonColors(),
    textColor: Color = Chili.color.buttonPrimaryText,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = Chili.shapes.RoundedCornerShape,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        colors = colors
    ) {
        Text(text = text, style = Chili.typography.H14_Primary_500, color = textColor)
    }
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