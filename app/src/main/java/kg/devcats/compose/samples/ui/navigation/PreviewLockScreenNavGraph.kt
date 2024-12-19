package kg.devcats.compose.samples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.samples.ui.chili_sample.pin_lock.PreviewLoginPinScreen
import kg.devcats.compose.samples.ui.chili_sample.pin_lock.PreviewPinCreateScreen
import kg.devcats.compose.samples.ui.chili_sample.PinLockSampleScreen

@Composable
fun PreviewLockScreenNavGraph(navigateUp: () -> Unit) {
    val pinNavController = rememberNavController()

    NavHost(
        navController = pinNavController,
        startDestination = LockScreens.PinLockSampleScreen.toString()
    ) {
        composable(LockScreens.PinLockSampleScreen.toString()) {
            PinLockSampleScreen(
                navController = pinNavController,
                navigateUp = navigateUp
            )
        }
        composable(LockScreens.CreatePinCodeScreen.toString()) {
            PreviewPinCreateScreen { pinNavController.navigateUp() }
        }
        composable(LockScreens.LoginPinCodeScreen.toString()) {
            PreviewLoginPinScreen { pinNavController.navigateUp() }
        }
    }
}