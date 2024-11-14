package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.ShimmerView
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.R

@Composable
fun BorderAnimatedView(
    modifier: Modifier = Modifier,
    title: String,
    commissionInfo: String,
    commissionValue: String,
    icon: Painter,
    isAnimating: Boolean = true,
    isLoading: Boolean = false
) {
    if (isLoading) {
        Surface(
            color = Chili.color.cardViewBackground,
            contentColor = Color.Unspecified,
            modifier = modifier
                .padding(12.dp),
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerView(height = 32.dp, width = 32.dp)
                    ShimmerView(
                        modifier = Modifier.padding(start = 8.dp),
                        height = 8.dp,
                        width = 105.dp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerView(
                        modifier = Modifier.padding(top = 14.dp, bottom = 4.dp, end = 5.dp),
                        height = 8.dp,
                        width = 105.dp
                    )
                    ShimmerView(height = 20.dp, width = 38.dp)
                }
            }
        }
    } else {
        Box(
            modifier = modifier
                .size(172.dp, 80.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AnimatedGradientBackground(modifier = Modifier.fillMaxSize(), isAnimating)

            Surface(
                color = Chili.color.cardViewBackground,
                contentColor = Color.Unspecified,
                modifier = modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = title,
                            style = Chili.typography.H14_Primary_500,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = commissionInfo,
                            style = Chili.typography.H12_Secondary,
                        )
                        Surface(
                            color = Chili.color.buttonAdditionalContainer,
                            contentColor = Color.Unspecified,
                            modifier = modifier
                                .clip(RoundedCornerShape(8.dp)),
                        ) {
                            Text(

                                modifier = Modifier.padding(4.dp, 3.dp, 4.dp, 3.dp),
                                text = commissionValue,
                                style = Chili.typography.H12_Primary,
                            )
                        }

                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BorderAnimatedCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BorderAnimatedView(
                title = "Title",
                commissionInfo = "13",
                commissionValue = "13%",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BorderAnimatedView(
                title = "Title",
                commissionInfo = "13",
                commissionValue = "13%",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }
    }
}