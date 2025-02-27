package kg.devcats.compose.samples.ui.chili_sample.keyboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliLoaderButton
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliAmountInputField
import kg.devcats.compose.jetpack_chili.components.keyboards.NumberKeyboardFrame
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PreviewNumberKeyboard(navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
            .imePadding()
    ) {
        ChiliCenteredAppToolbar(
            title = "NumberKeyboard",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        var isKeyboardVisible by remember { mutableStateOf(false) }

        NumberKeyboardFrame(
            modifier = Modifier.weight(1f),
            specialSymbols = listOf(',', '&'),
            isKeyboardVisible = isKeyboardVisible
        ) {
            var inputText by remember { mutableStateOf(TextFieldValue(text = "0")) }
            var inputText2 by remember { mutableStateOf(TextFieldValue(text = "0")) }

            Column {
                ChiliAmountInputField(
                    inputBgColor = Chili.color.inputFieldPrimaryBg,
                    value = inputText,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .onFocusChanged {
                            if (it.isFocused) {
                                isKeyboardVisible = true
                            }
                        }
                    ,
                    message = "Message",
                    placeholder = "Placeholder",
                    actionText = "Action",
                    suffix = AnnotatedString.fromHtml("<u>c</u>"),
                    keyboardType = KeyboardType.Number,
                    maxLenBeforeComma = 6
                ) { textFieldValue ->
                    inputText = textFieldValue
                }

                ChiliAmountInputField(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .onFocusChanged {
                            if (it.isFocused) {
                                isKeyboardVisible = true
                            }
                        }
                    ,
                    inputBgColor = Chili.color.inputFieldPrimaryBg,
                    value = inputText2,
                    message = "Message",
                    placeholder = "Placeholder",
                    actionText = "Action",
                    suffix = AnnotatedString.fromHtml("<u>b</u>"),
                    keyboardType = KeyboardType.Number,
                ) { textFieldValue ->
                    inputText2 = textFieldValue
                }
                ChiliLoaderButton(text = "Изменить видимость кнопки") {
                    isKeyboardVisible = !isKeyboardVisible
                }
            }
        }
    }
}