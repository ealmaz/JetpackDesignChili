package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputOtp
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliUneditableInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.MaskInputInterceptor
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewInputFields(navigateUp: () -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.surfaceBackground)
        .imePadding()) {
        ChiliCenteredAppToolbar(title = "InputFields", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
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
                actionText = "Action",
                isInputCenteredAlign = false,
            ) { inputText2 = it }


            val maskInterceptor = remember { MaskInputInterceptor("+996 XXX XXX XXX") }
            var inputText3 by remember { mutableStateOf(maskInterceptor.intercept(TextFieldValue())) }

            ChiliInputField(
                value = inputText3,
                modifier = Modifier.padding(top = 16.dp),
                keyboardType = KeyboardType.Number,
            ) { inputText3 = maskInterceptor.intercept(it) }

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
                suffix = {
                    Image(painter = painterResource(id = R.drawable.chili_ic_documents_green), contentDescription = "")
                }
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
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewInputFields() {
    PreviewInputFields({})
}