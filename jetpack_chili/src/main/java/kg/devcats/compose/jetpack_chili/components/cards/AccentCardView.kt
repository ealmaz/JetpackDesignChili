package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.ShimmerView
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun AccentCardView(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    icon: Painter? = null,
    onClick: (() -> Unit)? = null,
    isLoading: Boolean? = false
) {

    if (isLoading == true) {
        Surface(
            color = Chili.color.cardViewBackground,
            contentColor = Color.Unspecified,
            modifier = modifier
                .padding(vertical = 10.dp, horizontal = 16.dp)
                .fillMaxWidth(),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    ShimmerView(
                        modifier = Modifier.padding(top = 8.dp),
                        height = 8.dp,
                        width = 200.dp
                    )
                    ShimmerView(
                        modifier = Modifier.padding(top = 12.dp),
                        height = 8.dp,
                        width = 140.dp
                    )
                    ShimmerView(
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                        height = 8.dp,
                        width = 82.dp
                    )
                }
                ShimmerView(height = 48.dp, width = 48.dp)
            }

        }
    } else {
        ChiliCardView(
            modifier = modifier.fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 16.dp),
            title = title,
            onClick = onClick,
            titleStyle = Chili.typography.H16_Primary_500,
            subtitle = subtitle,
            subtitleStyle = Chili.typography.H12_Secondary,
            subtitleMaxLines = 2,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            endFrame = {
                icon?.let {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = icon,
                        contentDescription = ""
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccentCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            AccentCardView(
                title = "Сканер штрихкодов и QR",
                subtitle = "Для удобной оплаты \nбез ввода реквизитов",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            AccentCardView(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }
    }
}