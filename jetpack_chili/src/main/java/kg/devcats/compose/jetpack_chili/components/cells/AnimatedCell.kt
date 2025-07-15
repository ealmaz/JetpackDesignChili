package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.cards.AnimatedGradientBackground
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun AnimatedCell(
    modifier: Modifier = Modifier,
    isAnimating: Boolean = true,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleStyle: TextStyle = Chili.typography.H14_Secondary,
    titleMaxLines: Int = 2,
    subtitleMaxLines: Int = 3,
    titleOverflow: TextOverflow = TextOverflow.Ellipsis,
    isDividerVisible: Boolean = false,
    isChevronVisible: Boolean = false,
    icon: Painter? = null,
    iconUrl: String? = null,
    iconSize: Dp = 32.dp,
    placeholderIcon: Painter = painterResource(R.drawable.chili_ic_stub),
    errorIcon: Painter = painterResource(R.drawable.chili_ic_stub),
    onClick: (() -> Unit)? = null,
    containerBackgroundColor: Color = Chili.color.cellViewBackground,
    containerPaddingValues: PaddingValues? = null,
    dividerPaddingValues: PaddingValues? = null,
    startFrame: (@Composable RowScope.() -> Unit)? = null,
    isLoading: Boolean = false
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(Chili.shapes.RoundedCornerShape)
    ) {
        AnimatedGradientBackground(
            modifier = Modifier
                .matchParentSize(),
            isAnimating = isAnimating
        )

        ChiliCell(
            modifier = Modifier
                .padding(2.dp)
                .clip(Chili.shapes.RoundedCornerShape),
            title = title,
            subtitle = subtitle,
            titleStyle = titleStyle,
            subtitleStyle = subtitleStyle,
            titleMaxLines = titleMaxLines,
            subtitleMaxLines = subtitleMaxLines,
            titleOverflow = titleOverflow,
            isDividerVisible = isDividerVisible,
            isChevronVisible = isChevronVisible,
            icon = icon,
            iconUrl = iconUrl,
            iconSize = iconSize,
            placeholderIcon = placeholderIcon,
            errorIcon = errorIcon,
            onClick = onClick,
            containerBackgroundColor = containerBackgroundColor,
            containerPaddingValues = containerPaddingValues,
            dividerPaddingValues = dividerPaddingValues,
            isLoading = isLoading,
            startFrame = startFrame
        )

    }
}

@Preview
@Composable
fun AnimatedChiliCellPreview() {
    AnimatedCell(
        title = "AnimatedCell"
    )
}