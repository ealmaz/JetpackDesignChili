package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliSecondaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliChooseActionBottomSheet(
    modifier: Modifier = Modifier,
    isShown: Boolean,
    hideOnSwipe: Boolean = false,
    onDismissRequest: () -> Unit,
    actions: List<ChiliChooseActionBSAction>
) {
    ChiliBottomSheetContainer(
        modifier = modifier,
        isShown = isShown,
        onDismissRequest = onDismissRequest,
        isCloseIconVisible = false,
        hideOnSwipe = hideOnSwipe,
    ) {
        actions.forEachIndexed { index, item ->
            if (index == actions.lastIndex) {
                ChiliSecondaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.title,
                    onClick = item.onClick,
                    rippleColor = Chili.color.secondaryText,
                    textColor = item.titleColor ?: Chili.color.buttonComponentText
                )
            }
            else ChiliSecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = item.title,
                onClick = item.onClick,
                rippleColor = Chili.color.secondaryText,
                textColor = item.titleColor ?: Chili.color.primaryText
            )
        }
    }
}

data class ChiliChooseActionBSAction(
    val title: String,
    val titleColor: Color? = null,
    val onClick: () -> Unit
)