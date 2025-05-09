package kg.devcats.compose.jetpack_chili.components.buttons


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = Chili.shapes.RoundedCornerShape,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        colors = primaryButtonColors()
    ) {
        Text(text = text.parseHtml(), style = Chili.typography.H14_Primary_500, color = Chili.color.buttonPrimaryText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliPrimaryButton() {
    ChiliPrimaryButton(text = "Primary button", modifier = Modifier.fillMaxWidth().padding(16.dp)){

    }
}
