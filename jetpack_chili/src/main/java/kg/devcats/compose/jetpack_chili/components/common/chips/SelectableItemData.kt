package kg.devcats.compose.jetpack_chili.components.common.chips

interface SelectableItemData {
    val itemId: Any
}

data class SimpleTextChip(
    override val itemId: Any,
    val text: String
) : SelectableItemData