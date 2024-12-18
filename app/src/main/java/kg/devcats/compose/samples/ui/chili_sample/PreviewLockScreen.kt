package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kg.devcats.compose.jetpack_chili.components.lock.PinLockScreen
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewLockScreen(navigateUp: () -> Unit) {
    val context = LocalContext.current
    PinLockScreen(
        titleText = "Создайте ПИН-код для входа",
//        titleText = "Введите ПИН-код",
        code = "1212",
        onActionTextClick = { navigateUp() },
//        actionText = "Forgot?",
        onSuccess = {context.showToast("Success")},
        onError = {context.showToast("Error")},
        versionText = "4.0.1",
        isToolbarVisible = true,
        navigateUp = navigateUp
    )
}