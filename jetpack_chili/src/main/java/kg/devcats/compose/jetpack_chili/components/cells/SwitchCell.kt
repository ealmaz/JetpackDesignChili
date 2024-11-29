package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.ChiliSwitch
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliSwitchCell(
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
    onCheckedChange: ((Boolean) -> Unit)?
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
            ChiliSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                modifier = Modifier
                    .size(width = 40.dp, height = 24.dp)
                    .padding(end = 16.dp)
            )
        }
    )
}