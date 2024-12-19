package kg.devcats.compose.jetpack_chili.components.lock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonParams
import kg.devcats.compose.jetpack_chili.components.keyboards.KeyboardParams
import kg.devcats.compose.jetpack_chili.components.keyboards.PinKeyboard
import kg.devcats.compose.jetpack_chili.components.pin.ChiliPinInputField
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType

@Composable
fun PinInputComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    pinCodeState: State<String> = mutableStateOf(""),
    pinStatusState: State<PinStatusType> = mutableStateOf(PinStatusType.None),
    errorAnimFinished: () -> Unit = {},
    successAnimFinished: () -> Unit = {},
    codeMaxSize: Int = 4,
    keyboardParams: KeyboardParams = KeyboardParams(),
    actionButtonParams: ActionButtonParams = ActionButtonParams()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChiliPinInputField(
            pinCode = pinCodeState,
            pinStatusType = pinStatusState,
            errorAnimFinished = errorAnimFinished,
            successAnimFinished = successAnimFinished,
            maxSize = codeMaxSize
        )

        Spacer(modifier = Modifier.weight(1f))

        PinKeyboard(
            keyboardParams = keyboardParams.copy(
                textState = pinCodeState,
                codeMaxSize = codeMaxSize
            ),
            actionButtonParams = actionButtonParams
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PinInputComponent()
}