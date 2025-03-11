package kg.devcats.compose.jetpack_chili.components.buttons


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliComponentButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Chili.typography.H16,
    enabled: Boolean = true,
    enabledTextColor: Color = Chili.color.buttonComponentText,
    disabledTextColor: Color = Chili.color.buttonComponentDisabledText,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .background(Chili.color.buttonComponentContainer)
            .clickable(enabled = enabled, onClick = onClick)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = textStyle,
            color = if (enabled) enabledTextColor else disabledTextColor
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliComponentButton() {
    Box(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
        ChiliComponentButton(text = "Action") {}
    }
}
