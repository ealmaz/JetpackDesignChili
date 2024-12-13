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
fun LockItemBackgroundType.getColor() = when(this) {
    LockItemBackgroundType.Error -> Chili.color.lockErrorBg
    LockItemBackgroundType.Success -> Chili.color.lockSuccessBg
    LockItemBackgroundType.Selected -> Chili.color.lockSelectedBg
    LockItemBackgroundType.NonSelected -> Chili.color.lockNonSelectedBg
}