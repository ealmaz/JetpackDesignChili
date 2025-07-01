package kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.ShowcaseHighlight

@Composable
fun rememberSequenceShowcaseState(): SequenceShowcaseState = remember { SequenceShowcaseState() }

class SequenceShowcaseState {
    internal var targets = mutableStateMapOf<Int, SequenceShowcaseTarget>()

    var currentTargetIndex by mutableIntStateOf(0)
        private set

    val currentTarget: SequenceShowcaseTarget?
        get() = targets[currentTargetIndex]

    var showCaseVisible by mutableStateOf(false)
        private set

    private var isTransitioning = false

    fun start(index: Int = 0) {
        currentTargetIndex = index
        showCaseVisible = true
    }

    fun next() {
        isTransitioning = true
        showCaseVisible = false
    }

    fun dismiss() {
        showCaseVisible = false
        isTransitioning = false
    }

    fun isFinalStep(): Boolean = currentTargetIndex >= targets.size - 1

    internal fun onShowcaseViewAppear() {
        if (isTransitioning) {
            isTransitioning = false
        }
    }

    internal fun onShowcaseViewDisappear() {
        if (isTransitioning) {
            currentTargetIndex++
            showCaseVisible = currentTarget != null
        }
    }
}

data class SequenceShowcaseTarget(
    val index: Int,
    val closeOnTouch: Boolean,
    val coordinates: LayoutCoordinates,
    val position: ShowcasePosition,
    val alignment: ShowcaseAlignment,
    val highlight: ShowcaseHighlight,
    val duration: AnimationDuration,
    val backgroundAlpha: BackgroundAlpha,
    val dialog: @Composable (Rect) -> Unit
)

data class AnimationDuration internal constructor(val enterMillis: Int, val exitMillis: Int) {
    companion object {
        private const val DEFAULT_MILLIS = 700

        val Default = AnimationDuration(DEFAULT_MILLIS, DEFAULT_MILLIS)
        val Fast = AnimationDuration(100, 200)
        val Slow = AnimationDuration(1000, 1000)

        fun create(durationMillis: Int) = AnimationDuration(durationMillis, durationMillis)
        fun create(enterMillis: Int, exitMillis: Int) = AnimationDuration(enterMillis, exitMillis)
    }
}

data class BackgroundAlpha internal constructor(val value: Float) {
    companion object {
        val Light = BackgroundAlpha(0.3f)
        val Normal = BackgroundAlpha(0.6f)
        val Dark = BackgroundAlpha(0.9f)

        fun create(alpha: Float) = BackgroundAlpha(alpha)
    }
}

enum class ShowcaseAlignment {
    START,
    END,
    CENTER_HORIZONTAL,
    DEFAULT
}

enum class ShowcaseDisplayState {
    APPEARED,
    DISAPPEARED
}

enum class ShowcasePosition {
    TOP,
    BOTTOM,
    DEFAULT
}