package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliUneditableInputField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    onClick: () -> Unit
) {
    val textState by remember(text) { mutableStateOf(text) }
    val hintState by remember(hint) { mutableStateOf(hint) }

    ChiliInputField(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .clickable(onClick = onClick),
        value = textState,
        placeholder = hintState,
        isInputCenteredAlign = false,
        isClearButtonEnabled = false,
        enabled = false,
        endPlaceHolder = {
            Image(
                painter = painterResource(R.drawable.chili_ic_chevron),
                contentDescription = null
            )
        },
        onValueChange = {}
    )
}

@Preview
@Composable
private fun PreviewValue() {
    ChiliUneditableInputField(
        modifier = Modifier.padding(top = 16.dp),
        hint = "Chili Uneditable Input Field",
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewHint() {
    ChiliUneditableInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = "Chili Uneditable Input Field",
        onClick = {}
    )
}