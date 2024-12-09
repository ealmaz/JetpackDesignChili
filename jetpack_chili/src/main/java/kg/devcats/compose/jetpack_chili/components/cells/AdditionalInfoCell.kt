package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.ShowShimmerOrContent
import kg.devcats.compose.jetpack_chili.theme.Chili


@Composable
fun ChiliAdditionalInfoCell(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleStyle: TextStyle = Chili.typography.H12_Secondary,
    titleMaxLines: Int = 2,
    subtitleMaxLines: Int = 3,
    isDividerVisible: Boolean = false,
    isChevronVisible: Boolean = false,
    icon: Painter? = null,
    iconSize: Dp = 32.dp,
    onClick: (() -> Unit)? = null,
    additionalInfo: String? = null,
    additionalInfoStyle: TextStyle = Chili.typography.H16_Value,
    additionalEndIcon: Painter? = null,
    additionalEndIconSize: Dp = 18.dp,
    isLoading: Boolean = false
) {
    ChiliCell(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        titleStyle = titleStyle,
        subtitleStyle = subtitleStyle,
        titleMaxLines = titleMaxLines,
        subtitleMaxLines = subtitleMaxLines,
        isDividerVisible = isDividerVisible,
        isChevronVisible = isChevronVisible,
        icon = icon,
        iconSize = iconSize,
        onClick = onClick,
        isLoading = isLoading,
        endFrame = {
            additionalInfo?.let {
                ShowShimmerOrContent(
                    modifier = Modifier.padding(end = 4.dp),
                    isLoading = isLoading,
                    shimmerHeight = 8.dp,
                    shimmerWidth = 46.dp
                ) {
                    Text(
                        text = it,
                        style = additionalInfoStyle,
                        modifier = Modifier
                            .padding(end = 4.dp)
                    )
                }
            }
            additionalEndIcon?.let {
                Icon(
                    modifier = Modifier.size(additionalEndIconSize),
                    painter = additionalEndIcon,
                    contentDescription = ""
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdditionalTextCellPreview() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp)) {
        ChiliAdditionalInfoCell(
            title = "Заголовок",
            subtitle = "Подзаголовок",
            icon = painterResource(id = R.drawable.chili_ic_documents_green),
            additionalInfo = "Additionl info ",
            additionalEndIcon = painterResource(id = R.drawable.chili_ic_documents_green)
        )
    }
}