package kg.devcats.compose.jetpack_chili.components.cards
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatedGradientBackground(
    modifier: Modifier = Modifier,
    isAnimating: Boolean = true,
    animatedColors: List<Color> = listOf(
        Chili.color.animatedGradient1,
        Chili.color.animatedGradient2,
        Chili.color.animatedGradient3,
        Chili.color.animatedGradient4
    ),
) {
    val colors = if (isAnimating) animatedColors else listOf(
        Chili.color.cardViewBackground,
        Chili.color.cardViewBackground,
        Chili.color.cardViewBackground,
        Chili.color.cardViewBackground
    )

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val currentAngle = if (isAnimating) angle else 0f

    Canvas(modifier = modifier) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radius = (size.minDimension / 2f) * 0.9f

        val startX = centerX + (radius * cos(Math.toRadians(currentAngle.toDouble()))).toFloat()
        val startY = centerY + (radius * sin(Math.toRadians(currentAngle.toDouble()))).toFloat()

        val endX = centerX + (radius * cos(Math.toRadians((currentAngle + 180).toDouble()))).toFloat()
        val endY = centerY + (radius * sin(Math.toRadians((currentAngle + 180).toDouble()))).toFloat()

        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            tileMode = TileMode.Clamp
        )

        drawRect(brush = brush, size = size)
    }
}

