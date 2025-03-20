package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.lerp
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

@Composable
fun AnimatedSweepGradientBackground(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    animationEnabled: Boolean = true,
    animationDuration: Int = 3000
) {
    val limitedColors = if (colors.size > 3) colors.take(3) else colors

    val smoothStops = remember(limitedColors) { generateSmoothStops(limitedColors) }
    val extendedStops =
        remember(smoothStops) { smoothStops.map { it.first } + smoothStops.map { it.first + 1f } }
    val extendedColors =
        remember(smoothStops) { smoothStops.map { it.second } + smoothStops.map { it.second } }

    val rotationAngle by if (animationEnabled) {
        rememberInfiniteTransition(label = "").animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = animationDuration, easing = LinearEasing)
            ), label = ""
        )
    } else {
        remember { mutableFloatStateOf(0f) }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val center = center
        val offset = rotationAngle / 360f

        val filtered = extendedStops.zip(extendedColors)
            .filter { (stop, _) -> stop in offset..(offset + 1f) }
            .sortedBy { it.first }

        val adjusted = filtered.map { (stop, color) ->
            (stop - offset) to color
        }.toTypedArray()

        val sweepBrush = Brush.sweepGradient(center = center, colorStops = adjusted)

        drawRect(
            brush = sweepBrush,
            size = size
        )
    }
}

private fun generateSmoothStops(
    colors: List<Color>,
    sampleCountPerSegment: Int = 10
): List<Pair<Float, Color>> {
    val closedColors = colors + colors.first()
    val n = closedColors.size - 1
    val result = mutableListOf<Pair<Float, Color>>()
    for (segment in 0 until n) {
        for (j in 0 until sampleCountPerSegment) {
            val t = j.toFloat() / sampleCountPerSegment
            val interpolated = lerp(closedColors[segment], closedColors[segment + 1], t)
            val stop = (segment + t) / n
            result.add(stop to interpolated)
        }
    }
    result.add(1f to closedColors.last())
    return result
}