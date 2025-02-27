package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = Chili.typography.H14_Primary,
    titleMaxLines: Int = 2,
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
                .height(90.dp)
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 16.dp),
        ) {
            Column {
                Shimmer(height = 32.dp, width = 32.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Shimmer(
                    height = 6.dp,
                    width = 96.dp
                )
                Shimmer(
                    modifier = Modifier.padding(top = 8.dp),
                    height = 6.dp,
                    width = 60.dp
                )
            }
        }
    } else {
        ChiliCard(
            title = title,
            icon = icon,
            titleStyle = titleStyle,
            titleMaxLines = titleMaxLines,
            onClick = onClick,
            modifier = modifier
                .padding(start = 12.dp, top = 8.dp)
                .heightIn(84.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            CatalogCard(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = false
            )
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            CatalogCard(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }
    }
}

