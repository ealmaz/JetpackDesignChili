package kg.devcats.compose.jetpack_chili.components.keyboards

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    specialSymbols: List<Char> = emptyList(),
    isKeyboardVisible: Boolean = false,
    content: @Composable () -> Unit,
) {

    var inputConnection by remember { mutableStateOf<InputConnection?>(null) }

    InterceptPlatformTextInput(
        interceptor = { request, nextHandler ->
            inputConnection = request.createInputConnection(EditorInfo())
            awaitCancellation()
        }
    ) {

        Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
            content.invoke()
            if (isKeyboardVisible) {
                NumberKeyboard(
                    modifier = Modifier,
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
    }
}