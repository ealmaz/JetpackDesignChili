package kg.devcats.compose.samples.ui.chili_sample.keyboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonParams
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonType
import kg.devcats.compose.jetpack_chili.components.keyboards.KeyboardParams
import kg.devcats.compose.jetpack_chili.components.keyboards.PinKeyboard
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pin.ChiliPinInputField
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewPinKeyboard(navigateUp: () -> Unit) {
    val context = LocalContext.current
    val pinCode = remember { mutableStateOf("") }
    val code = "2323"
    val pinStatusState = remember { mutableStateOf<PinStatusType>(PinStatusType.None) }

    LaunchedEffect(pinCode.value) {
        pinStatusState.value = when {
            pinCode.value.length == code.length -> if (pinCode.value == code) PinStatusType.Success() else PinStatusType.Error()
            else -> PinStatusType.None
        }
    }

    Scaffold(
        topBar = {
            ChiliCenteredAppToolbar(
                title = "Pin Keyboard",
                isDividerVisible = true,
                isNavigationIconVisible = true,
                onNavigationIconClick = {
                    navigateUp.invoke()
                })
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(Chili.color.surfaceBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ChiliPinInputField(
                    modifier = Modifier.padding(top = 40.dp),
                    pinCode = pinCode,
                    pinStatusType = pinStatusState,
                    errorAnimFinished = {
                        pinCode.value = ""
                        context.showToast("Error")
                    },
                    successAnimFinished = {
                        pinCode.value = ""
                        context.showToast("Success")
                    }
                )

                // Keyboard
                PinKeyboard(
                    keyboardParams = KeyboardParams(
                        textState = pinCode,
                        onInputChange = { pinCode.value = it }
                    ),
                    leftActionButtonParams = ActionButtonParams(
                        buttonType = ActionButtonType.Text.create("Forgot?"),
                        onClick = { context.showToast("Action") }
                    )
                )
            }
        }
    )
}