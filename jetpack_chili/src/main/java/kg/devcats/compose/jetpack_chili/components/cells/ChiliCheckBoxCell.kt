package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ChiliCheckBox
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.ShowShimmerOrContent
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCheckBoxCell(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleStyle: TextStyle = Chili.typography.H14_Secondary,
    titleMaxLines: Int = 2,
    subtitleMaxLines: Int = 3,
    isDividerVisible: Boolean = false,
    isChevronVisible: Boolean = false,
    icon: Painter? = null,
    iconUrl: String? = null,
    placeholderIcon : Painter = painterResource(R.drawable.chili_ic_stub),
    errorIcon: Painter = painterResource(R.drawable.chili_ic_stub),
    iconSize: Dp = 32.dp,
    onClick: (() -> Unit)? = null,
    checked: Boolean,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    additionalInfoTextPaddingValues: PaddingValues = PaddingValues(end = 8.dp),
    additionalInfoTextWeight: Float? = null,
    additionalInfo: String? = null,
    additionalInfoStyle: TextStyle = Chili.typography.H16_Primary,
    onCheckedChange: ((Boolean) -> Unit)?,
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
        iconUrl = iconUrl,
        placeholderIcon = placeholderIcon,
        errorIcon = errorIcon,
        iconSize = iconSize,
        isLoading = isLoading,
        onClick = onClick,
        endFrame = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                additionalInfo?.let {
                    ShowShimmerOrContent(
                        modifier = Modifier.padding(end = 8.dp),
                        isLoading = isLoading,
                        shimmerHeight = 8.dp,
                        shimmerWidth = 46.dp
                    ) {
                        Text(
                            text = it.parseHtml(),
                            style = additionalInfoStyle,
                            modifier = Modifier
                                .padding(additionalInfoTextPaddingValues)
                                .run {
                                    additionalInfoTextWeight?.takeIf { it > 0 }?.let { weight(it) } ?: this
                                }
                        )
                    }
                }
                ChiliCheckBox(
                    checked = checked && !isLoading,
                    onCheckedChange = onCheckedChange,
                    enabled = enabled,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliCheckBoxCellView() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {

        ChiliCheckBoxCell(
            title = "Заголовок",
            subtitle = "Подзаголовок",
            icon = painterResource(id = R.drawable.chili_ic_documents_green),
            enabled = true,
            checked = true,
            onCheckedChange = {}
        )
    }
}

