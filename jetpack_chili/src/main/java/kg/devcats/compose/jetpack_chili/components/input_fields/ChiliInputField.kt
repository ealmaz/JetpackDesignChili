package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
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
    messageWeight: Float = 2f,
    messagePaddingValues: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    actionText: String? = null,
    isActionEnabled: Boolean = true,
    actionTextStyle: TextStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
    actionEnabledTextColor: Color = Chili.color.buttonComponentText,
    actionDisabledTextColor: Color = Chili.color.buttonComponentDisabledText,
    isInputFieldEmpty: Boolean? = null,
    isInputCenteredAlign: Boolean = true,
    inputFieldPaddingValues: PaddingValues = PaddingValues(start = 14.dp, top = 14.dp, end = 8.dp, bottom = 14.dp),
    startFrame: @Composable (() -> Unit)? = null,
    clearIcon: Painter = painterResource(id = R.drawable.chili_ic_circle_clear),
    rightActionIcon: Painter? = null,
    onRightActionIconClick: (() -> Unit) = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    onActionClick: (() -> Unit) = {},
    onDoubleClick: ((Offset) -> Unit)? = null,
    onLongClick: ((Offset) -> Unit)? = null,
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
        isInputFieldEmpty = isInputFieldEmpty,
        message = message,
        messageWeight = messageWeight,
        messagePaddingValues = messagePaddingValues,
        actionText = actionText,
        isActionEnabled = isActionEnabled,
        actionTextStyle = actionTextStyle,
        actionEnabledTextColor = actionEnabledTextColor,
        actionDisabledTextColor = actionDisabledTextColor,
        isInputCenteredAlign = isInputCenteredAlign,
        inputFieldPaddingValues = inputFieldPaddingValues,
        startFrame = startFrame,
        clearIcon = clearIcon,
        rightActionIcon = rightActionIcon,
        onRightActionIconClick = onRightActionIconClick,
        keyboardType = keyboardType,
        onActionClick = onActionClick,
        onDoubleClick = onDoubleClick,
        onLongClick = onLongClick,
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
    messageWeight: Float = 2f,
    messagePaddingValues: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    isInputFieldEmpty: Boolean? = null,
    actionText: String? = null,
    isActionEnabled: Boolean = true,
    actionTextStyle: TextStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
    actionEnabledTextColor: Color = Chili.color.buttonComponentText,
    actionDisabledTextColor: Color = Chili.color.buttonComponentDisabledText,
    isInputCenteredAlign: Boolean = true,
    inputFieldPaddingValues: PaddingValues = PaddingValues(start = 14.dp, top = 14.dp, end = 8.dp, bottom = 14.dp),
    startFrame: @Composable (() -> Unit)? = null,
    clearIcon: Painter = painterResource(id = R.drawable.chili_ic_circle_clear),
    rightActionIcon: Painter? = null,
    onRightActionIconClick: (() -> Unit) = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    onActionClick: (() -> Unit) = {},
    onDoubleClick: ((Offset) -> Unit)? = null,
    onLongClick: ((Offset) -> Unit)? = null,
    onValueChange: ((TextFieldValue) -> Unit),
) {
    InputFieldContainer(
        modifier = modifier,
        value = value,
        error = error,
        isInputFieldEmpty = isInputFieldEmpty,
        inputBgColor = inputBgColor,
        isClearButtonEnabled = isClearButtonEnabled,
        message = message,
        messageWeight = messageWeight,
        messagePaddingValues = messagePaddingValues,
        actionText = actionText,
        isActionEnabled = isActionEnabled,
        actionTextStyle = actionTextStyle,
        actionEnabledTextColor = actionEnabledTextColor,
        actionDisabledTextColor = actionDisabledTextColor,
        isInputCenteredAlign = isInputCenteredAlign,
        rightActionIcon = rightActionIcon,
        onRightActionIconClick = onRightActionIconClick,
        clearIcon = clearIcon,
        startFrame = startFrame,
        onActionClick = onActionClick,
        onDoubleClick = onDoubleClick,
        onLongClick = onLongClick,
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
            inputFieldPaddingValues = inputFieldPaddingValues,
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
    messagePaddingValues: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    actionText: String? = null,
    isActionEnabled: Boolean = true,
    actionTextStyle: TextStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
    isInputFieldEmpty: Boolean? = null,
    isInputCenteredAlign: Boolean = true,
    startFrame: @Composable (() -> Unit)? = null,
    clearIcon: Painter = painterResource(id = R.drawable.chili_ic_circle_clear),
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLenBeforeComma: Int = Int.MAX_VALUE,
    maxLenAfterComma: Int = InputFieldDefaults.MAX_DIGITS_AFTER_COMMA,
    addDecimal: Boolean = true,
    suffix: AnnotatedString? = null,
    enabled: Boolean = true,
    onActionClick: (() -> Unit) = {},
    onDoubleClick: ((Offset) -> Unit)? = null,
    onLongClick: ((Offset) -> Unit)? = null,
    onValueChange: ((TextFieldValue) -> Unit),
) {
    InputFieldContainer(
        modifier = modifier,
        value = value,
        error = error,
        isInputFieldEmpty = isInputFieldEmpty,
        inputBgColor = inputBgColor,
        isClearButtonEnabled = isClearButtonEnabled,
        message = message,
        messagePaddingValues = messagePaddingValues,
        actionText = actionText,
        isActionEnabled = isActionEnabled,
        actionTextStyle = actionTextStyle,
        startFrame = startFrame,
        clearIcon = clearIcon,
        isInputCenteredAlign = isInputCenteredAlign,
        onActionClick = onActionClick,
        onDoubleClick = onDoubleClick,
        onLongClick = onLongClick,
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
                val finalText = amountValueChange(filtered.text, maxDigitAfterComma = maxLenAfterComma, addDecimal)
                val lenBeforeComma = finalText.substringBefore(InputFieldDefaults.DECIMAL_COMMA).length

                if (lenBeforeComma <= maxLenBeforeComma) {
                    val finalValue = TextFieldValue(finalText, filtered.selection)
                    onValueChange(finalValue)
                }
            },
            placeholder = placeholder,
            enabled = enabled,
            textStyle = textStyle,
            isInputCenteredAlign = isInputCenteredAlign,
            keyboardType = keyboardType,
            visualTransformation = AmountInputVisualTransformator(
                addDecimals = addDecimal,
                suffix = suffix,
                maxDigitsAfterComma = maxLenAfterComma,
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
    messageWeight: Float = 2f,
    messagePaddingValues: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    actionText: String? = null,
    actionTextStyle: TextStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
    isActionEnabled: Boolean = true,
    actionEnabledTextColor: Color = Chili.color.buttonComponentText,
    actionDisabledTextColor: Color = Chili.color.buttonComponentDisabledText,
    isInputFieldEmpty: Boolean? = null,
    isInputCenteredAlign: Boolean = true,
    clearIcon: Painter = painterResource(id = R.drawable.chili_ic_circle_clear),
    rightActionIcon: Painter? = null,
    onRightActionIconClick: (() -> Unit) = {},
    startFrame: @Composable (() -> Unit)? = null,
    onActionClick: (() -> Unit) = {},
    onDoubleClick: ((Offset) -> Unit)? = null,
    onLongClick: ((Offset) -> Unit)? = null,
    onValueChange: ((TextFieldValue) -> Unit),
    onClearInput: (() -> Unit) = { onValueChange(TextFieldValue()) },
    inputField: @Composable RowScope.() -> Unit
) {
    val isValueEmpty by remember(isInputFieldEmpty, value.text) {
        mutableStateOf(isInputFieldEmpty ?: value.text.isEmpty())
    }

    Column(modifier = modifier) {
        Surface(
            modifier = Modifier.then(
                if (onDoubleClick != null || onLongClick != null) {
                    Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = onLongClick,
                            onDoubleTap = onDoubleClick
                        )
                    }
                } else Modifier
            ),
            color = decideBackgroundColor(error, inputBgColor),
            shape = Chili.shapes.RoundedCornerShape
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                when {
                    startFrame != null -> startFrame()
                    isClearButtonEnabled && !isValueEmpty && isInputCenteredAlign ->
                        Spacer(modifier = Modifier.width(44.dp))

                    rightActionIcon != null && isInputCenteredAlign ->
                        Spacer(modifier = Modifier.width(44.dp))
                }

                inputField()

                rightActionIcon?.takeIf { isValueEmpty }?.let {
                    InputFieldRightActionIcon(actionIcon = it) { onRightActionIconClick() }
                }

                if (isClearButtonEnabled && !isValueEmpty) {
                    InputFieldClearIcon(clearIcon = clearIcon) { onClearInput() }
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (message != null || error != null) {
                Text(
                    modifier = Modifier
                        .weight(messageWeight)
                        .padding(messagePaddingValues),
                    style = if (error == null) Chili.typography.H14_Secondary else Chili.typography.H14_Error,
                    text = error.takeIf { !it.isNullOrBlank() } ?: message ?: ""
                )
            }
            actionText?.let {
                Box(modifier = Modifier
                    .weight(1f)
                ) {
                    ChiliComponentButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = it,
                        textStyle = actionTextStyle,
                        enabled = isActionEnabled,
                        enabledTextColor = actionEnabledTextColor,
                        disabledTextColor = actionDisabledTextColor,
                        onClick = onActionClick
                    )
                }
            }
        }
    }
}

@Composable
private fun InputFieldClearIcon(clearIcon: Painter, clearValue: () -> Unit) {
    Image(
        painter = clearIcon,
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
private fun InputFieldRightActionIcon(actionIcon: Painter, rightActionClick: () -> Unit) {
    Image(
        painter = actionIcon,
        contentDescription = "right action",
        modifier = Modifier
            .padding(end = 8.dp, start = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
                onClick = rightActionClick
            )
    )
}

@Composable
internal fun decideBackgroundColor(error: String? = null, inputBgColor: Color): Color {
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
            isInputCenteredAlign = false,
            startFrame = {
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    painter = painterResource(id = R.drawable.chili4_ic_search),
                    contentDescription = ""
                )
            },
            message = "Message",
            actionText = "Action"
        ) {
        }
    }
}