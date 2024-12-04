package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    titlePaddingValues: PaddingValues = PaddingValues(top = 4.dp, end = 8.dp),
    subtitleStyle: TextStyle = Chili.typography.H12_Secondary,
    titleMaxLines: Int = 1,
    subtitleMaxLines: Int = 1,
    icon: Painter? = null,
    endFrame: @Composable (() -> Unit)? = null,
    iconSize: Dp = 32.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    isEnabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val alphaForDisableState = if (isEnabled) 1f else 0.5f
    Surface(
        color = Chili.color.cardViewBackground,
        contentColor = Color.Unspecified,
        modifier = modifier.run {
            if (onClick != null) clickable { onClick.invoke() }
            else this
        },
    ) {
        Row(
            modifier = Modifier.alpha(alphaForDisableState),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment
        ) {
            Column(
                horizontalAlignment = horizontalAlignment
            ) {
                icon?.let {
                    Image(
                        painter = it,
                        contentDescription = "",
                        modifier = Modifier
                            .clip(Chili.shapes.RoundedCornerShape)
                            .size(iconSize)
                    )
                }
                Text(
                    modifier = Modifier.padding(titlePaddingValues),
                    text = title,
                    style = titleStyle,
                    maxLines = titleMaxLines,
                    overflow = TextOverflow.Ellipsis,
                )
                subtitle?.let {
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = it,
                        style = subtitleStyle,
                        maxLines = subtitleMaxLines,
                    )
                }
            }
            endFrame?.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChiliCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            ChiliCard(
                modifier = Modifier.fillMaxWidth(),
                title = "Title",
                subtitle = "1100 c",
                verticalAlignment = Alignment.CenterVertically,

                endFrame = {
                    Image(
                        painter = painterResource(id = R.drawable.chili_ic_documents_green),
                        contentDescription = ""
                    )
                }
            )
        }
    }

}
