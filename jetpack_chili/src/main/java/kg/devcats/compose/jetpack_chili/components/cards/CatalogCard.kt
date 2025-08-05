package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.magenta_1
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = Chili.typography.H14_Primary,
    titleMaxLines: Int = 2,
    title: String,
    icon: Painter? = null,
    isLoading: Boolean? = false,
    isHighlighted: Boolean? = false,
    animatedColors: List<Color> = listOf(magenta_1, white_1),
    onClick: (() -> Unit)? = null,
) {
    if (isLoading == true) {
        Surface(
            modifier = modifier
                .height(102.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = Chili.color.cardViewBackground,
            contentColor = Color.Unspecified
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Shimmer(height = 32.dp, width = 32.dp)
                Spacer(modifier = Modifier.height(16.dp))
                Shimmer(height = 6.dp, width = 83.dp)
                Spacer(modifier = Modifier.height(12.dp))
                Shimmer(height = 6.dp, width = 60.dp)
            }
        }
    } else {
        Box(
            modifier = modifier
                .height(102.dp)
                .clip(RoundedCornerShape(12.dp))
                .then(
                    onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier
                )
        ) {

            if (isHighlighted == true) {
                AnimatedGradientBackground(
                    modifier = Modifier.fillMaxSize(),
                    animatedColors = animatedColors
                )
            }

            Surface(
                modifier = Modifier
                    .then(
                        if (isHighlighted == true) Modifier
                            .padding(1.5.dp)
                            .clip(RoundedCornerShape(10.dp))
                        else Modifier
                    )
                    .fillMaxSize(),
                color = Chili.color.cellViewBackground
            ) {
                ChiliCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp),
                    title = title,
                    icon = icon,
                    titleStyle = titleStyle,
                    titlePaddingValues = PaddingValues(top = 8.dp),
                    titleMaxLines = titleMaxLines
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            CatalogCard(
                modifier = Modifier.width(158.dp),
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            CatalogCard(
                modifier = Modifier.width(158.dp),
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }
    }
}

