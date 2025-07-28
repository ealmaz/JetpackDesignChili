package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu
import kg.devcats.compose.samples.ui.navigation.LockScreens

@Composable
fun PinLockSampleScreen(navController: NavController, navigateUp: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground),
        topBar = {
            ChiliCenteredAppToolbar(
                modifier = Modifier.statusBarsPadding(),
                title = "Pdf Viewers",
                isNavigationIconVisible = true,
                isDividerVisible = true,
                onNavigationIconClick = navigateUp,
                endFrame = { SampleToolbarMenu() },
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .navigationBarsPadding()
            ) {
                ChiliPrimaryButton(
                    text = "Create Pin", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    navController.navigate(LockScreens.CreatePinCodeScreen.toString())
                }

                ChiliPrimaryButton(
                    text = "Login Pin", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    navController.navigate(LockScreens.LoginPinCodeScreen.toString())
                }

                ChiliPrimaryButton(
                    text = "Custom Pin", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    navController.navigate(LockScreens.CustomPinCodeScreen.toString())
                }
            }
        }
    )
}