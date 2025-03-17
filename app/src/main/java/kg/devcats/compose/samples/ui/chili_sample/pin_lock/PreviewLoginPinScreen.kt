package kg.devcats.compose.samples.ui.chili_sample.pin_lock

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonParams
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonType
import kg.devcats.compose.jetpack_chili.components.keyboards.KeyboardParams
import kg.devcats.compose.jetpack_chili.components.lock.PinInputComponent
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewLoginPinScreen(navigateUp: () -> Unit) {
    val context = LocalContext.current
    val pinCodeState = remember { mutableStateOf("") }
    val pinStatusState = remember { mutableStateOf<PinStatusType>(PinStatusType.None) }
    val mockPinManager = remember { MockPinManager() }

    LaunchedEffect(Unit) {
        mockPinManager.savePinCode("2121")
    }

    LaunchedEffect(pinCodeState.value) {
        if (pinCodeState.value.length >= MAX_PIN_SIZE) {
            pinStatusState.value = if (mockPinManager.checkPin(pinCodeState.value)) {
                PinStatusType.Success()
            } else {
                PinStatusType.Error()
            }
        } else {
            pinStatusState.value = PinStatusType.None
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(Chili.color.surfaceBackground),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chili_ic_logo_moy_o),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp).padding(top = 12.dp)
                )
                Text(
                    text = "4.1.0",
                    style = Chili.typography.H13_Primary,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 28.dp, start = 40.dp, end = 40.dp, bottom = 48.dp),
                    text = "Введите пин-код",
                    style = Chili.typography.H24_Primary_700,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )

                PinInputComponent(
                    modifier = Modifier,
                    keyboardParams = KeyboardParams(
                        onInputChange = { text -> pinCodeState.value = text }
                    ),
                    actionButtonParams = ActionButtonParams(
                        buttonType = ActionButtonType.Text.create(text = "Забыли?"),
                        onClick = { context.showToast("Action") }
                    ),
                    errorAnimFinished = {
                        pinCodeState.value = ""
                    },
                    successAnimFinished = {
                        pinCodeState.value = ""
                        navigateUp()
                    },
                    pinCodeState = pinCodeState,
                    pinStatusState = pinStatusState
                )
            }
        }
    )
}