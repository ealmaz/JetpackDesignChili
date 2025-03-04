package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.navigation.PdfScreens

@Composable
fun PdfSampleScreens(navController: NavController, navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Chili.color.surfaceBackground)
            .fillMaxSize()
    ) {
        ChiliCenteredAppToolbar(
            title = "Pdf Viewers",
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
                text = "PdfViewer Portrait", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.PdfViewerPortraitScreen.toString())
            }

            ChiliPrimaryButton(
                text = "PdfViewer Album", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.PdfViewerAlbumScreen.toString())
            }

            ChiliPrimaryButton(
                text = "PdfViewer With Share", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.PdfViewerWithShareScreen.toString())
            }

            ChiliPrimaryButton(
                text = "PdfViewer Without Zoom", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.PdfViewerWithoutZoomScreen.toString())
            }

            ChiliPrimaryButton(
                text = "PdfViewer One Element", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.PdfViewerOneElementScreen.toString())
            }

            ChiliPrimaryButton(
                text = "PdfViewer with Action Button Multi Pages", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.ActionButtonPdfViewerScreenMultiPages.toString())
            }

            ChiliPrimaryButton(
                text = "PdfViewer with Action Button One Page", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                navController.navigate(PdfScreens.ActionButtonPdfViewerScreenOnePage.toString())
            }
        }
    }
}