package kg.devcats.compose.samples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.samples.ui.chili_sample.keyboards.KeyboardSampleScreens
import kg.devcats.compose.samples.ui.chili_sample.keyboards.PreviewPinKeyboard
import kg.devcats.compose.samples.ui.chili_sample.keyboards.PreviewNumberKeyboard

@Composable
fun PreviewKeyboardNavGraph(navController: NavController) {
    val keyboardNavController = rememberNavController()
    NavHost(
        navController = keyboardNavController,
        startDestination = KeyboardScreens.KeyboardSampleScreens.toString()
    ) {
        composable(KeyboardScreens.KeyboardSampleScreens.toString()) {
            KeyboardSampleScreens(
                navController = keyboardNavController,
                navigateUp = navController::navigateUp
            )
        }
        composable(KeyboardScreens.ChiliKeyboardScreen.toString()) {
            PreviewPinKeyboard { navController.navigateUp() }
        }
        composable(Screens.NumberKeyboardScreen.toString()) {
            PreviewNumberKeyboard { navController.navigateUp() }
        }
    }
}



