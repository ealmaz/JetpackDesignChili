package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun BonusCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter? = null,
    size: BonusCardSize = BonusCardSize.Large(),
    onClick: (() -> Unit)? = null,
    isLoading: Boolean? = false
) {

    if (isLoading == true) {
        Surface(
            color = Chili.color.cardViewBackground,
            contentColor = Color.Unspecified,
            modifier = modifier
                .padding(12.dp),
        ) {
            Column(
                modifier = modifier.size(width = size.width, height = size.height)
            ) {
                Shimmer(height = size.iconSize, width = size.iconSize)
                when(size) {
                    is BonusCardSize.Large -> Spacer(modifier = Modifier.height(8.dp))
                    is BonusCardSize.Middle -> Spacer(modifier = Modifier.height(4.dp))
                }
                Shimmer(
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    height = 8.dp,
                    width = 82.dp
                )
                Shimmer(
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    height = 8.dp,
                    width = 48.dp
                )
            }
        }
    } else {
        ChiliCard(
            modifier = modifier
                .padding(12.dp)
                .size(width = size.width, height = size.height),
            title = title,
            icon = icon,
            iconSize = size.iconSize,
            onClick = onClick,
            titlePaddingValues = PaddingValues(top = 12.dp, end = 8.dp),
            titleMaxLines = 2,
            titleStyle = if (size is BonusCardSize.Large) Chili.typography.H16_Primary_500
            else Chili.typography.H12_Primary
        )
    }
}

open class BonusCardSize(
    val height: Dp,
    val width: Dp,
    val iconSize: Dp
) {
    class Large(
        height: Dp = 120.dp,
        width: Dp = 164.dp,
        iconSize: Dp = 48.dp,
    ) : BonusCardSize(height, width, iconSize)

    class Middle(
        height: Dp = 96.dp,
        width: Dp = 120.dp,
        iconSize: Dp = 32.dp,
    ) : BonusCardSize(height, width, iconSize)

    class Small(
        height: Dp = 72.dp,
        width: Dp = 120.dp,
        iconSize: Dp = 24.dp,
    ) : BonusCardSize(height, width, iconSize)
}

@Preview(showBackground = true)
@Composable
fun BonusCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BonusCard(
                title = "Заголовок в 2 строки",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BonusCard(
                title = "Заголовок в 2 строки",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false,
                size = BonusCardSize.Middle()
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BonusCard(
                title = "Заголовок в 2 строки",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false,
                size = BonusCardSize.Small()
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BonusCard(
                title = "Заголовок в 2 строки",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BonusCard(
                title = "Заголовок в 2 строки",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true,
                size = BonusCardSize.Middle()
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
            BonusCard(
                title = "Заголовок в 2 строки",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true,
                size = BonusCardSize.Small()
            )
        }
    }
}

