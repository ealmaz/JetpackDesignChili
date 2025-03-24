package kg.devcats.compose.jetpack_chili.components.keyboards

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ActionButton(
    modifier: Modifier = Modifier,
    actionButtonParams: ActionButtonParams,
    keyboardParams: KeyboardParams
) {
    when(actionButtonParams.buttonType) {
        is ActionButtonType.Text -> KeyboardTextButton(
            type = actionButtonParams.buttonType,
            onClick = actionButtonParams.onClick,
            enabled = keyboardParams.isEnableInput,
            textStyle = actionButtonParams.buttonType.textStyle,
            modifier = modifier
        )
        is ActionButtonType.Drawable -> KeyboardIconButton(
            defaultIcon = actionButtonParams.buttonType.defaultDrawable,
            pressedIcon = actionButtonParams.buttonType.pressedDrawable,
            onClick = actionButtonParams.onClick,
            enabled = keyboardParams.isEnableInput,
            modifier = modifier
        )
        else -> Spacer(modifier.widthIn(min = 64.dp))
    }
}