package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliSmsOtpInputField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = Chili.typography.H16_Primary_500,
    inputBgColor: Color = Chili.color.inputFieldBackground,
    error: String? = null,
    isTimerFinished: Boolean = false,
    isClearButtonEnabled: Boolean = true,
    placeholder: String? = null,
    focusRequester: FocusRequester = FocusRequester(),
    message: String? = null,
    actionText: String? = null,
    actionTextStyle: TextStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
    isInputFieldEmpty: Boolean? = null,
    isInputCenteredAlign: Boolean = true,
    inputFieldPaddingValues: PaddingValues = PaddingValues(
        start = 14.dp,
        top = 14.dp,
        end = 8.dp,
        bottom = 14.dp
    ),
    startFrame: @Composable (() -> Unit)? = null,
    clearIcon: Painter = painterResource(id = R.drawable.chili_ic_circle_clear),
    keyboardType: KeyboardType = KeyboardType.Number,
    onActionClick: (() -> Unit) = {},
    onValueChange: ((String) -> Unit),
) {
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }
    val textFieldValue = textFieldValueState.copy(text = value)
    InputFieldContainer(
        modifier = modifier,
        value = textFieldValue,
        error = error,
        isTimerFinished = isTimerFinished,
        isInputFieldEmpty = isInputFieldEmpty,
        inputBgColor = inputBgColor,
        isClearButtonEnabled = isClearButtonEnabled,
        message = message,
        actionText = actionText,
        actionTextStyle = actionTextStyle,
        isInputCenteredAlign = isInputCenteredAlign,
        clearIcon = clearIcon,
        startFrame = startFrame,
        onActionClick = onActionClick,
        onValueChange = { newTextFieldValueState ->
            textFieldValueState = newTextFieldValueState
            onValueChange(newTextFieldValueState.text)
        }
    ) {
        ChiliPlainInputField(
            modifier = Modifier.weight(1f),
            focusRequester = focusRequester,
            value = textFieldValue,
            onValueChange = { newTextFieldValueState ->
                textFieldValueState = newTextFieldValueState
                onValueChange(newTextFieldValueState.text)
            },
            placeholder = placeholder,
            textStyle = textStyle,
            isInputCenteredAlign = isInputCenteredAlign,
            inputFieldPaddingValues = inputFieldPaddingValues,
            keyboardType = keyboardType
        )
    }
}

@Composable
private fun InputFieldContainer(
    modifier: Modifier,
    value: TextFieldValue,
    error: String? = null,
    isTimerFinished: Boolean = false,
    isClearButtonEnabled: Boolean = true,
    inputBgColor: Color = Chili.color.inputFieldBackground,
    message: String? = null,
    actionText: String? = null,
    actionTextStyle: TextStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
    isInputFieldEmpty: Boolean? = null,
    isInputCenteredAlign: Boolean = true,
    clearIcon: Painter = painterResource(id = R.drawable.chili_ic_circle_clear),
    startFrame: @Composable (() -> Unit)? = null,
    onActionClick: (() -> Unit) = {},
    onValueChange: ((TextFieldValue) -> Unit),
    onClearInput: (() -> Unit) = { onValueChange(TextFieldValue()) },
    inputField: @Composable RowScope.() -> Unit
) {

    val isValueEmpty by remember(isInputFieldEmpty, value.text) {
        mutableStateOf(isInputFieldEmpty ?: value.text.isEmpty())
    }

    Column(modifier = modifier) {
        Surface(
            color = decideBackgroundColor(error, inputBgColor),
            shape = Chili.shapes.RoundedCornerShape
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                when {
                    startFrame != null -> startFrame()
                    isClearButtonEnabled && !isValueEmpty && isInputCenteredAlign ->
                        Spacer(modifier = Modifier.width(44.dp))
                }

                inputField()

                if (isClearButtonEnabled && !isValueEmpty) {
                    InputFieldClearIcon(clearIcon = clearIcon) { onClearInput() }
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (message != null || error != null) {
                androidx.compose.material3.Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    style = if (error == null) Chili.typography.H14_Secondary else Chili.typography.H14_Error,
                    text = error.takeIf { !it.isNullOrBlank() } ?: message ?: ""
                )
            }
            actionText?.let {
                Box(modifier = Modifier.weight(1f)) {
                    if (isTimerFinished) {
                        ChiliComponentButton(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .align(Alignment.CenterEnd),
                            text = it,
                            textStyle = actionTextStyle,
                            enabled = true,
                            onClick = onActionClick
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                                .align(Alignment.CenterEnd),
                            text = it,
                            style = actionTextStyle
                        )
                    }
                }
            }
        }
    }
}