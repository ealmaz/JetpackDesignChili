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
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.navigation.Screens

@Composable
fun PreviewPdfViewer(navController: NavController) {
    Column(
        modifier = Modifier
            .background(Chili.color.surfaceBackground)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        ChiliPrimaryButton(
            text = "PdfViewer Portrait", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            navController.navigate(Screens.PdfViewerPortraitScreen.toString())
        }

        ChiliPrimaryButton(
            text = "PdfViewer Album", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            navController.navigate(Screens.PdfViewerAlbumScreen.toString())
        }

        ChiliPrimaryButton(
            text = "PdfViewer With Share", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            navController.navigate(Screens.PdfViewerWithShareScreen.toString())
        }
    }
}