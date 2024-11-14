package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PieChartCardView(
    modifier: Modifier = Modifier,
    title: String,
    data: List<Float>,
    colors: List<Color>,
    pieChartTitle: String = "",
    pieChartSubtitle: String = "",
    onNextClick: (() -> Unit)? = null,
    onPreviousClick: (() -> Unit)? = null,
    isSinglePieData: Boolean? = false
) {
    Surface(
        color = Chili.color.cardViewBackground,
        contentColor = Color.Unspecified,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = Chili.typography.H14_Value
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chili_ic_chevron_left),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        onPreviousClick?.invoke()
                    }
                )
                PieChart(
                    data = data,
                    colors = colors,
                    title = pieChartTitle,
                    subtitle = pieChartSubtitle,
                    modifier = Modifier.size(180.dp).padding(16.dp),
                    isSinglePieData = isSinglePieData
                )
                Image(
                    painter = painterResource(id = R.drawable.chili_ic_chevron_right),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        onNextClick?.invoke()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            PieChartCardView(
                title = "Детализация на 06.06.2020",
                data = listOf(40f, 30f, 20f, 10f),
                colors = listOf(
                    Color(0xFFE91E63),
                    Color(0xFF4CAF50),
                    Color(0xFFFFEB3B),
                    Color(0xFFF57F17)
                ),
                pieChartTitle = "Все расходы",
                pieChartSubtitle = "140,00 с",
            )
        }
    }

}