package kg.devcats.compose.jetpack_chili.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.ChiliChevron
import kg.devcats.compose.jetpack_chili.components.shimmer.ShimmerView
import kg.devcats.compose.jetpack_chili.components.shimmer.shimmerBrush
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_2
import kg.devcats.compose.jetpack_chili.theme.magenta_1
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun PromoBannerCardView(
    modifier: Modifier = Modifier,
    title: String = "",
    subTitle: String? = null,
    cardReadyText: String? = null,
    titleTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    subTitleTextStyle: TextStyle = Chili.typography.H12_Primary,
    @DrawableRes icon: Int? = null,
    @DrawableRes rightImage: Int,
    showCardReadyText: Boolean = false,
    gradientColors: List<Color> = emptyList(),
    isLoading: Boolean? = false
) {
        Box(
            modifier = modifier.background(
                brush = Brush.linearGradient(
                    colors = gradientColors
                ),
                shape = Chili.shapes.RoundedCornerShape
            )
        ) {
            if (isLoading == true) {
                Column(modifier = Modifier.fillMaxWidth().height(80.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                    ShimmerView(
                        modifier = Modifier.padding(start = 12.dp), height = 8.dp, width = 150.dp
                    )

                    ShimmerView(
                        modifier = Modifier.padding(start = 12.dp), height = 8.dp, width = 200.dp
                    )
                }
            } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BannerContent(
                    modifier = Modifier.padding(start = 12.dp),
                    title = title,
                    subtitle = subTitle,
                    cardReadyText = cardReadyText,
                    titleTextStyle = titleTextStyle,
                    subTitleTextStyle = subTitleTextStyle,
                    icon = icon,
                    showCardReadyText = showCardReadyText
                )
                RightImage(modifier = Modifier, rightImage)
            }
        }
    }
}

@Composable
private fun BannerContent(
    modifier: Modifier,
    title: String = "",
    subtitle: String? = null,
    cardReadyText: String? = null,
    titleTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    subTitleTextStyle: TextStyle = Chili.typography.H12_Primary,
    @DrawableRes icon: Int?,
    showCardReadyText: Boolean = false,
) {
    Column(modifier = modifier.height(80.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = title,
                style = titleTextStyle
            )
        }
        if (showCardReadyText && cardReadyText != null) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = white_1)
                    .background(shimmerBrush(colors = listOf(
                        Color.LightGray.copy(alpha = 0.6f),
                        Color.LightGray.copy(alpha = 0.0f),
                        Color.LightGray.copy(alpha = 0.6f)
                    )))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                text = cardReadyText,
                style = subTitleTextStyle.copy(color = magenta_1)
            )
        } else {
            subtitle?.let {
                Text(
                    modifier = Modifier,
                    text = it,
                    style = subTitleTextStyle
                )
            }
        }
    }
}

@Composable
private fun RightImage(modifier: Modifier, @DrawableRes rightImage: Int) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterEnd) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(rightImage),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        ChiliChevron(tint = gray_2)
    }
}
