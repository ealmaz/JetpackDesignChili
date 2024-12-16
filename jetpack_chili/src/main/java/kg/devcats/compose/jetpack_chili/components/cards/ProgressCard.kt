package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.AnimatedProgressLine
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_2
import kg.devcats.compose.jetpack_chili.theme.gray_4
import kg.devcats.compose.jetpack_chili.theme.orange_1

@Composable
fun ProgressCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    progressPercent: Int,
    isProgressAnimated: Boolean = true,
    progressBackgroundColor: Color = gray_2,
    progressColor: Color = orange_1,
    progressGradientColors: List<Color>? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    descriptionStyle: TextStyle = Chili.typography.H14_Value,
    isDividerVisible: Boolean = false,
    isLoading: Boolean = false,
) {
    Surface(
        modifier = modifier,
        color = Chili.color.cardViewBackground,
        contentColor = Color.Unspecified
    ) {
        if (isLoading) {
            LoadingCard(modifier = Modifier.fillMaxWidth())
        } else {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = title,
                        style = titleStyle,
                    )

                    description?.let {
                        Text(
                            text = it,
                            style = descriptionStyle,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    AnimatedProgressLine(
                        progressPercent = progressPercent,
                        modifier = Modifier.fillMaxWidth(),
                        isProgressAnimated = isProgressAnimated,
                        progressBackgroundColor = progressBackgroundColor,
                        progressColor = progressColor,
                        progressGradientColors = progressGradientColors
                    )
                }

                if(isDividerVisible) {
                    HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter))
                }
            }
        }
    }
}

@Composable
private fun LoadingCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(12.dp)
    ) {
        Shimmer(
            modifier = Modifier
                .width(102.dp)
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Shimmer(
            modifier = Modifier
                .width(102.dp)
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Shimmer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressCellViewPreview() {
    Column(modifier = Modifier.fillMaxSize().background(gray_4).padding(16.dp)) {
        ProgressCard(
            modifier = Modifier.clip(Chili.shapes.RoundedTopCornerShape),
            title = "80 000,00 из 100 000 c",
            description = "Доступный объем счета",
            progressPercent = 80,
            isDividerVisible = true
        )

        ProgressCard(
            modifier = Modifier.clip(Chili.shapes.RoundedBottomCornerShape),
            title = "Loading...",
            isLoading = true,
            progressPercent = 0
        )
    }
}