package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliTabs(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Row(modifier = modifier.clip(RoundedCornerShape(4.dp)).background(Chili.color.tabsContainerBg).padding(2.dp)) {
        items.forEachIndexed { index, s ->
            ChiliTab(index = index, isSelected = index == selectedIndex, title = s, onSelect = onSelect)
        }
    }
}

@Composable
private fun RowScope.ChiliTab(
    index: Int,
    title: String,
    isSelected:
    Boolean,
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
            .clip(RoundedCornerShape(4.dp))
            .background(color)
            .padding(8.dp),
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