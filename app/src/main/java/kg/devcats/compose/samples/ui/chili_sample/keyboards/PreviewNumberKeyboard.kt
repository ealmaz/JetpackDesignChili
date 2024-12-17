package kg.devcats.compose.samples.ui.chili_sample.keyboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliAmountInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.handleZero
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputField
import kg.devcats.compose.jetpack_chili.components.keyboard.NumberKeyboard
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
        val systemKeyboardController = LocalSoftwareKeyboardController.current

        var inputText by remember { mutableStateOf(TextFieldValue(text = "0")) }
        systemKeyboardController?.hide()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ChiliAmountInputField(
                value = inputText,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .onFocusChanged {
                        isKeyboardVisible = it.isFocused
                        systemKeyboardController?.hide()
                    },
                message = "Message",
                placeholder = "Placeholder",
                actionText = "Action",
            ) { textFieldValue ->
                inputText = textFieldValue.handleZero(previousValue = inputText)
            }

            if (isKeyboardVisible) {
                NumberKeyboard(
                    textFieldValue = inputText,
                    specialSymbols = listOf(','),
                    onInputChanged = { textFieldValue ->
                        inputText = textFieldValue.handleZero(previousValue = inputText)
                })
            }
        }
    }
}