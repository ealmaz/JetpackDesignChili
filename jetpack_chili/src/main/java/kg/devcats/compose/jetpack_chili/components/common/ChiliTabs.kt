package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import androidx.compose.ui.graphics.Shape

@Composable
fun ChiliTabs(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(4.dp),
    isBorderVisible: Boolean = false,
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Row(modifier = modifier.clip(shape).background(Chili.color.tabsContainerBg).padding(2.dp)) {
        items.forEachIndexed { index, s ->
            ChiliTab(
                index = index,
                isSelected = index == selectedIndex,
                title = s,
                shape = shape,
                isBorderVisible = isBorderVisible,
                onSelect = onSelect
            )
        }
    }
}

@Composable
private fun RowScope.ChiliTab(
    index: Int,
    title: String,
    isSelected: Boolean,
    shape: Shape = RoundedCornerShape(4.dp),
    isBorderVisible: Boolean = false,
    onSelect: (Int) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    val color = if(isSelected) Chili.color.tabsSelectedTab else Chili.color.tabsUnselectedTab
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) { onSelect.invoke(index) }
            .wrapContentHeight()
            .weight(1f)
            .clip(shape)
            .border(
                width = if (isBorderVisible && isSelected) 0.5.dp else 0.dp,
                color = if (isBorderVisible && isSelected) Chili.color.borderColor else Color.Transparent,
                shape = shape)
            .background(color)
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, style = Chili.typography.H14_Primary)
    }
}


@Composable
@Preview
fun ChiliTabPreview() {

    Column(modifier = Modifier.padding(16.dp)) {
        ChiliTabs(items = listOf("Open", "Closed"), selectedIndex = 0) {

        }
    }

}