package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.gray_2
import kg.devcats.compose.jetpack_chili.theme.orange_1

@Composable
fun AnimatedProgressLine(
    progressPercent: Int,
    modifier: Modifier = Modifier,
    backgroundHeight: Dp = 8.dp,
    trackHeight: Dp = 8.dp,
    isProgressAnimated: Boolean = true,
    progressBackgroundColor: Color = gray_2,
    progressColor: Color = orange_1,
    progressGradientColors: List<Color>? = null,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progressPercent.coerceIn(0, 100) / 100f,
        animationSpec = if (isProgressAnimated) tween(1000) else snap(), label = "progress"
    )

    val progressBrush = remember(progressGradientColors) {
        progressGradientColors?.let {
            Brush.linearGradient(colors = it)
        }
    }

    Canvas(modifier = modifier.height(trackHeight)) {
        val cornerRadius = trackHeight.toPx() / 2

        drawRoundRect(
            color = progressBackgroundColor,
            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            size = size.copy(height = backgroundHeight.toPx())
        )

        if (animatedProgress > 0f) {
            drawRoundRect(
                brush = progressBrush ?: SolidColor(progressColor),
                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                size = size.copy(width = size.width * animatedProgress, height = trackHeight.toPx())
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedProgressLinePreview() {
    var progress by remember { mutableIntStateOf(20) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedProgressLine(
            progressPercent = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { progress = 20 }) {
                Text("Set to 20%")
            }
            Button(onClick = { progress = 80 }) {
                Text("Set to 80%")
            }
        }
    }
}