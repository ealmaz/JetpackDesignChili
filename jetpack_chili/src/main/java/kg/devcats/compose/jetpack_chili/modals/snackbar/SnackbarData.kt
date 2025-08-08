package kg.devcats.compose.jetpack_chili.modals.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

enum class SnackbarType {
    SIMPLE,
    LOADER,
    TIMER,
    WARNING
}

data class SnackbarMessage(
    val message: String,
    @DrawableRes val iconRes: Int? = null,
    val type: SnackbarType,
    val snackbarDurationMillis: Long = 3000,
    val progressDurationMillis: Long? = null,
    val actionText: String? = null,
    val alignment: Alignment = Alignment.TopCenter,
    val backgroundColor: Color? = null,
    val onActionClick: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null,
)
