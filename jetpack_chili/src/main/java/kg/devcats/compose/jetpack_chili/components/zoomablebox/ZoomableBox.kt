package kg.devcats.compose.jetpack_chili.components.zoomablebox

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize

@Composable
fun ZoomableBox(
    modifier: Modifier = Modifier,
    state: ZoomableState = rememberZoomableState(),
    enabled: Boolean = true,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    val gesturesModifier = if (!enabled) Modifier else {
        Modifier.pointerInput(state) {
            detectZoomableGestures(
                state = state
            )
        }
    }

    Box(
        modifier = modifier
            .then(gesturesModifier)
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val measuredSize = IntSize(placeable.measuredWidth, placeable.measuredHeight).toSize()
                state.setLayoutSize(measuredSize)
                layout(placeable.width, placeable.height) {
                    placeable.placeWithLayer(x = 0, y = 0) {
                        scaleX = state.scale
                        scaleY = state.scale
                        translationX = state.translationX
                        translationY = state.translationY
                    }
                }
            },
        contentAlignment = contentAlignment,
        content = content
    )
}