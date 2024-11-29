package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCheckBoxCell(
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
    checked: Boolean,
    enabled: Boolean = true,
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
        iconSize = iconSize,
        onClick = onClick,
        endFrame = {
            ChiliCheckBox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                modifier = Modifier.size(24.dp),
            )
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

