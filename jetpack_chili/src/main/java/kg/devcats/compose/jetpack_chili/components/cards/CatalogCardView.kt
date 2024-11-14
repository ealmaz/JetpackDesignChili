package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
fun CatalogCardView(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter? = null,
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
            Column {
                ShimmerView(height = 32.dp, width = 32.dp)
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerView(
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    height = 8.dp,
                    width = 68.dp
                )
            }
        }
    } else {
        ChiliCardView(
            modifier = modifier.padding(12.dp),
            title = title,
            icon = icon,
            onClick = onClick,
            titleStyle = Chili.typography.H16_Primary_500
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            CatalogCardView(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            CatalogCardView(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }
    }
}

