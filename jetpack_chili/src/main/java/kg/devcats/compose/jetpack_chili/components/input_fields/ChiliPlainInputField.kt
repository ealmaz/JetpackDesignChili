package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliPlainInputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    textStyle: TextStyle = Chili.typography.H16_Primary_500,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    placeholder: String? = null,
    suffix: (@Composable () -> Unit)? = null,
    focusRequester: FocusRequester = FocusRequester(),
    isInputCenteredAlign: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (TextFieldValue) -> Unit,
) {
    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        textStyle = if (isInputCenteredAlign) textStyle.copy(textAlign = TextAlign.Center) else textStyle,
        cursorBrush = SolidColor(Chili.color.inputFieldCursorColor),
        minLines = minLines,
        maxLines = maxLines,
        singleLine = isSingleLine,
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType)
    ) { inputField ->
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = if (isInputCenteredAlign) Arrangement.Center else Arrangement.Start) {
            Box(modifier = Modifier.padding(start = 14.dp, top = 14.dp, end = 8.dp, bottom = 14.dp)) {
                if (value.text.isEmpty() && !(placeholder.isNullOrBlank())) {
                    Text(modifier = Modifier.align(
                        if (isInputCenteredAlign) Alignment.Center else Alignment.CenterStart
                    ), text = placeholder, style = textStyle.copy(color = Chili.color.valueText))
                }
                if (value.text.isNotEmpty()) {
                    inputField()
                }
            }
            suffix?.invoke()
        }
    }
}


@Preview()
@Composable
fun PreviewChiliPlainInputField() {
    ChiliPlainInputField(
        modifier = Modifier.fillMaxWidth().background(Chili.color.screenBackground),
        value = TextFieldValue(),
        placeholder = "placeHoldler",
        isSingleLine = false,
        isInputCenteredAlign = true,
        focusRequester = FocusRequester(),
        suffix = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.chili_ic_documents_green),
                contentDescription = ""
            )
        }
    ) {

    }
}
