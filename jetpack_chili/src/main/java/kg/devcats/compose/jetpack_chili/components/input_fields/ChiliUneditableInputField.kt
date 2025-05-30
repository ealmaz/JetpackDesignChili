package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_1

@Composable
fun ChiliUneditableInputField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    error: String? = null,
    message: String? = null,
    messagePaddingValues: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    textStyle: TextStyle = Chili.typography.H20_Primary_500,
    textAlign: TextAlign = TextAlign.Unspecified,
    textPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
    maxLines: Int = 1,
    backgroundColor: Color = Chili.color.inputFieldBackground,
    endIcon: Painter? = painterResource(R.drawable.chili4_ic_chevron),
    onClick: (() -> Unit)? = null
) {
    val textState by remember(text) { mutableStateOf(text) }
    val hintState by remember(hint) { mutableStateOf(hint) }

    val clickableModifier = if (onClick != null) {
        Modifier.clickable { onClick() }
    } else {
        Modifier
    }

    Column(modifier = modifier) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .then(clickableModifier),
            shape = Chili.shapes.RoundedCornerShape,
            color = decideBackgroundColor(error, backgroundColor)
        ) {
            Row(
                Modifier.padding(textPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = textState.ifEmpty { hintState },
                    style = textStyle,
                    color = if (textState.isNotEmpty()) Chili.color.markedText else Chili.color.valueText,
                    textAlign = textAlign,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis
                )

                if (endIcon != null) {
                    Icon(
                        painter = endIcon,
                        contentDescription = null,
                        tint = gray_1.copy(alpha = 0.5f)
                    )
                }
            }
        }

        if (message != null || error != null) {
            Text(
                modifier = Modifier.padding(messagePaddingValues),
                style = if (error == null) Chili.typography.H14_Secondary else Chili.typography.H14_Error,
                text = error.takeIf { !it.isNullOrBlank() } ?: message ?: ""
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
        endIcon = null,
        onClick = null
    )
}

@Preview
@Composable
private fun PreviewWithClick() {
    ChiliUneditableInputField(
        modifier = Modifier.padding(top = 16.dp),
        hint = "Chili Uneditable Input Field",
        onClick = {}
    )
}