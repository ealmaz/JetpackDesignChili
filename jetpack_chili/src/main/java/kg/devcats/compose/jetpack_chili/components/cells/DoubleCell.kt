package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_2

@Composable
fun DoubleCell(vararg cells: DoubleCellItemParams) {
    Column(
        modifier = Modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .background(Chili.color.cellViewBackground)
            .padding(vertical = 8.dp)
    ) {
        cells.forEachIndexed { index, doubleCellItemParams ->
            DoubleCellItem(params = doubleCellItemParams)
            if (index != cells.lastIndex) ItemsDivider()
        }
    }
}

@Composable
private fun ItemsDivider() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .size(width = 28.dp, height = 1.dp)
                .background(Chili.color.dividerColor)
        )
        Icon(
            tint = gray_2,
            painter = painterResource(R.drawable.chili4_ic_arrow_down_gray),
            contentDescription = null
        )
        Divider(modifier = Modifier
            .height(1.dp)
            .weight(1f)
            .background(Chili.color.dividerColor))
    }
}

@Composable
private fun DoubleCellItem(params: DoubleCellItemParams) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            params.icon?.let {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = it,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = params.title,
                    style = params.titleTextStyle
                )
                params.subtitle?.let {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = params.subtitleTextStyle,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        params.additionalText?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Chili.color.contentSecondary)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                text = it,
                style = params.additionalTextStyle
            )
        }
    }
}

data class DoubleCellItemParams(
    val icon: Painter? = null,
    val title: String,
    val subtitle: String? = null,
    val additionalText: String? = null,
    val titleTextStyle: TextStyle,
    val subtitleTextStyle: TextStyle,
    val additionalTextStyle: TextStyle,
) {
    companion object {

        @Composable
        fun getDefaultParams(
            title: String,
            subtitle: String? = null,
            additionalText: String? = null,
            icon: Painter? = null,
            titleTextStyle: TextStyle = Chili.typography.H16_Primary,
            subtitleTextStyle: TextStyle = Chili.typography.H14_Secondary,
            additionalTextStyle: TextStyle = Chili.typography.H14_Secondary,
        ): DoubleCellItemParams {
            return DoubleCellItemParams(
                icon = icon,
                title = title,
                subtitle = subtitle,
                additionalText = additionalText,
                titleTextStyle = titleTextStyle,
                subtitleTextStyle = subtitleTextStyle,
                additionalTextStyle = additionalTextStyle,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun DoubleCellPreview() {

    Box(modifier = Modifier.padding(16.dp)) {
        DoubleCell(
            DoubleCellItemParams.getDefaultParams(
                title = "Заговолок",
                subtitle = "Подзаголовок",
                additionalText = "За ужин",
                icon = painterResource(R.drawable.chili_ic_documents_green)
            ),
            DoubleCellItemParams.getDefaultParams(
                title = "Заговолок",
                subtitle = "Подзаголовок",
                additionalText = "За ужин",
                icon = painterResource(R.drawable.chili_ic_documents_green)
            )
        )
    }
}