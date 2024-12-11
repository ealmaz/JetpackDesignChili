package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliUneditableInputField(
    modifier: Modifier,
    text: String = "",
    hint: String = "",
    textStyle: TextStyle = Chili.typography.H16_Primary_500,
    textAlign: TextAlign = TextAlign.Unspecified,
    onClick: () -> Unit
) {
    val textState by remember(text) { mutableStateOf(text) }
    val hintState by remember(hint) { mutableStateOf(hint) }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = Chili.shapes.RoundedCornerShape,
        color = Chili.color.inputFieldBackground,
        onClick = onClick
    ) {
        Row(
            Modifier.padding(start = 14.dp, top = 14.dp, end = 8.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = textState.ifEmpty { hintState },
                style = textStyle,
                color = if (textState.isNotEmpty()) Chili.color.markedText else Chili.color.valueText,
                textAlign = textAlign
            )

            Image(
                painter = painterResource(R.drawable.chili_ic_chevron),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun PreviewValue() {
    ChiliUneditableInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = "Chili Uneditable Input Field",
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewHint() {
    ChiliUneditableInputField(
        modifier = Modifier.padding(top = 16.dp),
        hint = "Chili Uneditable Input Field",
        onClick = {}
    )
}