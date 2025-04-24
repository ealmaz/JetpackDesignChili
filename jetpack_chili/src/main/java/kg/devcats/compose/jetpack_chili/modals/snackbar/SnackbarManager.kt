package kg.devcats.compose.jetpack_chili.modals.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

object SnackbarManager {
    val snackbarHostState = SnackbarHostState()
    private var _currentSnackbarMessage: SnackbarMessage? = null
    val currentSnackbarMessage: SnackbarMessage?
        get() = _currentSnackbarMessage

    val alignment: MutableState<Alignment> = mutableStateOf(Alignment.BottomCenter)

    fun CoroutineScope.showSimpleSnackbar(message: String) {
        showSnackbar(
            SnackbarMessage(
                message = message,
                type = SnackbarType.SIMPLE
            )
        )
    }

    fun CoroutineScope.showColorSnackbar(
        message: String,
        @DrawableRes iconRes: Int? = null,
        backgroundColor: Color
    ) {
        showSnackbar(
            SnackbarMessage(
                message = message,
                type = SnackbarType.SIMPLE,
                iconRes = iconRes,
                backgroundColor = backgroundColor
            )
        )
    }

    fun CoroutineScope.showTopSnackbar(
        message: String,
        @DrawableRes iconRes: Int? = null,
        backgroundColor: Color? = null
    ) {
        showSnackbar(
            SnackbarMessage(
                message = message,
                type = SnackbarType.SIMPLE,
                iconRes = iconRes,
                alignment = Alignment.TopCenter,
                backgroundColor = backgroundColor
            )
        )
    }

    fun CoroutineScope.showIconSnackbar(message: String, @DrawableRes iconRes: Int) {
        showSnackbar(
            SnackbarMessage(
                message = message,
                type = SnackbarType.SIMPLE,
                iconRes = iconRes
            )
        )
    }

    fun CoroutineScope.showLoaderSnackbar(message: String, duration: Long = 3000) {
        showSnackbar(
            SnackbarMessage(
                message = message,
                type = SnackbarType.LOADER,
                snackbarDurationMillis = duration
            )
        )
    }

    fun CoroutineScope.showTimerSnackbar(
        message: String,
        actionText: String,
        timerDurationMillis: Long,
        onTimerExpire: () -> Unit,
        onActionClick: (() -> Unit),
        alignment: Alignment = Alignment.BottomCenter
    ) {
        showSnackbar(
            SnackbarMessage(
                message = message,
                type = SnackbarType.TIMER,
                progressDurationMillis = timerDurationMillis,
                snackbarDurationMillis = timerDurationMillis,
                actionText = actionText,
                onActionClick = onActionClick,
                onDismiss = onTimerExpire,
                alignment = alignment
            )
        )
    }

    private fun CoroutineScope.showSnackbar(snackbarMessage: SnackbarMessage) {
        if (isAnySnackbarShowing()) {
            dismissSnackbar()
        }

        _currentSnackbarMessage = snackbarMessage
        alignment.value = snackbarMessage.alignment
        launch {
            snackbarHostState.showSnackbar(
                message = snackbarMessage.message,
                duration = SnackbarDuration.Indefinite
            )
        }.invokeOnCompletion { throwable ->
            if (throwable is CancellationException) {
                snackbarMessage.onDismiss?.invoke()
            }
        }
    }

    fun dismissSnackbar() {
        _currentSnackbarMessage = null
        snackbarHostState.currentSnackbarData?.dismiss()
    }

    private fun isAnySnackbarShowing(): Boolean {
        return snackbarHostState.currentSnackbarData != null
    }
}

