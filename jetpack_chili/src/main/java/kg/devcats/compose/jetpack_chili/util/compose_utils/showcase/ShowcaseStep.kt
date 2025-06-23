package kg.devcats.compose.jetpack_chili.util.compose_utils.showcase

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.BackgroundAlpha
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.SequenceShowcaseTarget
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcaseAlignment
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcaseDisplayState
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcasePosition

@Composable
fun ShowcaseStep(
    modifier: Modifier = Modifier,
    visible: Boolean,
    target: SequenceShowcaseTarget,
    detectTouchScreen: () -> Unit,
    onDisplayStateChanged: (ShowcaseDisplayState) -> Unit = {}
) {
    val transition =  remember { MutableTransitionState(false) }
    val highlightDrawer = target.highlight.create(targetCoordinates = target.coordinates)

    AnimatedVisibility(
        visibleState = transition,
        enter = fadeIn(tween(target.duration.enterMillis)),
        exit = fadeOut(tween(target.duration.exitMillis))
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) { detectTapGestures { detectTouchScreen.invoke() } }
        ) {
            ShowcaseBackground(
                coordinates = target.coordinates,
                drawHighlight = highlightDrawer.drawHighlight,
                backgroundAlpha = target.backgroundAlpha
            )
            ShowcaseDialog(
                targetRect = target.coordinates.boundsInRoot(),
                position = target.position,
                alignment = target.alignment,
                highlightBounds = highlightDrawer.highlightBounds,
                content = target.dialog
            )
        }
    }
    LaunchedEffect(key1 = visible) {
        transition.targetState = visible
    }
    LaunchedEffect(key1 = transition.isIdle) {
        if (transition.isIdle) {
            if (transition.targetState) {
                onDisplayStateChanged(ShowcaseDisplayState.APPEARED)
            } else {
                onDisplayStateChanged(ShowcaseDisplayState.DISAPPEARED)
            }
        }
    }
}

@Composable
private fun ShowcaseBackground(
    modifier: Modifier = Modifier,
    coordinates: LayoutCoordinates,
    backgroundAlpha: BackgroundAlpha,
    drawHighlight: DrawScope.(LayoutCoordinates) -> Unit
) {
    val targetRadius = remember { mutableFloatStateOf(0f) }
    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius.floatValue,
        animationSpec = tween(1000)
    )

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(alpha = backgroundAlpha.value)
    ) {
        targetRadius.floatValue = size.height
        drawCircle(
            color = Color.Black.copy(alpha = backgroundAlpha.value),
            radius = animatedRadius,
            center = coordinates.boundsInRoot().center
        )
        drawHighlight(coordinates)
    }
}

@Composable
private fun ShowcaseDialog(
    modifier: Modifier = Modifier,
    targetRect: Rect,
    position: ShowcasePosition,
    alignment: ShowcaseAlignment,
    highlightBounds: Rect,
    content: @Composable (Rect) -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val verticalSpacerPx = with(density) { 16.dp.toPx() }

    Box(
        modifier = modifier
            .offset(x = offsetX.toDp(), y = offsetY.toDp())
            .onGloballyPositioned {
                val dialogHeight = it.size.height
                val dialogWidth = it.size.width
                val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
                val screenWidth = with(density) { configuration.screenWidthDp.dp.toPx() }
                val highlightCenterX = highlightBounds.center.x

                offsetX = when (alignment) {
                    ShowcaseAlignment.START -> highlightBounds.left
                    ShowcaseAlignment.END -> highlightBounds.right - dialogWidth
                    ShowcaseAlignment.CENTER_HORIZONTAL -> (highlightCenterX - dialogWidth / 2)
                    ShowcaseAlignment.DEFAULT -> {
                        if (highlightCenterX > screenWidth / 2) {
                            highlightBounds.right - dialogWidth
                        } else {
                            highlightBounds.left
                        }
                    }
                }

                offsetY = when (position) {
                    ShowcasePosition.TOP -> highlightBounds.top - verticalSpacerPx - dialogHeight
                    ShowcasePosition.BOTTOM -> highlightBounds.bottom + verticalSpacerPx
                    ShowcasePosition.DEFAULT -> {
                        if (targetRect.center.y > screenHeight / 2 + verticalSpacerPx) {
                            highlightBounds.top - verticalSpacerPx - dialogHeight
                        } else {
                            highlightBounds.bottom + verticalSpacerPx
                        }
                    }
                }
            },
        content = { content(highlightBounds) }
    )
}

@Composable
fun Float.toDp() = with(LocalDensity.current) { toDp() }