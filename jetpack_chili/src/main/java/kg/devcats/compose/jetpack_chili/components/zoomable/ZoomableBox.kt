package kg.devcats.compose.jetpack_chili.components.zoomable

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val MAX_ZOOM = 3f
private const val MIN_ZOOM = 1f

@Composable
fun ZoomableBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope. () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val scale = remember { Animatable(MIN_ZOOM) }

    BoxWithConstraints(modifier = modifier) {
        val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
            val extraWidth = (scale.value - 1) * constraints.maxWidth
            val extraHeight = (scale.value - 1) * constraints.maxHeight

            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            coroutineScope.launch {
                offsetX.snapTo((offsetX.value + scale.value * panChange.x).coerceIn(-maxX, maxX))
                offsetY.snapTo((offsetY.value + scale.value * panChange.y).coerceIn(-maxY, maxY))
                scale.snapTo((scale.value * zoomChange).coerceIn(MIN_ZOOM, MAX_ZOOM))
            }
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .clipToBounds()
                .graphicsLayer {
                    scaleX = scale.value; scaleY = scale.value
                    translationX = offsetX.value; translationY = offsetY.value
                }
                .transformable(state)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { tapOffset ->
                            val extraWidth = (scale.value - 1) * constraints.maxWidth
                            val extraHeight = (scale.value - 1) * constraints.maxHeight

                            val maxX = extraWidth / 2
                            val maxY = extraHeight / 2

                            if (scale.value > MIN_ZOOM) {
                                coroutineScope.launch {
                                    offsetX.animateTo(0f)
                                    offsetY.animateTo(0f)
                                    scale.animateTo(MIN_ZOOM)
                                }
                            } else {
                                coroutineScope.launch {
                                    scale.animateTo(MAX_ZOOM)
                                }
                            }
                        }
                    )
                },
            content = content
        )
    }
}