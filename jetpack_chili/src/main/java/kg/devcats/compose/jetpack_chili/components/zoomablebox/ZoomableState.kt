package kg.devcats.compose.jetpack_chili.components.zoomablebox

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.lerp
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.manager.PdfBitmapConverter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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

    internal var layoutSize: Size = Size.Zero
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

    internal var isGestureInProgress: Boolean = false
        private set

    fun setLayoutSize(size: Size) {
        layoutSize = size
    }

    suspend fun onDrag(dragAmount: Offset) {
        _translationX.snapTo(_translationX.value + dragAmount.x)
        _translationY.snapTo(_translationY.value + dragAmount.y)
    }

    private fun updateBounds() {
        if (layoutSize == Size.Zero) return
        val offsetX = layoutSize.width * scale - layoutSize.width
        val offsetY = layoutSize.height * scale - layoutSize.height
        val maxX = offsetX.coerceAtLeast(0f) / PdfBitmapConverter.BITMAP_SCALE
        val maxY = offsetY.coerceAtLeast(0f) / PdfBitmapConverter.BITMAP_SCALE
        _translationX.updateBounds(-maxX, maxX)
        _translationY.updateBounds(-maxY, maxY)
    }

    internal fun calculateTargetTranslation(centroid: Offset): Offset =
        (layoutSize.center + Offset(translationX, translationY) - centroid) / scale

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
        isGestureInProgress = true
    }

    internal suspend fun onTransform(centroid: Offset, pan: Offset, zoom: Float) {
        var targetTranslation = calculateTargetTranslation(centroid - pan)
        scale *= zoom
        targetTranslation = targetTranslation * scale - layoutSize.center + centroid
        _translationX.snapTo(targetTranslation.x)
        _translationY.snapTo(targetTranslation.y)
    }

    internal fun onGestureEnd() {
        isGestureInProgress = false
    }
}