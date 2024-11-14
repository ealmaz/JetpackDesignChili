package kg.devcats.compose.jetpack_chili.components.buttons


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliSecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Chili.typography.H14_Primary_500,
    enabled: Boolean = true,
    textColor: Color = if (enabled) Chili.color.buttonComponentText else Chili.color.buttonComponentDisabledText,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .background(Chili.color.buttonComponentContainer)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp, ),
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliSecondaryButton() {
    ChiliSecondaryButton(text = "Secondary button", modifier = Modifier.fillMaxWidth()) {}
}
