package kg.devcats.compose.jetpack_chili.components.keyboards

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
    data class Drawable(val defaultDrawable: Painter, val pressedDrawable: Painter) : ActionButtonType()
    data class Text(val text: String): ActionButtonType()
    data object None : ActionButtonType()
}

@Composable
fun PinKeyboard(
    keyboardParams: KeyboardParams = KeyboardParams(),
    actionButtonParams: ActionButtonParams = ActionButtonParams()
) {
    var inputText by remember(keyboardParams.textState.value) { mutableStateOf(keyboardParams.textState.value) }
    val buttonType = remember(actionButtonParams) { actionButtonParams.buttonType }

    fun addDigit(digit: String) {
        if (inputText.length < keyboardParams.codeMaxSize) {
            inputText += digit
            keyboardParams.onInputChange(inputText)
        }
    }

    Column(
        modifier = keyboardParams.modifier
            .fillMaxWidth()
            .background(Chili.color.surfaceBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NumberRow(listOf(1, 2, 3), keyboardParams.isEnableInput) { digit -> addDigit(digit) }
        NumberRow(listOf(4, 5, 6), keyboardParams.isEnableInput) { digit -> addDigit(digit) }
        NumberRow(listOf(7, 8, 9), keyboardParams.isEnableInput) { digit -> addDigit(digit) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            when(buttonType) {
                is ActionButtonType.None -> Spacer(Modifier.width(64.dp))
                is ActionButtonType.Text -> KeyboardTextButton(
                    text = buttonType.text,
                    onClick = actionButtonParams.onClick,
                    enabled = keyboardParams.isEnableInput
                )
                is ActionButtonType.Drawable -> KeyboardIconButton(
                    defaultIcon = buttonType.defaultDrawable,
                    pressedIcon = buttonType.pressedDrawable,
                    onClick = actionButtonParams.onClick,
                    enabled = keyboardParams.isEnableInput
                )
            }

            KeyboardDigitButton(
                digit = 0,
                onClick = { addDigit("0") },
                enabled = keyboardParams.isEnableInput
            )

            KeyboardIconButton(
                defaultIcon = painterResource(id = R.drawable.chili_ic_remove_digit),
                pressedIcon = painterResource(id = R.drawable.chili_ic_remove_digit_pressed),
                onClick = {
                    inputText = inputText.dropLast(1)
                    keyboardParams.onInputChange(inputText)
                },
                enabled = keyboardParams.isEnableInput
            )
        }
    }
}

@Composable
private fun NumberRow(
    digits: List<Int>,
    enableInput: Boolean,
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
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .background(
                color = if (enabled) Chili.color.surfaceBackground else Chili.color.dividerColor,
                shape = Chili.shapes.Circle
            )
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
            style = Chili.typography.H28_Primary,
            color = Chili.color.primaryText
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
        modifier = modifier
            .size(64.dp)
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
                    modifier = Modifier.size(width = 30.dp, height = 24.dp)
                )
            }
        }
    }
}

@Composable
fun KeyboardTextButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle = Chili.typography.H16_Primary,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clickableWithoutEffect { if (enabled) onClick() }
            .setIsAlpha(enabled),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            color = Chili.color.primaryText
        )
    }
}

data class KeyboardParams(
    val modifier: Modifier = Modifier,
    val textState: State<String> = mutableStateOf(""),
    val onInputChange: (String) -> Unit = {},
    val isEnableInput: Boolean = true,
    val codeMaxSize: Int = 4,
)

data class ActionButtonParams(
    val buttonType: ActionButtonType = ActionButtonType.None,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
private fun Preview() {
    PinKeyboard()
}
