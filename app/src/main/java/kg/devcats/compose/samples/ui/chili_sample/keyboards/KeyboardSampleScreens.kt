package kg.devcats.compose.samples.ui.chili_sample.keyboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.navigation.KeyboardScreens
import kg.devcats.compose.samples.ui.navigation.Screens

@Composable
fun KeyboardSampleScreens(navController: NavController, navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Chili.color.surfaceBackground)
            .fillMaxSize()
    ) {
        ChiliCenteredAppToolbar(
            title = "Keyboards",
            isNavigationIconVisible = true,
            isDividerVisible = true,
            onNavigationIconClick = navigateUp
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            ChiliPrimaryButton(
                text = "Chili Keyboard",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(KeyboardScreens.ChiliKeyboardScreen.toString())
            }

            ChiliPrimaryButton(text = "Number Keyboard", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController.navigate(Screens.NumberKeyboardScreen.toString())
            }

        }
    }
}
