package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCustomButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    colors: ButtonColors = primaryButtonColors()
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = Chili.shapes.RoundedCornerShape,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        colors = colors
    ) {
        Text(text = text, style = Chili.typography.H14_Primary_500, color = Chili.color.buttonPrimaryText)
    }
}