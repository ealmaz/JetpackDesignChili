package kg.devcats.compose.jetpack_chili.components.zoomablebox

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun rememberZoomableState(
    minScale: Float = ZoomableDefaults.MIN_SCALE,
    maxScale: Float = ZoomableDefaults.MAX_SCALE,
    doubleTapScale: Float = ZoomableDefaults.DOUBLE_TAP_SCALE,
    initialScale: Float = minScale,
    initialTranslationX: Float = 0f,
    initialTranslationY: Float = 0f
) = remember {
    ZoomableState(initialScale, initialTranslationX, initialTranslationY)
        .apply {
            this.minScale = minScale
            this.maxScale = maxScale
            this.doubleTapScale = doubleTapScale
        }
}

@Stable
class ZoomableState(
    initialScale: Float = ZoomableDefaults.MIN_SCALE,
    initialTranslationX: Float = 0f,
    initialTranslationY: Float = 0f
) {
    var minScale: Float = ZoomableDefaults.MIN_SCALE
    var maxScale: Float = ZoomableDefaults.MAX_SCALE

    internal var doubleTapScale: Float = ZoomableDefaults.DOUBLE_TAP_SCALE

    internal var boundOffset by mutableStateOf(IntOffset.Zero)
        private set

    internal var dismissDragAbsoluteOffsetY by mutableStateOf(0f)
        private set

    internal var size: IntSize = IntSize.Zero
        set(value) {
            field = value
            updateBounds()
        }

    internal var childSize: Size = Size.Zero
        set(value) {
            field = value
            updateBounds()
        }

    internal var scale: Float = initialScale
        private set(value) {
            field = value.coerceIn(minimumValue = minScale, maximumValue = maxScale)
            updateBounds()
        }

    private var _translationX = Animatable(initialTranslationX)
    val translationX: Float
        get() = _translationX.value

    private var _translationY = Animatable(initialTranslationY)
    val translationY: Float
        get() = _translationY.value

    val isZooming: Boolean
        get() = scale > minScale && scale <= maxScale

    private var flingJob: Job? = null
    internal var isGestureInProgress: Boolean by mutableStateOf(false)
        private set

    private fun updateBounds() {
        if (childSize == Size.Zero) return
        val offsetX = childSize.width * scale - size.width
        val offsetY = childSize.height * scale - size.height
        boundOffset = IntOffset((offsetX / 2f).roundToInt(), (offsetY / 2f).roundToInt())
        val maxX = offsetX.coerceAtLeast(0f) / 2f
        val maxY = offsetY.coerceAtLeast(0f) / 2f
        _translationX.updateBounds(-maxX, maxX)
        _translationY.updateBounds(-maxY, maxY)
    }

    internal fun calculateTargetTranslation(centroid: Offset): Offset =
        (size.toSize().center + Offset(translationX, translationY) - centroid) / scale

    suspend fun animateScaleTo(
        targetScale: Float,
        targetTranslation: Offset = Offset(translationX, translationY) / scale * targetScale,
        animationSpec: AnimationSpec<Float> = spring()
    ) = coroutineScope {
        val initialTranslation = Offset(translationX, translationY)
        val initialScale = scale
        val range = targetScale - initialScale
        animate(
            initialValue = initialScale,
            targetValue = targetScale,
            animationSpec = animationSpec
        ) { value, _ ->
            launch {
                scale = value
                if (targetTranslation != Offset.Unspecified) {
                    val fraction = if (range == 0f) 1f else (value - initialScale) / range
                    val translation = lerp(initialTranslation, targetTranslation, fraction)
                    _translationX.snapTo(translation.x)
                    _translationY.snapTo(translation.y)
                }
            }
        }
    }

    internal fun onGestureStart() {
        flingJob?.cancel()
        isGestureInProgress = true
    }

    internal suspend fun onTransform(centroid: Offset, pan: Offset, zoom: Float) {
        var targetTranslation = calculateTargetTranslation(centroid - pan)
        scale *= zoom
        targetTranslation = targetTranslation * scale - size.toSize().center + centroid
        _translationX.snapTo(targetTranslation.x)
        _translationY.snapTo(targetTranslation.y)
    }

    internal fun onTransformEnd() {
        isGestureInProgress = false
    }
}