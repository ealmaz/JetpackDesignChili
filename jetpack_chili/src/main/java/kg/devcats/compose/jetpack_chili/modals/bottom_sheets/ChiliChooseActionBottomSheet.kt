package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliSecondaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliChooseActionBottomSheet(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    actions: List<ChiliChooseActionBSAction>
) {
    ChiliBottomSheetContainer(
        isShown = isShown,
        onDismissRequest = onDismissRequest,
        isCloseIconVisible = false,
        hideOnSwipe = false,
    ) {
        actions.forEachIndexed { index, item ->
            if (index == actions.lastIndex) ChiliSecondaryButton(modifier = Modifier.fillMaxWidth(), text = item.title, onClick = item.onClick)
            else ChiliSecondaryButton(modifier = Modifier.fillMaxWidth(), text = item.title, onClick = item.onClick, textColor = Chili.color.primaryText)

        }
    }
}

data class ChiliChooseActionBSAction(
    val title: String,
    val onClick: () -> Unit
)