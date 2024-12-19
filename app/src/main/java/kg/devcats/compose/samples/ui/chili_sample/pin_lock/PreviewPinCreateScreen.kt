package kg.devcats.compose.samples.ui.chili_sample.pin_lock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.keyboards.KeyboardParams
import kg.devcats.compose.jetpack_chili.components.lock.PinInputComponent
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType
import kg.devcats.compose.jetpack_chili.theme.Chili

const val MAX_PIN_SIZE = 4

@Composable
fun PreviewPinCreateScreen(
    navigateUp: () -> Unit = {}
) {
    var isConfirm by remember { mutableStateOf(false) }
    val pinCodeState = remember { mutableStateOf("") }
    val pinStatusState = remember { mutableStateOf<PinStatusType>(PinStatusType.None) }
    val pinManager = remember { PinManager() }

    LaunchedEffect(pinCodeState.value) {
        if (pinCodeState.value.length >= MAX_PIN_SIZE) {
            if (!isConfirm) {
                pinManager.savePinCode(pinCodeState.value)
                isConfirm = true
                pinCodeState.value = ""
            } else {
                pinStatusState.value = if (pinManager.checkPin(pinCodeState.value)) {
                    PinStatusType.Success()
                } else {
                    PinStatusType.Error()
                }
            }
        } else {
            pinStatusState.value = PinStatusType.None
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            ChiliCenteredAppToolbar(
                title = "",
                onNavigationIconClick = navigateUp
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(Chili.color.surfaceBackground),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 28.dp, start = 40.dp, end = 40.dp, bottom = 48.dp),
                    text = if (!isConfirm) "Создайте пин-код для входa" else "Подтвердите пин-код",
                    style = Chili.typography.H24_Primary_700,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )

                PinInputComponent(
                    modifier = Modifier,
                    keyboardParams = KeyboardParams(
                        onInputChange = { text -> pinCodeState.value = text },
                        codeMaxSize = MAX_PIN_SIZE
                    ),
                    errorAnimFinished = {
                        pinCodeState.value = ""
                    },
                    successAnimFinished = {
                        pinCodeState.value = ""
                    },
                    pinCodeState = pinCodeState,
                    pinStatusState = pinStatusState
                )
            }
        }
    )
}