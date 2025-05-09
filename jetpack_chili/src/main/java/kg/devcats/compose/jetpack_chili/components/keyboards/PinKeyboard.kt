package kg.devcats.compose.jetpack_chili.components.keyboards

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.clickableWithoutEffect
import kg.devcats.compose.jetpack_chili.rippleClickable
import kg.devcats.compose.jetpack_chili.setIsAlpha
import kg.devcats.compose.jetpack_chili.setIsPressedEffect
import kg.devcats.compose.jetpack_chili.theme.Chili

sealed class ActionButtonType {
    data object None : ActionButtonType()
    data class Drawable(val defaultDrawable: Painter, val pressedDrawable: Painter) : ActionButtonType()
    data class Text(val text: String, val textStyle: TextStyle? = null): ActionButtonType()
}

@Composable
fun PinKeyboard(
    modifier: Modifier = Modifier,
    keyboardParams: KeyboardParams = KeyboardParams(),
    leftActionButtonParams: ActionButtonParams = ActionButtonParams(),
    rightActionButtonParams: ActionButtonParams = ActionButtonParams()
) {
    var inputText by remember(keyboardParams.textState.value) { mutableStateOf(keyboardParams.textState.value) }
    val digitTextStyle = keyboardParams.digitTextStyle ?: Chili.typography.H28_Primary

    fun addDigit(digit: String) {
        if (inputText.length < keyboardParams.codeMaxSize) {
            inputText += digit
            keyboardParams.onInputChange(inputText)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..9).chunked(3).forEach { row ->
            NumberRow(row, keyboardParams.isEnableInput, digitTextStyle, keyboardParams.modifier) { addDigit(it) }
        }

        Row(
            modifier = keyboardParams.modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionButton(
                actionButtonParams = leftActionButtonParams,
                keyboardParams = keyboardParams
            )

            KeyboardDigitButton(
                digit = 0,
                digitTextStyle = digitTextStyle,
                onClick = { addDigit("0") },
                enabled = keyboardParams.isEnableInput
            )

            if (inputText.isNotEmpty() || rightActionButtonParams.buttonType == ActionButtonType.None) {
                KeyboardIconButton(
                    defaultIcon = painterResource(id = keyboardParams.clearIconButtonDefault ?: R.drawable.chili_ic_remove_digit),
                    pressedIcon = painterResource(id = keyboardParams.clearIconButtonPressed ?: R.drawable.chili_ic_remove_digit_pressed),
                    onClick = {
                        inputText = inputText.dropLast(1)
                        keyboardParams.onInputChange(inputText)
                    },
                    enabled = keyboardParams.isEnableInput
                )
            } else {
                ActionButton(
                    actionButtonParams = rightActionButtonParams,
                    keyboardParams = keyboardParams
                )
            }
        }
    }
}

@Composable
private fun NumberRow(
    digits: List<Int>,
    enableInput: Boolean,
    digitTextStyle: TextStyle = Chili.typography.H28_Primary,
    modifier: Modifier = Modifier,
    onInputChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        digits.forEach { digit ->
            KeyboardDigitButton(
                digit = digit,
                digitTextStyle = digitTextStyle,
                onClick = { onInputChange(digit.toString()) },
                enabled = enableInput
            )
        }
    }
}

@Composable
fun KeyboardDigitButton(
    digit: Int,
    onClick: () -> Unit,
    digitTextStyle: TextStyle = Chili.typography.H28_Primary,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
            .rippleClickable(
                enabled,
                onClick = onClick,
                radius = 32.dp,
                rippleColor = Chili.color.pinDigitClickedBackground
            )
            .setIsAlpha(enabled),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit.toString(),
            style = digitTextStyle
        )
    }
}

@Composable
fun KeyboardIconButton(
    defaultIcon: Painter?,
    pressedIcon: Painter?,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val isPressed = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
            .setIsPressedEffect(isPressed, onClick, enabled)
            .setIsAlpha(enabled),
        contentAlignment = Alignment.Center
    ) {
        if (enabled) {
            val iconToDisplay = if (isPressed.value) pressedIcon else defaultIcon
            iconToDisplay?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = modifier.sizeIn(minWidth = 30.dp, minHeight = 24.dp)
                )
            }
        }
    }
}

@Composable
fun KeyboardTextButton(
    type: ActionButtonType.Text,
    onClick: () -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle? = null,
    textColor: Color? = null,
    modifier: Modifier = Modifier
) {
    val colorState = animateColorAsState(
        targetValue = if (enabled) textColor ?: Chili.color.primaryText else Chili.color.secondaryText,
        label = ""
    )

    Box(
        modifier = modifier
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
            .clickableWithoutEffect { if (enabled) onClick() }
            .setIsAlpha(enabled),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.text,
            style = textStyle ?: Chili.typography.H16_Primary,
            color = colorState.value
        )
    }
}

data class KeyboardParams(
    val modifier: Modifier = Modifier,
    val textState: State<String> = mutableStateOf(""),
    val onInputChange: (String) -> Unit = {},
    var digitTextStyle: TextStyle? = null,
    val isEnableInput: Boolean = true,
    val codeMaxSize: Int = 4,
    @DrawableRes val clearIconButtonDefault: Int? = null,
    @DrawableRes val clearIconButtonPressed: Int? = null,
)

data class ActionButtonParams(
    val modifier: Modifier = Modifier,
    val buttonType: ActionButtonType = ActionButtonType.None,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
private fun Preview() {
    PinKeyboard()
}
