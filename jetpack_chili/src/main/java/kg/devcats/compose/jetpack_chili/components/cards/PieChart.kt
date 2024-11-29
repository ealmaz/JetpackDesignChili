package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: List<Float>,
    colors: List<Color>,
    title: String,
    subtitle: String,
    isSinglePieData: Boolean? = false
) {
    val total = data.sum()
    val proportions = data.map { it * 100 / total }
    val sweepAngles = proportions.map { 360 * it / 100 }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            var startAngle = 0f
            val strokeWidth = size.minDimension / 10

            if (isSinglePieData == true) {
                sweepAngles.reversed().forEachIndexed { index, sweepAngle ->
                    drawArc(
                        color = colors.reversed()[index],
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    )
                    startAngle += sweepAngle
                }
            } else {
                sweepAngles.forEachIndexed { index, sweepAngle ->
                    drawArc(
                        color = colors[index],
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    )

                    val endAngle = startAngle + sweepAngle
                    val radius = size.minDimension / 2
                    val endX = radius + radius * cos(Math.toRadians(endAngle.toDouble())).toFloat()
                    val endY = radius + radius * sin(Math.toRadians(endAngle.toDouble())).toFloat()

                    drawCircle(
                        color = colors[0],
                        radius = strokeWidth / 2,
                        center = Offset(endX, endY)
                    )

                    startAngle += sweepAngle
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = Chili.typography.H14_Primary,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                style = Chili.typography.H16_Primary_500,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            PieChart(
                data = listOf(40f, 30f, 20f, 10f),
                colors = listOf(
                    Color(0xFFE91E63),
                    Color(0xFF4CAF50),
                    Color(0xFFFFEB3B),
                    Color(0xFFF57F17)
                ),
                title = "Все расходы",
                subtitle = "140,00 с",
                modifier = Modifier
                    .size(180.dp)
                    .padding(16.dp)
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            PieChart(
                data = listOf(1000f, 4000f, 100f),
                colors = listOf(
                    Color(0xFFE91E63),
                    Color(0xFFececec),
                    Color(0xFFececec),
                ),
                title = "Все расходы",
                subtitle = "140,00 с",
                modifier = Modifier
                    .size(180.dp)
                    .padding(16.dp),
                isSinglePieData = true
            )
        }
    }
}