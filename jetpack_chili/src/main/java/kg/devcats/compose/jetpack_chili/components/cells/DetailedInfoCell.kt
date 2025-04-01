package kg.devcats.compose.jetpack_chili.components.cells

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.RoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun DetailedInfoCell(
    modifier: Modifier = Modifier,
    icon: Painter?,
    title: String,
    subTitle: String? = null,
    value: String? = null,
    subValue: String? = null,
    additionalText: String? = null,
    caption: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subTitleStyle: TextStyle = Chili.typography.H12_Secondary,
    valueStyle: TextStyle = Chili.typography.H16_Primary,
    subValueStyle: TextStyle = Chili.typography.H12_Value,
    additionalTextStyle: TextStyle = Chili.typography.H12_Secondary,
    captionStyle: TextStyle = Chili.typography.H12_Value,
    isLoading: Boolean = false
) {
    if (isLoading) {
        LoadingCell()
        return
    }
    Box {
        val captionOffsetModifier = if (caption.isNullOrBlank()) Modifier else Modifier.padding(top = 9.dp)
        RoundedBox(modifier = modifier.then(captionOffsetModifier)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    icon?.let {
                        Icon(modifier = Modifier.size(32.dp), painter = icon, contentDescription = null, tint = Color.Unspecified)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(modifier = Modifier.weight(1f), text = title, style = titleStyle, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    value?.let { Text(text = value.parseHtml(), style = valueStyle) }
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                    subTitle?.let {
                        Text(modifier = Modifier
                            .weight(2f)
                            .padding(top = 8.dp, end = 8.dp), text = subTitle, style = subTitleStyle)
                    }
                    subValue?.let {
                        Text(modifier = Modifier
                            .padding(top = 8.dp)
                            .weight(1f), text = subValue.parseHtml(), style = subValueStyle, textAlign = TextAlign.End)
                    }
                }
                additionalText?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Chili.color.contentSecondary)
                            .padding(8.dp),
                        text = it.parseHtml(),
                        style = additionalTextStyle
                    )
                }
            }
        }
        caption?.let {
            Text(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.TopEnd)
                    .border(
                        width = 1.dp,
                        color = Chili.color.contentSecondary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Chili.color.contentPrimary)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                text = it,
                style = captionStyle
            )
        }
    }
}

@Composable
private fun LoadingCell(modifier: Modifier = Modifier) {
    RoundedBox(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Shimmer(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Shimmer(
                    modifier = Modifier
                        .width(140.dp)
                        .height(8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Shimmer(
                    modifier = Modifier
                        .width(80.dp)
                        .height(8.dp),
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Shimmer(
                modifier = Modifier
                    .width(110.dp)
                    .height(8.dp)
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
fun DetailedInfoCellPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.screenSecondary)
        .padding(16.dp)) {

        DetailedInfoCell(
            icon = painterResource(R.drawable.chili_ic_documents_green),
            title = "USDT (TRC20)",
            subTitle = "Достигнут лимит",
            value = "- 500,00 <u>с</u>",
            subValue = "44 500,00 с ",
            additionalText = "Ожидает зачисления: 10 500,00 c",
            caption = "09:42",
            isLoading = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        DetailedInfoCell(
            icon = painterResource(R.drawable.chili_ic_documents_green),
            title = "USDT (TRC20)",
            subTitle = "Достигнут лимит",
            value = "- 500,00 с",
            subValue = "44 500,00 с ",
            additionalText = "Ожидает зачисления: 10 500,00 c",
            caption = "09:42",
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailedInfoCell(
            icon = painterResource(R.drawable.chili_ic_documents_green),
            title = "USDT (TRC20)",
            subTitle = null,
            value = "- 500,00 с",
            subValue = null,
            additionalText = "Ожидает зачисления: 10 500,00 c",
            caption = "09:42",
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailedInfoCell(
            icon = painterResource(R.drawable.chili_ic_documents_green),
            title = "USDT (TRC20)",
            subTitle = null,
            value = "- 500,00 с",
            subValue = "+ 44 567,00",
            additionalText = "Ожидает зачисления: 10 500,00 c",
            caption = "09:42",
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailedInfoCell(
            icon = painterResource(R.drawable.chili_ic_documents_green),
            title = "USDT (TRC20)",
            subTitle = "Достигнут лимит",
            value = "- 500,00 с",
            subValue = "44 500,00 с ",
            additionalText = null,
            caption = "09:42",
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailedInfoCell(
            icon = painterResource(R.drawable.chili_ic_documents_green),
            title = "USDT (TRC20)",
            subTitle = "Достигнут лимит",
            value = "- 500,00 с",
            subValue = "44 500,00 с ",
            additionalText = null,
            caption = null,
        )
    }
}