package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.samples.ui.navigation.LockScreens

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
            PinCreateScreen { pinNavController.navigateUp() }
        }
        composable(LockScreens.LoginPinCodeScreen.toString()) {
            LoginPinScreen { pinNavController.navigateUp() }
        }
    }
}