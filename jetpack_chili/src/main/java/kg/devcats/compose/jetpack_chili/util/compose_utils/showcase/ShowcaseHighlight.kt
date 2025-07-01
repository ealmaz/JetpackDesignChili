package kg.devcats.compose.jetpack_chili.util.compose_utils.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ShowcaseHighlight(
    val cornerRadius: Dp = 8.dp,
    val targetMargin: Dp = 0.dp
) {
    @Composable
    fun create(targetCoordinates: LayoutCoordinates): HighlightProperties {
        val highlightBounds = createHighlightBounds(
            targetRect = targetCoordinates.boundsInRoot(),
            targetMargin = with(LocalDensity.current) { targetMargin.toPx() }
        )
        return HighlightProperties(
            drawHighlight = { rectangularHighlight(cornerRadius.toPx(), highlightBounds) },
            highlightBounds = highlightBounds
        )
    }

    private fun DrawScope.rectangularHighlight(
        cornerRadius: Float,
        highlightBounds: Rect
    ) {
        drawRoundRect(
            color = Color.White,
            size = highlightBounds.size,
            topLeft = highlightBounds.topLeft,
            blendMode = BlendMode.Clear,
            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
        )
    }

    private fun createHighlightBounds(
        targetRect: Rect,
        targetMargin: Float
    ): Rect {
        return Rect(
            top = targetRect.top - targetMargin,
            bottom = targetRect.bottom + targetMargin,
            left = targetRect.left - targetMargin,
            right = targetRect.right + targetMargin
        )
    }
}

class HighlightProperties internal constructor(
    val drawHighlight: DrawScope.(LayoutCoordinates) -> Unit,
    val highlightBounds: Rect
)