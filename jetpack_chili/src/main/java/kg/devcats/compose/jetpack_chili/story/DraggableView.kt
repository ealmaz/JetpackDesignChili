package kg.devcats.compose.jetpack_chili.story

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.IntOffset
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun VerticalDraggableView(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    var offsetY by remember { mutableFloatStateOf(0f) }
    val dragState = rememberDraggableState { delta ->
        offsetY = (offsetY + delta).coerceAtLeast(0f)
    }
    val scale by remember {
        derivedStateOf {
            1f - (offsetY / 10000f).coerceIn(0f, 0.2f)
        }
    }

    Box(
        modifier = modifier
            .offset { IntOffset(0, offsetY.roundToInt()) }
            .scale(scale)
            .draggable(
                orientation = Orientation.Vertical,
                state = dragState,
                onDragStopped = {
                    if (abs(offsetY) > 200) {
                        onDismiss()
                    } else {
                        offsetY = 0f
                    }
                }
            )
    ) {
        content()
    }
}

