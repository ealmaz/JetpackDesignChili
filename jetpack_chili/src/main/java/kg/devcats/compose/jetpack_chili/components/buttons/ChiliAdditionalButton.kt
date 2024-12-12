package kg.devcats.compose.jetpack_chili.components.buttons


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.R

@Composable
fun ChiliAdditionalButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Chili.typography.H14_Primary_500,
    enabled: Boolean = true,
    endIconPainter: Painter? = null,
    endIconContentDescription: String? = null,
    endIconModifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .background(if (enabled) Chili.color.buttonAdditionalContainer else Chili.color.buttonAdditionalDisabledContainer)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp,),
                text = text,
                style = textStyle,
                color = if (enabled) Chili.color.buttonAdditionalText else Chili.color.buttonAdditionalDisabledText
            )
            endIconPainter?.let {
                Image(modifier = endIconModifier, painter = endIconPainter, contentDescription = endIconContentDescription ?: "")
            }
        }
    }

}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun PreviewChiliAdditionalButton() {
    ChiliAdditionalButton(
        text = "AdditiuonalButton",
        modifier = Modifier.fillMaxWidth(),
        endIconPainter = painterResource(id = R.drawable.chili_ic_documents_green)) {}
}
