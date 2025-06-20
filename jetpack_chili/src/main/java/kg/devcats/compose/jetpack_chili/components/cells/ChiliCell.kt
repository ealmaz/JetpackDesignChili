package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ChiliChevron
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.ShowShimmerOrContent
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCell(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleStyle: TextStyle = Chili.typography.H14_Secondary,
    titleMaxLines: Int = 2,
    subtitleMaxLines: Int = 3,
    isDividerVisible: Boolean = false,
    isChevronVisible: Boolean = true,
    chevronTintColor: Color = Chili.color.chevronColor,
    icon: Painter? = null,
    iconUrl: String? = null,
    placeholderIcon : Painter = painterResource(R.drawable.chili_ic_stub),
    errorIcon: Painter = painterResource(R.drawable.chili_ic_stub),
    iconSize: Dp = 32.dp,
    containerPaddingValues: PaddingValues? = null,
    containerBackgroundColor: Color = Chili.color.cellViewBackground,
    startFrame: (@Composable RowScope.() -> Unit)? = null,
    endFrame: (@Composable RowScope.() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    isLoading: Boolean = false,
) {
    Surface(
        color = containerBackgroundColor,
        contentColor = Color.Unspecified,
        modifier = modifier
            .run {
                if (onClick != null) clickable { onClick.invoke() }
                else this
            }
            .fillMaxWidth()
    ) {
        val iconEndMargin: Dp = 12.dp

        Column(
            modifier = Modifier
                .heightIn(min = 48.dp)
                .run {
                    containerPaddingValues?.let { padding(it) }
                        ?: padding(start = 12.dp, end = if (isChevronVisible) 8.dp else 12.dp)
                },
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                startFrame?.invoke(this)

                iconUrl?.let {
                    Box(modifier = Modifier.padding(vertical = 8.dp)) {
                        ShowShimmerOrContent(
                            shimmerHeight = iconSize,
                            shimmerWidth = iconSize,
                            isLoading = isLoading
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = it,
                                    placeholder = placeholderIcon,
                                    error = errorIcon
                                ),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(iconSize)
                                    .clip(Chili.shapes.RoundedCornerShape),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(iconEndMargin))
                }
                icon?.let {
                    Box(modifier = Modifier.padding(vertical = 8.dp)) {
                        ShowShimmerOrContent(
                            shimmerHeight = iconSize,
                            shimmerWidth = iconSize,
                            isLoading = isLoading
                        ) {
                            Image(
                                painter = it,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(iconSize)
                                    .clip(Chili.shapes.RoundedCornerShape)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(iconEndMargin))
                }

                Column(modifier = Modifier.weight(1f)) {
                    ShowShimmerOrContent(
                        modifier = Modifier
                            .padding(top = 18.dp, bottom = 10.dp),
                        shimmerWidth = 160.dp,
                        shimmerHeight = 8.dp,
                        isLoading = isLoading
                    ) {
                        Text(
                            text = title.parseHtml(),
                            style = titleStyle,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(top = 12.dp, bottom = 4.dp),
                            maxLines = titleMaxLines,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    subtitle?.let {
                        ShowShimmerOrContent(
                            modifier = Modifier
                                .padding(bottom = 12.dp),
                            shimmerWidth = 82.dp,
                            shimmerHeight = 8.dp,
                            isLoading = isLoading
                        ) {
                            Text(
                                text = it.parseHtml(),
                                style = subtitleStyle,
                                maxLines = subtitleMaxLines,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(bottom = 12.dp)
                            )
                        }

                    } ?: run { Spacer(modifier = Modifier.height(8.dp)) }
                }

                endFrame?.invoke(this)
                if (isChevronVisible) ChiliChevron(chevronTintColor = chevronTintColor)
            }
            if (isDividerVisible) Divider(
                modifier = Modifier.then(
                    if (icon != null || iconUrl != null) Modifier.padding(start = iconSize + iconEndMargin)
                    else Modifier
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CellPreview() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
        Column {
            ChiliCell(
                title = "Заголовок",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
            )
        }
    }
}