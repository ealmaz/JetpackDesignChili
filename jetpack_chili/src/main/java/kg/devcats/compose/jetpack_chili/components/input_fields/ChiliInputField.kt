package kg.devcats.compose.jetpack_chili.components.input_fields

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.AmountInputVisualTransformator
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.InputFieldDefaults
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.handleZero
import kg.devcats.compose.jetpack_chili.components.input_fields.utils.amountValueChange
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.ChiliTheme

@Composable
fun ChiliInputField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = Chili.typography.H16_Primary_500,
    inputBgColor: Color = Chili.color.inputFieldBackground,
    error: String? = null,
    isClearButtonEnabled: Boolean = true,
    placeholder: String? = null,
    focusRequester: FocusRequester = FocusRequester(),
    message: String? = null,
    actionText: String? = null,
    isInputCenteredAlign: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onActionClick: (() -> Unit) = {},
    onValueChange: ((String) -> Unit),
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length))) }
    val textFieldValue = textFieldValueState.copy(text = value)

    ChiliInputField(
        modifier = modifier,
        value = textFieldValue,
        textStyle = textStyle,
        error = error,
        inputBgColor = inputBgColor,
        isClearButtonEnabled = isClearButtonEnabled,
        placeholder = placeholder,
        focusRequester = focusRequester,
        message = message,
        actionText = actionText,
        isInputCenteredAlign = isInputCenteredAlign,
        keyboardType = keyboardType,
        onActionClick = onActionClick,
        onValueChange = { newTextFieldValueState ->
            textFieldValueState = newTextFieldValueState
            onValueChange(newTextFieldValueState.text)
        })
}

@Composable
fun ChiliInputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    textStyle: TextStyle = Chili.typography.H16_Primary_500,
    inputBgColor: Color = Chili.color.inputFieldBackground,
    error: String? = null,
    isClearButtonEnabled: Boolean = true,
    placeholder: String? = null,
    focusRequester: FocusRequester = FocusRequester(),
    message: String? = null,
    actionText: String? = null,
    isInputCenteredAlign: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onActionClick: (() -> Unit) = {},
    onValueChange: ((TextFieldValue) -> Unit),
) {
    InputFieldContainer(
        modifier = modifier,
        value = value,
        error = error,
        inputBgColor = inputBgColor,
        isClearButtonEnabled = isClearButtonEnabled,
        message = message,
        actionText = actionText,
        isInputCenteredAlign = isInputCenteredAlign,
        onActionClick = onActionClick,
        onValueChange = onValueChange,
    ) {
        ChiliPlainInputField(
            modifier = Modifier.weight(1f),
            focusRequester = focusRequester,
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            textStyle = textStyle,
            isInputCenteredAlign = isInputCenteredAlign,
            keyboardType = keyboardType
        )
    }
}

@Composable
fun ChiliAmountInputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    textStyle: TextStyle = Chili.typography.H16_Primary_500,
    inputBgColor: Color = Chili.color.inputFieldBackground,
    error: String? = null,
    isClearButtonEnabled: Boolean = true,
    placeholder: String? = null,
    focusRequester: FocusRequester = FocusRequester(),
    message: String? = null,
    actionText: String? = null,
    isInputCenteredAlign: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLenBeforeComma: Int = Int.MAX_VALUE,
    addDecimal: Boolean = true,
    suffix: AnnotatedString? = null,
    onActionClick: (() -> Unit) = {},
    onValueChange: ((TextFieldValue) -> Unit),
) {
    InputFieldContainer(
        modifier = modifier,
        value = value,
        error = error,
        inputBgColor = inputBgColor,
        isClearButtonEnabled = isClearButtonEnabled,
        message = message,
        actionText = actionText,
        isInputCenteredAlign = isInputCenteredAlign,
        onActionClick = onActionClick,
        onValueChange = onValueChange,
        onClearInput = {
            onValueChange(TextFieldValue("0", selection = TextRange(1)))
        }
    ) {
        ChiliPlainInputField(
            modifier = Modifier.weight(1f),
            focusRequester = focusRequester,
            value = value,
            onValueChange = { newTextFieldValueState ->
                val filtered = newTextFieldValueState.handleZero(value)
                val finalText = amountValueChange(filtered.text, addDecimal)
                val lenBeforeComma = finalText.substringBefore(InputFieldDefaults.DECIMAL_COMMA).length

                if (lenBeforeComma <= maxLenBeforeComma) {
                    val finalValue = TextFieldValue(finalText, filtered.selection)
                    onValueChange(finalValue)
                }
            },
            placeholder = placeholder,
            textStyle = textStyle,
            isInputCenteredAlign = isInputCenteredAlign,
            keyboardType = keyboardType,
            visualTransformation = AmountInputVisualTransformator(
                addDecimals = addDecimal,
                suffix = suffix
            )
        )
    }
}

@Composable
private fun InputFieldContainer(
    modifier: Modifier,
    value: TextFieldValue,
    error: String? = null,
    isClearButtonEnabled: Boolean = true,
    inputBgColor: Color = Chili.color.inputFieldBackground,
    message: String? = null,
    actionText: String? = null,
    isInputCenteredAlign: Boolean = true,
    onActionClick: (() -> Unit) = {},
    onValueChange: ((TextFieldValue) -> Unit),
    onClearInput: (() -> Unit) = { onValueChange(TextFieldValue()) },
    inputField: @Composable RowScope.() -> Unit
) {
    Column(modifier = modifier) {
        Surface(
            color = decideBackgroundColor(error, inputBgColor),
            shape = Chili.shapes.RoundedCornerShape
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isClearButtonEnabled && value.text.isNotEmpty() && isInputCenteredAlign) {
                    Spacer(modifier = Modifier.width(44.dp))
                }
                inputField()
                if (isClearButtonEnabled && value.text.isNotEmpty()) {
                    InputFieldClearIcon { onClearInput() }
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (message != null || error != null) {
                Text(
                    modifier = Modifier
                        .weight(2f)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    style = if (error == null) Chili.typography.H14_Secondary else Chili.typography.H14_Error,
                    text = error.takeIf { !it.isNullOrBlank() } ?: message ?: ""
                )
            }
            actionText?.let {
                Box(modifier = Modifier.weight(1f)) {
                    ChiliComponentButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = it,
                        textStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
                        enabled = true,
                        onClick = onActionClick
                    )
                }
            }
        }
    }
}

@Composable
private fun InputFieldClearIcon(clearValue: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.chili_ic_circle_clear),
        contentDescription = "clear",
        modifier = Modifier
            .padding(end = 14.dp, start = 8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
                onClick = clearValue
            )
    )
}

@Composable
private fun decideBackgroundColor(error: String? = null, inputBgColor: Color): Color {
    return if (error == null) inputBgColor
    else Chili.color.inputFieldErrorBackground
}

@Preview(showBackground = false)
@Composable
fun PreviewInputField() {
    ChiliTheme {
        ChiliInputField(
            modifier = Modifier.fillMaxWidth(),
            value = TextFieldValue("Hello"),
            message = "Message",
            actionText = "Action") {

        }
    }
}