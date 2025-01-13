package kg.devcats.compose.jetpack_chili.components.common.chips

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.util.SelectionType

@Composable
fun ChiliChipsGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    titlePaddingValues: PaddingValues = PaddingValues(bottom = 14.dp),
    items: List<SelectableItemData>,
    rowPadding: PaddingValues = PaddingValues(0.dp),
    selectionType: SelectionType = SelectionType.SINGLE,
    onSelectionChanged: ((id: Any, isSelected: Boolean) -> Unit)? = null,
) {
    val selectedIds = remember { mutableStateListOf<Any>() }

    CustomChiliChipsGroup(
        modifier = modifier,
        title = title,
        titleTextStyle = titleTextStyle,
        titlePaddingValues = titlePaddingValues,
        items = items,
        selectedIds = selectedIds,
        rowPadding = rowPadding,
        selectionType = selectionType,
        onSelectionChanged = onSelectionChanged,
        chipContent = { item, isSelected, onClick ->
            ChiliTextChip(
                text = (item as? SimpleTextChip)?.text ?: "",
                isSelected = isSelected,
                onClick = onClick
            )
        }
    )
}