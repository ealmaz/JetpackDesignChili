package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.common.RoundedBox
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun AdditionalContentInfoCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter? = null,
    iconUrl: String? = null,
    subTitle: String? = null,
    errorIcon: Painter? = painterResource(R.drawable.chili_ic_stub),
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subTitleStyle: TextStyle = Chili.typography.H12_Secondary,
    iconSize: Dp = 72.dp,
    infoContainerPadding: PaddingValues = PaddingValues(
        top = 12.dp,
        bottom = 12.dp,
        start = 12.dp,
        end = 4.dp
    ),
    onClick: (() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    val iconPainter = iconUrl.takeIf { !it.isNullOrEmpty() }?.let {
        rememberAsyncImagePainter(model = it, error = errorIcon)
    } ?: icon

    RoundedBox(
        modifier = Modifier.then(
            onClick?.let{ click ->
                Modifier
                    .clip(Chili.shapes.RoundedCornerShape)
                    .clickable(onClick = click)
            } ?: Modifier
        ),
        contentColor = Chili.color.contentPrimary
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(infoContainerPadding)
            ) {
                Text(
                    text = title,
                    style = titleStyle
                )
                Spacer(Modifier.height(8.dp))

                AnimatedVisibility(visible = !subTitle.isNullOrEmpty()) {
                    Column {
                        Text(
                            text = subTitle.orEmpty(),
                            style = subTitleStyle
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }

                content?.invoke(this)
            }

            AnimatedVisibility(visible = iconPainter != null) {
                iconPainter?.let {
                    Image(
                        painter = it,
                        contentDescription = "",
                        modifier = Modifier.size(iconSize),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdditionalContentInfoCardPreview() {

    val buttonTextStyle: TextStyle = Chili.typography.H14_Secondary
    val buttonShape: Shape = remember { RoundedCornerShape(20.dp) }
    val buttonBackgroundColor: Color = Chili.color.contentSecondary

    Column(modifier = Modifier.padding(16.dp)) {
        AdditionalContentInfoCard(
            title = "Заголовок",
            subTitle = "Подзаголовок",
        ) {
            ChiliComponentButton(
                modifier = Modifier
                    .clip(buttonShape)
                    .background(buttonBackgroundColor),
                text = "Подробнее",
                textStyle = buttonTextStyle,
                enabledTextColor = buttonTextStyle.color) {}
        }
        Spacer(modifier = Modifier.height(16.dp))

        AdditionalContentInfoCard(
            title = "Оплачивайте покупки через QR  или в каталоге",
            icon = painterResource(R.drawable.chilli_ic_bonus),
        ) {
            ChiliComponentButton(
                modifier = Modifier
                    .clip(buttonShape)
                    .background(buttonBackgroundColor),
                text = "Подробнее",
                textStyle = buttonTextStyle,
                enabledTextColor = buttonTextStyle.color) {}
            Spacer(modifier = Modifier.height(8.dp))

            ChiliComponentButton(
                modifier = Modifier
                    .clip(buttonShape)
                    .background(buttonBackgroundColor),
                text = "Подробнее",
                textStyle = buttonTextStyle,
                enabledTextColor = buttonTextStyle.color) {}
            Spacer(modifier = Modifier.height(8.dp))

            ChiliComponentButton(
                modifier = Modifier
                    .clip(buttonShape)
                    .background(buttonBackgroundColor),
                text = "Подробнее",
                textStyle = buttonTextStyle,
                enabledTextColor = buttonTextStyle.color) {}
        }
        Spacer(modifier = Modifier.height(16.dp))

        AdditionalContentInfoCard(
            title = "Оплачивайте покупки через QR  или в каталоге",
            iconUrl = "",
            icon = painterResource(R.drawable.chilli_ic_bonus)
        ) {
            ChiliComponentButton(
                modifier = Modifier
                    .clip(buttonShape)
                    .background(buttonBackgroundColor),
                text = "Оплачивайте покупки через QR или в каталоге",
                textStyle = buttonTextStyle,
                enabledTextColor = buttonTextStyle.color) {}
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}