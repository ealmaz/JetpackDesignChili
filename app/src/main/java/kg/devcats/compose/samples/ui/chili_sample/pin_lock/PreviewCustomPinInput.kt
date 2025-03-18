package kg.devcats.compose.samples.ui.chili_sample.pin_lock

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonParams
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonType
import kg.devcats.compose.jetpack_chili.components.keyboards.KeyboardParams
import kg.devcats.compose.jetpack_chili.components.keyboards.PinKeyboard
import kg.devcats.compose.jetpack_chili.components.lock.PinInputComponent
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pin.ChiliPinInputField
import kg.devcats.compose.jetpack_chili.components.pin.PinItemConfig
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.black_7
import kg.devcats.compose.jetpack_chili.theme.blue_1
import kg.devcats.compose.samples.R
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewCustomPinInput(navigateUp: () -> Unit) {
    val context = LocalContext.current
    val pinCodeState = remember { mutableStateOf("") }
    val pinStatusState = remember { mutableStateOf<PinStatusType>(PinStatusType.None) }
    val mockPinManager = remember { MockPinManager() }

    LaunchedEffect(Unit) {
        mockPinManager.savePinCode("0000")
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
        topBar = {
            ChiliCenteredAppToolbar(
                navigationIcon = painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili4_ic_back_arrow_rounded),
                onNavigationIconClick = navigateUp,
                title = ""
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
                Image(
                    modifier = Modifier.padding(top = 30.dp).fillMaxWidth(),
                    painter = painterResource(R.drawable.ic_esi),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

                Spacer(Modifier.weight(1f))

                Column(
                    modifier = Modifier.padding(top = 35.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 40.dp),
                        text = "Введите пин-код",
                        style = Chili.typography.H20_Secondary,
                        maxLines = 2,
                        minLines = 2
                    )

                    ChiliPinInputField(
                        modifier = Modifier.padding(top = 12.dp),
                        lockItemModifier = Modifier.size(19.dp),
                        pinCode = pinCodeState,
                        pinStatusType = pinStatusState,
                        maxSize = 4,
                        errorAnimFinished = {
                            pinCodeState.value = ""
                        },
                        successAnimFinished = {
                            pinCodeState.value = ""
                            navigateUp()
                        },
                        pinItemConfig = PinItemConfig.create(
                            size = 19.dp,
                            borderWidth = 1.dp,
                            borderColor = black_7,
                            selectedColor = blue_1
                        )
                    )
                }

                Spacer(Modifier.weight(1f))

                PinKeyboard(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .heightIn(400.dp, 420.dp),
                    keyboardParams = KeyboardParams(
                        modifier = Modifier.weight(1f),
                        textState = pinCodeState,
                        digitTextStyle = Chili.typography.H42_Primary.copy(
                            fontFamily = FontFamily(Font(R.font.museo_regular))
                        ),
                        digitTextColor = Color(0xFF36424B),
                        codeMaxSize = 4,
                        onInputChange = { pinCodeState.value = it }
                    ),
                    leftActionButtonParams = ActionButtonParams(
                        buttonType = ActionButtonType.Text(
                            text = "Выйти",
                            textStyle = Chili.typography.H18_Primary.copy(
                                fontFamily = FontFamily(Font(R.font.museo_regular))
                            )
                        ),
                        onClick = { context.showToast("Action") }
                    )
                )

                Text(
                    modifier = Modifier.padding(bottom = 40.dp),
                    text = "Забыли пин-код?",
                    style = Chili.typography.H16_Primary,
                    color = blue_1
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    PreviewCustomPinInput {  }
}