package kg.devcats.compose.jetpack_chili.components.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.annotations.ApiStatus.Experimental

@ExperimentalComposeApi
@Composable
fun ChiliScreen(
    content: @Composable UiScreenScope.(Modifier) -> Unit
) {
    val commonUiState = rememberCommonUiState()
    val commonScope = remember(commonUiState) { UiScreenScope(commonUiState) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = commonUiState.snackbarHostState) }
    ) {
        content.invoke(commonScope, Modifier.padding(it))
    }
}

class UiScreenScope(val state: CommonUiState) {

    fun showSnackbar(message: String) {
        state.showSnackbar(message)
    }

}

@Composable
fun rememberCommonUiState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): CommonUiState {
    return remember(snackbarHostState, coroutineScope) {
        CommonUiState(snackbarHostState, coroutineScope)
    }
}

class CommonUiState(
    val snackbarHostState: SnackbarHostState,
    val scope: CoroutineScope,
) {
    fun showSnackbar(message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}
