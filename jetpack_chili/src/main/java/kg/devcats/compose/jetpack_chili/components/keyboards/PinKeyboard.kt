package kg.devcats.compose.jetpack_chili.components.keyboards

import androidx.annotation.DrawableRes
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
    data class Drawable(val defaultDrawable: Painter, val pressedDrawable: Painter) : ActionButtonType()
    data class Text(
        val text: String,
        val textStyle: TextStyle,
        val textColor: Color
    ): ActionButtonType() {
        companion object {
            @Composable
            fun create(
                text: String,
                textStyle: TextStyle = Chili.typography.H16_Primary,
                textColor: Color = Chili.color.primaryText
            ) = Text(
                text = text,
                textStyle = textStyle,
                textColor = textColor
            )
        }
    }
    data object None : ActionButtonType()
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
    val digitTextColor = keyboardParams.digitTextColor ?: Chili.color.primaryText

    fun addDigit(digit: String) {
        if (inputText.length < keyboardParams.codeMaxSize) {
            inputText += digit
            keyboardParams.onInputChange(inputText)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Chili.color.surfaceBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..9).chunked(3).forEach { row ->
            NumberRow(row, keyboardParams.isEnableInput, digitTextStyle, digitTextColor, keyboardParams.modifier) { addDigit(it) }
        }

        Row(
            modifier = Modifier
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
                digitTextColor = digitTextColor,
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
                    modifier = Modifier.weight(1f),
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
    digitTextColor: Color = Chili.color.primaryText,
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
                digitTextColor = digitTextColor,
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
    digitTextColor: Color = Chili.color.primaryText,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
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
            style = digitTextStyle,
            color = digitTextColor
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
                    modifier = Modifier.size(width = 30.dp, height = 24.dp)
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
            .clickableWithoutEffect { if (enabled) onClick() }
            .setIsAlpha(enabled),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.text,
            style = type.textStyle,
            color = type.textColor
        )
    }
}

data class KeyboardParams(
    val modifier: Modifier = Modifier,
    val textState: State<String> = mutableStateOf(""),
    val onInputChange: (String) -> Unit = {},
    var digitTextStyle: TextStyle? = null,
    var digitTextColor: Color? = null,
    val isEnableInput: Boolean = true,
    val codeMaxSize: Int = 4,
    @DrawableRes val clearIconButtonDefault: Int? = null,
    @DrawableRes val clearIconButtonPressed: Int? = null,
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
