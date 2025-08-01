package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Composable
fun ChiliTabs(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    isBorderVisible: Boolean = false,
    items: List<String>,
    selectedIndex: Int,
    pagerOffsetFraction: Float? = null,
    tabTextStyle: TextStyle = Chili.typography.H14_Primary,
    tabPaddings: PaddingValues = PaddingValues(8.dp),
    tabsContainerColor: Color = Chili.color.tabsContainerBg,
    tabsSelectedTabColor: Color = Chili.color.tabsSelectedTab,
    borderColor: Color = Chili.color.borderColor,
    onSelect: (Int) -> Unit
) {
    var tabHeightPx by remember { mutableIntStateOf(0) }
    val targetOffset = pagerOffsetFraction?.let {
        selectedIndex + it
    } ?: selectedIndex.toFloat()

    val animatedOffset by animateFloatAsState(
        targetValue = targetOffset,
        // Default TabRow indicator animation: smooth spring, no bounce
        animationSpec = spring(
            stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioNoBouncy
        ),
        label = "TabIndicatorOffset"
    )

    Box(modifier = modifier
        .fillMaxWidth()
        .clip(shape)
        .background(tabsContainerColor)
        .padding(2.dp)
        .drawBehind {
            if (tabHeightPx > 0) {
                val tabWidth = size.width / items.size
                val offsetX = tabWidth * animatedOffset

                drawRoundRect(
                    color = tabsSelectedTabColor,
                    topLeft = Offset(offsetX, 0f),
                    size = Size(tabWidth, tabHeightPx.toFloat()),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

                if (isBorderVisible) {
                    drawRoundRect(
                        color = borderColor,
                        topLeft = Offset(offsetX, 0f),
                        size = Size(tabWidth, tabHeightPx.toFloat()),
                        style = Stroke(width = 0.5.dp.toPx()),
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                }
            }
        }
    ) {

        Row {
            items.forEachIndexed { index, title ->
                ChiliTab(
                    index = index,
                    title = title,
                    tabTextStyle = tabTextStyle,
                    tabPaddings = tabPaddings,
                    onHeightMeasured = {
                        if (tabHeightPx == 0) tabHeightPx = it
                    },
                    onSelect = onSelect
                )
            }
        }
    }
}

@Composable
private fun RowScope.ChiliTab(
    index: Int,
    title: String,
    tabTextStyle: TextStyle,
    tabPaddings: PaddingValues,
    onHeightMeasured: (Int) -> Unit,
    onSelect: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) { onSelect.invoke(index) }
            .wrapContentHeight()
            .weight(1f)
            .onGloballyPositioned { coordinates ->
                onHeightMeasured(coordinates.size.height)
            }
            .background(Color.Transparent)
            .padding(tabPaddings)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, style = tabTextStyle)
    }
}


@Composable
@Preview
private fun ChiliTabPreview() {
    val tabs = listOf("Open", "Closed", "Free")
    val index = remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        ChiliTabs(
            items = tabs,
            selectedIndex = index.intValue
        ) {
            index.intValue = it % tabs.size
        }
    }
}