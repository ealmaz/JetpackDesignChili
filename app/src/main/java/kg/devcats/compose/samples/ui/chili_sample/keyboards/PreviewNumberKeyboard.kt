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
import androidx.compose.ui.unit.dp
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

        var inputText by remember { mutableStateOf("") }
        var isKeyboardVisible by remember { mutableStateOf(false) }
        val systemKeyboardController = LocalSoftwareKeyboardController.current
        systemKeyboardController?.hide()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ChiliInputField(
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
            ) {
                inputText = it
            }
            if (isKeyboardVisible) {
                NumberKeyboard(
                    specialSymbols = listOf('-', '/'),
                    onInputChanged = { text ->
                        inputText = text
                })
            }
        }
    }
}