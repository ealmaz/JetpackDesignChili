package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ShadowRoundedBox(modifier: Modifier = Modifier, contentColor: Color = Chili.color.shadowContainerContent, content: @Composable () -> Unit) {
//    Surface(
//        modifier = modifier.advancedShadow(),
//        color = contentColor,
//        shape = Chili.shapes.RoundedCornerShape,
//    ) {
//        content.invoke()
//    }
    Surface(
        modifier = modifier,
        color = contentColor,
        shape = Chili.shapes.RoundedCornerShape,
        shadowElevation = Chili.attrs.shadowElevation
    ) {
        content.invoke()
    }
}

fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 0.08f,
    cornersRadius: Dp = 14.dp,
    shadowBlurRadius: Dp = 32.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}