package kg.devcats.compose.jetpack_chili.util.compose_utils.showcase

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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
import kg.devcats.compose.jetpack_chili.clickableWithoutEffect
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.BackgroundAlpha
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.SequenceShowcaseTarget
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcaseAlignment
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcaseDisplayState
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcasePosition

@Composable
fun ShowcaseStep(
    modifier: Modifier = Modifier,
    visible: Boolean,
    isFinalStep: () -> Boolean,
    target: SequenceShowcaseTarget,
    detectTouchScreen: () -> Unit,
    onDisplayStateChanged: (ShowcaseDisplayState) -> Unit = {}
) {
    val transition =  remember { MutableTransitionState(false) }
    val highlightDrawer = target.highlight.create(targetCoordinates = target.coordinates)

    Box(modifier = modifier) {
        ShowcaseBackground(
            detectTouchScreen = detectTouchScreen,
            visibleState = transition,
            isFinalStep = isFinalStep,
            coordinates = target.coordinates,
            drawHighlight = highlightDrawer.drawHighlight,
            backgroundAlpha = target.backgroundAlpha
        )
        ShowcaseDialog(
            visibleState = transition,
            targetRect = target.coordinates.boundsInRoot(),
            position = target.position,
            alignment = target.alignment,
            highlightBounds = highlightDrawer.highlightBounds,
            content = target.dialog
        )
    }

    LaunchedEffect(key1 = visible) { transition.targetState = visible }
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
    detectTouchScreen: () -> Unit,
    visibleState: MutableTransitionState<Boolean>,
    isFinalStep: () -> Boolean,
    coordinates: LayoutCoordinates,
    backgroundAlpha: BackgroundAlpha,
    drawHighlight: DrawScope.(LayoutCoordinates) -> Unit
) {
    val density = LocalDensity.current
    val screenWidth = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val center = coordinates.boundsInRoot().center

    val maxDistanceToCorner = remember(center, screenWidth, screenHeight) {
        val corners = listOf(
            Offset(0f, 0f),
            Offset(screenWidth, 0f),
            Offset(0f, screenHeight),
            Offset(screenWidth, screenHeight)
        )
        corners.maxOf { corner -> center.minus(corner).getDistance() }
    }

    var targetRadius by remember(visibleState.targetState, maxDistanceToCorner) {
        mutableFloatStateOf(if (visibleState.targetState) maxDistanceToCorner else 0f)
    }

    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = tween(durationMillis = 500),
        finishedListener = { if (it <= 0f) detectTouchScreen() }
    )

    if (animatedRadius > 0f) {
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .graphicsLayer(alpha = backgroundAlpha.value)
                .clickableWithoutEffect {
                    if (isFinalStep()) { targetRadius = 0f }
                    else { detectTouchScreen() }
                }
        ) {
            drawCircle(
                color = Color.Black.copy(alpha = backgroundAlpha.value),
                radius = animatedRadius,
                center = center
            )
            drawHighlight(coordinates)
        }
    }
}

@Composable
private fun ShowcaseDialog(
    modifier: Modifier = Modifier,
    visibleState: MutableTransitionState<Boolean>,
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
        content = { if (visibleState.targetState) content(highlightBounds) }
    )
}

@Composable
fun Float.toDp() = with(LocalDensity.current) { toDp() }