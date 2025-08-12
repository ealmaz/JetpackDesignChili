package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliAmountInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliDoubleInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputOtp
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliUneditableInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.MaskInputInterceptor
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewInputFields(navigateUp: () -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.surfaceBackground)
        .safeDrawingPadding()) {
        ChiliCenteredAppToolbar(title = "InputFields",
            endFrame = { SampleToolbarMenu() },
            isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 164.dp)) {

            var inputText by remember { mutableStateOf("") }
            ChiliInputField(
                value = inputText,
                modifier = Modifier.padding(top = 16.dp),
                containerShape = RectangleShape,
                message = "Message",
                placeholder = "Placeholder",
                actionText = "Action",
            ) { inputText = it }

            var inputText2 by remember { mutableStateOf("") }
            ChiliInputField(
                value = inputText2,
                modifier = Modifier.padding(top = 16.dp),
                message = "Message",
                placeholder = "Placeholder",
                onRightActionIconClick = {},
                rightActionIcon = painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_contact),
                actionText = "Action",
                isInputCenteredAlign = false,
            ) { inputText2 = it }

            var amountInput by remember { mutableStateOf(TextFieldValue("0")) }
            ChiliAmountInputField(
                value = amountInput,
                modifier = Modifier.padding(top = 16.dp),
                message = "Amount Input field",
                actionText = "Action",
                isInputCenteredAlign = false,
                keyboardType = KeyboardType.Number,
            ) {
                amountInput = it
            }

            ChiliAmountInputField(
                inputBgColor = Chili.color.inputFieldPrimaryBg,
                value = amountInput,
                modifier = Modifier
                    .padding(top = 16.dp),
                message = "Message",
                placeholder = "Placeholder",
                actionText = "Action",
                suffix = AnnotatedString.fromHtml("<u>c</u>"),
                keyboardType = KeyboardType.Number,
                maxLenBeforeComma = 6
            ) { textFieldValue ->
                amountInput = textFieldValue
            }

            Text("DoubleInputField")
            Spacer(modifier = Modifier.height(16.dp))

            var doubleInputFieldValue1 by remember { mutableStateOf(TextFieldValue("0")) }
            var doubleInputFieldValue2 by remember { mutableStateOf(TextFieldValue("0")) }
            ChiliDoubleInputField(
                message = "В этом месяце можно продать на 87 000,00 c",
                firstFieldValue = doubleInputFieldValue1,
                secondFieldValue = doubleInputFieldValue2,
                firstFieldSuffix = "<u>c</u>".parseHtml(),
                secondFieldSuffix = "BTC".parseHtml(),
                firstFieldMaxLengthAfterComma = 6,
                secondFieldMaxLengthAfterComma = 6,
                onInputOnFirstField = {
                    doubleInputFieldValue1 = it
                },
                onInputOnSecondField = {
                    doubleInputFieldValue2 = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            var doubleErrorInputFieldValue1 by remember { mutableStateOf(TextFieldValue("0")) }
            var doubleErrorInputFieldValue2 by remember { mutableStateOf(TextFieldValue("0")) }

            ChiliDoubleInputField(
                message = "В этом месяце можно продать на 87 000,00 c",
                firstFieldValue = doubleErrorInputFieldValue1,
                secondFieldValue = doubleErrorInputFieldValue2,
                firstFieldSuffix = "<u>c</u>".parseHtml(),
                firstFieldMaxLengthAfterComma = 6,
                secondFieldMaxLengthAfterComma = 6,
                errorMessage = "Минимальный объём покупки 4 000,00с",
                onInputOnFirstField = {doubleErrorInputFieldValue1 = it},
                onInputOnSecondField = {doubleErrorInputFieldValue2 = it}
            )


            val inputMask1 = "+996 XXX XXX XXX"
            val maskInterceptor = remember { MaskInputInterceptor(inputMask1) }
            var inputText3 by remember { mutableStateOf(maskInterceptor.intercept(TextFieldValue())) }

            ChiliInputField(
                value = inputText3,
                modifier = Modifier.padding(top = 16.dp),
                onRightActionIconClick = {},
                rightActionIcon = painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_contact),
                isInputFieldEmpty = inputText3.text == inputMask1,
                keyboardType = KeyboardType.Number,
            ) {
                inputText3 = maskInterceptor.intercept(it)
            }

            val maskInterceptor2 = remember { MaskInputInterceptor("XX XX - XX / XX : XX POST XX") }
            var inputText4 by remember { mutableStateOf(maskInterceptor2.intercept(TextFieldValue())) }

            ChiliInputField(
                value = inputText4,
                modifier = Modifier.padding(top = 16.dp),
                keyboardType = KeyboardType.Number,
            ) { inputText4 = maskInterceptor2.intercept(it) }


            var inputText5 by remember { mutableStateOf("") }
            var error by remember { mutableStateOf<String?>("Some error message") }
            ChiliInputField(
                value = inputText5,
                modifier = Modifier.padding(top = 16.dp),
                message = "Message text",
                placeholder = "Placeholder",
                actionText = "Trigger error",
                onActionClick = {
                    error = if (error == null) "Some error message"
                    else null
                },
                error = error
            ) {
                error = null
                inputText5 = it
            }


            var inputText6 by remember { mutableStateOf("Prefilled value") }
            ChiliInputField(
                value = inputText6,
                modifier = Modifier.padding(top = 16.dp),
                isInputCenteredAlign = false,
            ) { inputText6 = it }

            var inputText10 by remember { mutableStateOf("Search...") }
            ChiliInputField(
                value = inputText10,
                modifier = Modifier.padding(top = 16.dp),
                isInputCenteredAlign = false,
                inputFieldPaddingValues = PaddingValues(all = 8.dp),
                clearIcon = painterResource(id = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_close_dark_gray),
                startFrame = {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = kg.devcats.compose.jetpack_chili.R.drawable.chili4_ic_search),
                        contentDescription = "")
                }
            ) { inputText10 = it }

            var inputText7 by remember { mutableStateOf("Prefilled value") }
            ChiliInputField(
                value = inputText7,
                modifier = Modifier.padding(top = 16.dp),
                isInputCenteredAlign = true,
            ) { inputText7 = it }


            var inputText8 by remember { mutableStateOf("80") }
            ChiliInputField(
                value = inputText8,
                modifier = Modifier.padding(top = 16.dp),
                isInputCenteredAlign = true,
            ) { inputText8 = it }


            var errorState by remember { mutableStateOf<String?>(null) }


            ChiliInputOtp(
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                action = "Some action",
                message = "Some message",
                error = errorState
            ) {
                if (it == "123456") errorState = "Error"
                else errorState = null
            }

            Spacer(Modifier.height(16.dp))

            var smsValue by remember { mutableStateOf("") }
            ChiliInputField(
                value = smsValue,
                placeholder = "Код из SMS",
                actionText = "Повторить через 01:59",
                isActionEnabled = false,
                actionTextStyle = Chili.typography.H16_Value,
                actionDisabledTextColor = Chili.color.valueText,
                keyboardType = KeyboardType.Number,
                messageWeight = 1f,
                onValueChange = { smsValue = it },
            )

            ChiliInputOtp(
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                action = "Some action",
                message = "Some message",
                error = errorState
            ) {
                if (it == "123456") errorState = "Error"
                else errorState = null
            }

            ChiliInputOtp(
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                action = "Some action",
                message = "Some message",
                otpLength = 4,
                otpItemPadding = 24.dp,
                error = errorState
            ) {
                if (it == "123456") errorState = "Error"
                else errorState = null
            }

            ChiliInputOtp(
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                action = "Some action",
                message = "Some message",
                otpLength = 7,
                otpItemPadding = 8.dp,
                error = errorState
            ) {
                if (it == "123456") errorState = "Error"
                else errorState = null
            }

            ChiliUneditableInputField(
                modifier = Modifier.padding(top = 16.dp),
                text = "Chili Uneditable Input Field",
                onClick = { context.showToast("Chili Uneditable Input Field") }
            )

            ChiliUneditableInputField(
                modifier = Modifier.padding(top = 16.dp),
                hint = "Chili Uneditable Input Field",
                onClick = { context.showToast("Chili Uneditable Input Field") }
            )

            ChiliUneditableInputField(
                modifier = Modifier.padding(top = 16.dp),
                text = "Chili Uneditable Input Field Max",
                onClick = { context.showToast("Chili Uneditable Input Field") }
            )

            var isErrorState by remember { mutableStateOf(true) }
            ChiliUneditableInputField(
                modifier = Modifier.padding(top = 16.dp),
                hint = if (isErrorState) "Click me to hide error state" else "Click me to show error state" ,
                error = "Check that the data is entered correctly".takeIf { isErrorState },
                message = "The data is entered correctly",
                onClick = { isErrorState = !isErrorState }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewInputFields() {
    PreviewInputFields({})
}