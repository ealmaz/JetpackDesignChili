package kg.devcats.compose.jetpack_chili.components.common.chips

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.util.SelectionType

@Composable
fun CustomChiliChipsGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    titlePaddingValues: PaddingValues = PaddingValues(bottom = 14.dp),
    items: List<SelectableItemData>,
    selectedIds: MutableList<Any>,
    rowPadding: PaddingValues = PaddingValues(0.dp),
    selectionType: SelectionType = SelectionType.SINGLE,
    onSelectionChanged: ((id: Any, isSelected: Boolean) -> Unit)? = null,
    chipContent: @Composable (item: SelectableItemData, isSelected: Boolean, onClick: () -> Unit) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (!title.isNullOrEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(titlePaddingValues),
                text = title,
                style = titleTextStyle
            )
        }

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(rowPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                val id = item.itemId
                val isSelected = selectedIds.contains(id)

                chipContent(item, isSelected) {
                    if (isSelected) {
                        selectedIds.remove(id)
                        onSelectionChanged?.invoke(id, false)
                    } else {
                        if (selectionType == SelectionType.SINGLE) {
                            selectedIds.clear()
                        }
                        selectedIds.add(id)
                        onSelectionChanged?.invoke(id, true)
                    }
                }
            }
        }
    }
}