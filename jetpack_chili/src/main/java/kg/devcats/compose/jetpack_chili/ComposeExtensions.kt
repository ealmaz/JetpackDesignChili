package kg.devcats.compose.jetpack_chili

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.setSurfaceClick(
    condition: Boolean = true,
    coroutineScope: CoroutineScope,
    onPressedState: () -> Unit,
    onDefaultState: () -> Unit
): Modifier {
    return this.pointerInput(condition) {
        coroutineScope.launch {
            while (condition) {
                val event = awaitPointerEventScope { awaitPointerEvent() }
                when (event.type) {
                    androidx.compose.ui.input.pointer.PointerEventType.Press -> {
                        onPressedState.invoke()
                    }

                    else -> {
                        onDefaultState.invoke()
                    }
                }
            }
        }
    }
}

sealed class SurfaceClickActionState {
    data object Pressed : SurfaceClickActionState()
    data object Default : SurfaceClickActionState()
}