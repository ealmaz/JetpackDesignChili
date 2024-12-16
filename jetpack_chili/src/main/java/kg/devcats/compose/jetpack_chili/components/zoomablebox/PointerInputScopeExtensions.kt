package kg.devcats.compose.jetpack_chili.components.zoomablebox

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.awaitVerticalTouchSlopOrCancellation
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.drag
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

internal suspend fun PointerInputScope.detectZoomableGestures(
    state: ZoomableState
): Unit = coroutineScope {
    launch(start = CoroutineStart.UNDISPATCHED) {
        detectTapGestures(
            onDoubleTap = { offset ->
                launch {
                    val isZooming = state.isZooming
                    val targetScale = if (isZooming) state.minScale else state.doubleTapScale
                    state.animateScaleTo(
                        targetScale = targetScale,
                        targetTranslation = if (isZooming) {
                            Offset.Zero
                        } else {
                            state.calculateTargetTranslation(offset) * targetScale
                        }
                    )
                }
            }
        )
    }
    launch(start = CoroutineStart.UNDISPATCHED) {
        detectTransformGestures(
            onGestureStart = { state.onGestureStart() },
            onGesture = { centroid, pan, zoom ->
                launch {
                    state.onTransform(centroid, pan, zoom)
                }
            },
            onGestureEnd = { state.onGestureEnd() }
        )
    }
    launch(start = CoroutineStart.UNDISPATCHED) {
        detectDragGestures(
            state = state,
            startDragImmediately = { state.isGestureInProgress },
            onDragStart = {
                state.onGestureStart()
            },
            onDrag = { change, dragAmount ->
                launch {
                    state.onDrag(dragAmount)
                }
            },
            onDragEnd = {
                state.onGestureEnd()
            }
        )
    }
}

private suspend fun PointerInputScope.detectTransformGestures(
    onGestureStart: () -> Unit = {},
    onGestureEnd: () -> Unit = {},
    onGesture: (centroid: Offset, pan: Offset, zoom: Float) -> Unit
) {
    awaitEachGesture {
        awaitTwoDowns(requireUnconsumed = false)
        onGestureStart()
        do {
            val event = awaitPointerEvent()
            val canceled = event.changes.fastAny { it.isConsumed }
            if (!canceled) {
                val zoomChange = event.calculateZoom()
                val panChange = event.calculatePan()
                val centroid = event.calculateCentroid()
                if (zoomChange != 1f || panChange != Offset.Zero) {
                    onGesture(centroid, panChange, zoomChange)
                }
                event.changes.fastForEach {
                    if (it.positionChanged()) {
                        it.consume()
                    }
                }
            }
        } while (!canceled && event.changes.fastAny { it.pressed })
        onGestureEnd()
    }
}

private suspend fun PointerInputScope.detectDragGestures(
    state: ZoomableState,
    startDragImmediately: () -> Boolean,
    onDragStart: (PointerInputChange) -> Unit = {},
    onDragEnd: () -> Unit = {},
    onDrag: (change: PointerInputChange, offset: Offset) -> Unit
) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        if (state.isZooming) {
            var offset = Offset.Zero
            val drag = if (state.isZooming) {
                if (startDragImmediately()) down else {
                    awaitTouchSlopOrCancellation(down.id) { change, over ->
                        change.consume()
                        offset = over
                    }
                }
            } else {
                awaitVerticalTouchSlopOrCancellation(down.id) { change, over ->
                    change.consume()
                    offset = Offset(0f, over)
                }
            }
            if (drag != null) {
                onDragStart(down)
                if (offset != Offset.Zero) onDrag(drag, offset)
                !drag(drag.id) {
                    onDrag(it, it.positionChange())
                    it.consume()
                }
                onDragEnd()
            }
        }
    }
}

private suspend fun AwaitPointerEventScope.awaitTwoDowns(requireUnconsumed: Boolean = true) {
    var event: PointerEvent
    var firstDown: PointerId? = null
    do {
        event = awaitPointerEvent()
        var downPointers = if (firstDown != null) 1 else 0
        event.changes.fastForEach {
            val isDown =
                if (requireUnconsumed) it.changedToDown() else it.changedToDownIgnoreConsumed()
            val isUp =
                if (requireUnconsumed) it.changedToUp() else it.changedToUpIgnoreConsumed()
            if (isUp && firstDown == it.id) {
                firstDown = null
                downPointers -= 1
            }
            if (isDown) {
                firstDown = it.id
                downPointers += 1
            }
        }
        val satisfied = downPointers > 1
    } while (!satisfied)
}