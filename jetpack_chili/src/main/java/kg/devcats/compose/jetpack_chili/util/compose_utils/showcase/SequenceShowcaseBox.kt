package kg.devcats.compose.jetpack_chili.util.compose_utils.showcase

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.AnimationDuration
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.BackgroundAlpha
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.SequenceShowcaseState
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcaseDisplayState
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.rememberSequenceShowcaseState
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.SequenceShowcaseTarget
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcaseAlignment
import kg.devcats.compose.jetpack_chili.util.compose_utils.showcase.state.ShowcasePosition

@Composable
fun SequenceShowcaseBox(
    modifier: Modifier = Modifier,
    state: SequenceShowcaseState = rememberSequenceShowcaseState(),
    content: @Composable SequenceShowcaseScope.() -> Unit
) {
    val scope = remember(state) { SequenceShowcaseScope(state) }

    Box(modifier = modifier.fillMaxWidth()) {
        scope.content()

        state.currentTarget?.let { target ->
            ShowcaseStep(
                visible = state.showCaseVisible,
                isFinalStep = state::isFinalStep,
                target = target,
                detectTouchScreen = { if (target.closeOnTouch) state.next() },
                onDisplayStateChanged = { displayState ->
                    when (displayState) {
                        ShowcaseDisplayState.APPEARED -> state.onShowcaseViewAppear()
                        ShowcaseDisplayState.DISAPPEARED -> state.onShowcaseViewDisappear()
                    }
                }
            )
        }
    }
}

class SequenceShowcaseScope(
    private val state: SequenceShowcaseState,
) {
    fun Modifier.sequenceShowcaseTarget(
        index: Int,
        closeOnTouch: Boolean = true,
        position: ShowcasePosition = ShowcasePosition.DEFAULT,
        alignment: ShowcaseAlignment = ShowcaseAlignment.DEFAULT,
        animationDuration: AnimationDuration = AnimationDuration.Fast,
        backgroundAlpha: BackgroundAlpha = BackgroundAlpha.Dark,
        highlight: ShowcaseHighlight = ShowcaseHighlight(),
        dialog: @Composable (Rect) -> Unit,
    ): Modifier = onGloballyPositioned { coordinates ->
        state.targets[index] = SequenceShowcaseTarget(
            index = index,
            closeOnTouch = closeOnTouch,
            coordinates = coordinates,
            position = position,
            alignment = alignment,
            highlight = highlight,
            duration = animationDuration,
            backgroundAlpha = backgroundAlpha,
            dialog = dialog
        )
    }
}