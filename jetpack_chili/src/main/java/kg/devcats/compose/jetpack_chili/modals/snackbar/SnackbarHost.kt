package kg.devcats.compose.jetpack_chili.modals.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChiliSnackbarHost(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        SnackbarHost(
            hostState = SnackbarManager.snackbarHostState,
            modifier = Modifier.align(
                SnackbarManager.alignment.value
            )
        ) { _ ->
            SnackbarManager.currentSnackbarMessage?.let {
                ChiliSnackBar(snackbarMessage = it)
            }
        }
    }
}

