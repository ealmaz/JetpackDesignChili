package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.components.shimmer.ShimmerView
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.orange_1
import kg.devcats.compose.jetpack_chili.theme.orange_1_alpha_10
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun ProductCardView(
    modifier: Modifier = Modifier,
    imageLink: String = "",
    imageContentScale: ContentScale = ContentScale.Crop,
    price: String,
    installmentPrice: String? = null,
    description: String = "",
    discountPrice: String? = null,
    discountBackgroundColor: String? = null,
    isLoading: Boolean = false
) {
    Column(
        modifier = modifier.background(
            Chili.color.cardViewBackground,
            Chili.shapes.RoundedCornerShape
        )
    ) {
        CardImage(
            modifier = Modifier
                .clip(Chili.shapes.RoundedCornerShape)
                .weight(72f),
            imageLink = imageLink,
            imageContentScale = imageContentScale,
            discountPrice = discountPrice,
            discountBackgroundColor = discountBackgroundColor,
            isLoading = isLoading
        )
        ProductContent(
            modifier = Modifier.weight(28f),
            price = price,
            installmentPrice = installmentPrice,
            description = description,
            isLoading = isLoading
        )
    }
}

@Composable
private fun CardImage(
    modifier: Modifier = Modifier,
    imageLink: String = "",
    imageContentScale: ContentScale = ContentScale.Crop,
    discountPrice: String?,
    discountBackgroundColor: String? = null,
    isLoading: Boolean = false
) {
    if (isLoading) {
        ShimmerView(
            modifier = modifier
                .height(210.dp)
                .fillMaxWidth()
        )
    } else {
        Box(
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = imageLink,
                contentScale = imageContentScale,
                modifier = modifier
                    .height(210.dp)
                    .fillMaxWidth(),
                contentDescription = null
            )
            if (discountPrice != null && discountBackgroundColor != null) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 7.dp)
                        .background(
                            color = parseColor(discountBackgroundColor),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 2.dp, horizontal = 6.dp),
                    text = discountPrice,
                    style = Chili.typography.H12_Primary_500.copy(color = white_1)
                )
            }
        }
    }
}

private fun parseColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}

@Composable
private fun ProductContent(
    modifier: Modifier = Modifier,
    price: String,
    installmentPrice: String?,
    description: String,
    isLoading: Boolean = false,
) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        if (isLoading) {
            ShimmerView(
                modifier = Modifier.padding(top = 12.dp), height = 6.dp, width = 100.dp
            )
            ShimmerView(
                modifier = Modifier.padding(top = 16.dp), height = 6.dp, width = 122.dp
            )
            ShimmerView(
                modifier = Modifier.padding(top = 8.dp), height = 6.dp, width = 100.dp
            )
        } else {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = price,
                style = Chili.typography.H14_Primary_700
            )
            installmentPrice?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .background(
                            color = orange_1_alpha_10,
                            shape = Chili.shapes.RoundedCornerShape
                        )
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                    text = it,
                    style = Chili.typography.H10_Primary_500.copy(color = orange_1)
                )
            }
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = description,
                style = Chili.typography.H14_Primary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
