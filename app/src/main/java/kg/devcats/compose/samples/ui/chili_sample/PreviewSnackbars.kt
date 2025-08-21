package kg.devcats.compose.samples.ui.chili_sample

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.ChiliSnackbarHost
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showBottomSnackbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showColorSnackbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showIconSnackbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showLoaderSnackbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showSimpleSnackbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showTimerSnackbar
import kg.devcats.compose.jetpack_chili.modals.snackbar.SnackbarManager.showWarningSnackbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu

@Composable
fun PreviewSnackbars(navigateUp: () -> Unit) {
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    Scaffold(snackbarHost = {
        ChiliSnackbarHost(modifier = Modifier.systemBarsPadding())
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Chili.color.surfaceBackground)
        ) {
            ChiliCenteredAppToolbar(
                modifier = Modifier.statusBarsPadding(),
                title = "Alerts",
                isDividerVisible = true,
                isNavigationIconVisible = true,
                endFrame = { SampleToolbarMenu() },
                onNavigationIconClick = {
                    navigateUp.invoke()
                })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
            ) {
                ChiliPrimaryButton(
                    text = "Simple snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showSimpleSnackbar("Simple snackbar")
                }

                ChiliPrimaryButton(
                    text = "Loader snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showLoaderSnackbar("Loader snackbar")
                }

                ChiliPrimaryButton(
                    text = "Timer snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showTimerSnackbar(
                        "Timer snackbar",
                        "Cancel",
                        10000L,
                        onTimerExpire = {
                            Toast.makeText(context, "Timer ended", Toast.LENGTH_SHORT).show()
                        },
                        onActionClick = {
                            Toast.makeText(context, "Timer canceled", Toast.LENGTH_SHORT).show()
                        })
                }

                ChiliPrimaryButton(
                    text = "Icon snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showIconSnackbar(
                        "Завтра, 12.05.2022 будет списана абонентская плата 223 с у номера +996700000999",
                        iconRes = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_documents_green
                    )
                }

                ChiliPrimaryButton(
                    text = "Сolored snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showColorSnackbar(
                        "Завтра, 12.05.2022 будет списана абонентская плата 223 с у номера +996700000999",
                        iconRes = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_documents_green,
                        backgroundColor = Color.Yellow
                    )
                }

                ChiliPrimaryButton(
                    text = "Bottom snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showBottomSnackbar(
                        "Завтра, 12.05.2022 будет списана абонентская плата 223 с у номера +996700000999",
                        iconRes = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_documents_green
                    )
                }

                ChiliPrimaryButton(
                    text = "Warning snackbar", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    scope.showWarningSnackbar(
                        "ACHTUNG ACHTUNG",
                    )
                }
            }
        }
    }
}