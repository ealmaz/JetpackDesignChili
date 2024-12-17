package kg.devcats.compose.jetpack_chili.components.keyboards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.clickableWithoutEffect
import kg.devcats.compose.jetpack_chili.rippleClickable
import kg.devcats.compose.jetpack_chili.setIsPressedEffect
import kg.devcats.compose.jetpack_chili.theme.Chili

sealed class Input {
    data class Digit(val digit: Int) : Input()
    data object Backspace : Input()
    data object ActionDrawable : Input()
    data object ActionText : Input()
}

enum class ActionButtonType {
    DRAWABLE,
    TEXT
}


@Composable
fun ChiliKeyboard(
    modifier: Modifier = Modifier,
    isActionButtonEnabled: Boolean = true,
    actionButtonType: ActionButtonType = ActionButtonType.DRAWABLE,
    actionButtonText: String? = null,
    onActionTextClick: (() -> Unit)? = null,
    actionButtonDefaultDrawable: Painter? = null,
    actionButtonPressedDrawable: Painter? = null,
    onActionDrawableClick: (() -> Unit)? = null,
    onInputChange: (String) -> Unit = {},
    enableInput: Boolean = true
) {
    var inputText by remember { mutableStateOf("") }

    val backgroundColor = Chili.color.surfaceBackground
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NumberRow(listOf(1, 2, 3), enableInput) { digit ->
            inputText += digit
            onInputChange(inputText)
        }
        NumberRow(listOf(4, 5, 6), enableInput) { digit ->
            inputText += digit
            onInputChange(inputText)
        }
        NumberRow(listOf(7, 8, 9), enableInput) { digit ->
            inputText += digit
            onInputChange(inputText)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isActionButtonEnabled) {
                when (actionButtonType) {
                    ActionButtonType.TEXT -> KeyboardTextButton(
                        text = actionButtonText ?: "",
                        onClick = {
                            onActionTextClick?.invoke()
                        },
                        enabled = enableInput
                    )

                    ActionButtonType.DRAWABLE -> KeyboardIconButton(
                        defaultIcon = actionButtonDefaultDrawable,
                        pressedIcon = actionButtonPressedDrawable,
                        onClick = {
                            onActionDrawableClick?.invoke()
                        },
                        enabled = enableInput
                    )
                }
            } else {
                Spacer(Modifier.width(64.dp))
            }

            KeyboardDigitButton(
                digit = 0,
                onClick = {
                    inputText += "0"
                    onInputChange(inputText)
                },
                enabled = enableInput
            )

            KeyboardIconButton(
                defaultIcon = painterResource(id = R.drawable.chili_ic_remove_digit),
                pressedIcon = painterResource(id = R.drawable.chili_ic_remove_digit_pressed),
                onClick = {
                    inputText = inputText.dropLast(1)
                    onInputChange.invoke(inputText)
                },
                enabled = enableInput
            )
        }
    }
}

@Composable
private fun NumberRow(
    digits: List<Int>,
    enableInput: Boolean,
    onInputChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
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
                rippleColor = Chili.color.valueText
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit.toString(),
            style = Chili.typography.H28_Primary,
            color = if (enabled) Chili.color.primaryText else Chili.color.secondaryText
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
            .setIsPressedEffect(isPressed, onClick, enabled),
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
            .clickableWithoutEffect { if (enabled) onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            color = if (enabled) Chili.color.primaryText else Chili.color.secondaryText
        )
    }
}
