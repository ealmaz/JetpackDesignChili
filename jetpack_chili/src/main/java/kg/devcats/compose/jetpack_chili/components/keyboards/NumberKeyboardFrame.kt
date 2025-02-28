package kg.devcats.compose.jetpack_chili.components.keyboards

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.InterceptPlatformTextInput
import kotlinx.coroutines.awaitCancellation

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberKeyboardFrame(
    modifier: Modifier = Modifier,
    keyboardModifier: Modifier = Modifier,
    specialSymbols: List<Char> = emptyList(),
    isKeyboardVisible: Boolean = false,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {

    var inputConnection by remember { mutableStateOf<InputConnection?>(null) }

    InterceptPlatformTextInput(
        interceptor = { request, nextHandler ->
            inputConnection = request.createInputConnection(EditorInfo())
            awaitCancellation()
        }
    ) {

        Scaffold(
            modifier = modifier,
            snackbarHost = snackbarHost,
            content = {
                content.invoke(it)
            },
            topBar = topBar,
            bottomBar = {
                if (isKeyboardVisible) {
                    NumberKeyboard(
                        modifier = keyboardModifier
                            .wrapContentSize(),
                        specialSymbols = specialSymbols
                    ) {
                        when (it) {
                            KeyboardKeyType.Backspace -> {
                                inputConnection?.deleteSurroundingText(1, 0)
                            }

                            is KeyboardKeyType.Symbol -> {
                                inputConnection?.commitText(it.value.toString(), 1)
                            }

                            else -> {}
                        }
                    }
                }
            }
        )
    }
}