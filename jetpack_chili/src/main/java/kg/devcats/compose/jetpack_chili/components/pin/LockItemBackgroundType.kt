package kg.devcats.compose.jetpack_chili.components.pin

import androidx.compose.runtime.Composable
import kg.devcats.compose.jetpack_chili.theme.Chili

enum class LockItemBackgroundType {
    Error,
    Success,
    NonSelected,
    Selected
}

@Composable
fun LockItemBackgroundType.getColor(pinItemConfig: PinItemConfig? = null) = when(this) {
    LockItemBackgroundType.Error -> pinItemConfig?.errorColor ?: Chili.color.lockErrorBg
    LockItemBackgroundType.Success -> pinItemConfig?.successColor ?: Chili.color.lockSuccessBg
    LockItemBackgroundType.Selected -> pinItemConfig?.selectedColor ?: Chili.color.lockSelectedBg
    LockItemBackgroundType.NonSelected -> pinItemConfig?.nonSelectedColor ?: Chili.color.lockNonSelectedBg
}