package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ChiliChevron
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCellView(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleStyle: TextStyle = Chili.typography.H12_Secondary,
    titleMaxLines: Int = 2,
    subtitleMaxLines: Int = 3,
    isDividerVisible: Boolean = false,
    isChevronVisible: Boolean = true,
    icon: Painter? = null,
    iconSize: Dp = 32.dp,
    endFrame: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    Surface(
        color = Chili.color.cellViewBackground,
        contentColor = Color.Unspecified,
        modifier = modifier
            .run {
                if (onClick != null) clickable { onClick.invoke() }
                else this
            }
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                icon?.let {
                    Image(
                        painter = it,
                        contentDescription = "",
                        modifier = Modifier
                            .size(iconSize)
                            .clip(Chili.shapes.RoundedCornerShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = titleStyle,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 12.dp, bottom = 4.dp),
                        maxLines = titleMaxLines,
                    )
                    subtitle?.let {
                        Text(
                            text = it,
                            style = subtitleStyle,
                            maxLines = subtitleMaxLines,
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                        )
                    } ?: run { Spacer(modifier = Modifier.height(8.dp)) }
                }

                endFrame?.invoke()
                if (isChevronVisible) ChiliChevron()
            }
            if (isDividerVisible) Divider(modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CellPreview() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
        Column {
            ChiliCellView(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
            )
        }
    }
}