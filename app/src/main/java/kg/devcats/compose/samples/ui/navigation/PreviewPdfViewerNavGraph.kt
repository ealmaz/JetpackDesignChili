package kg.devcats.compose.samples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfSourceCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfViewer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.chili_sample.PdfSampleScreens

@Composable
fun PreviewPdfViewerNavGraph(navController: NavController) {

    val pdfNavController = rememberNavController()
    NavHost(navController = pdfNavController, startDestination = PdfScreens.PdfViewerSampleScreens.toString()) {
        composable(PdfScreens.PdfViewerSampleScreens.toString()) {
            PdfSampleScreens(navController = pdfNavController, navigateUp = navController::navigateUp)
        }
        composable(PdfScreens.PdfViewerPortraitScreen.toString()) {
            PdfViewer(
                title = "PdfViewer Portrait",
                onNavigationIconClick = pdfNavController::navigateUp,
                pdfSourceCategory = PdfSourceCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf"),
            )
        }
        composable(PdfScreens.PdfViewerAlbumScreen.toString()) {
            PdfViewer(
                title = "PdfViewer Album",
                onNavigationIconClick = pdfNavController::navigateUp,
                pdfSourceCategory = PdfSourceCategory.Remote("https://fiu.gov.kg/uploads/65e953b2c33b7.pdf"),
            )
        }
        composable(PdfScreens.PdfViewerWithShareScreen.toString()) {
            PdfViewer(
                title = "PdfViewer With Share",
                onNavigationIconClick = pdfNavController::navigateUp,
                shareIsVisible = true,
                pdfSourceCategory = PdfSourceCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf"),
                shareTitle = "Share"
            )
        }
        composable(PdfScreens.PdfViewerWithoutZoomScreen.toString()) {
            PdfViewer(
                title = "PdfViewer Without Zoom",
                onNavigationIconClick = pdfNavController::navigateUp,
                shareIsVisible = true,
                pdfSourceCategory = PdfSourceCategory.Remote("https://fiu.gov.kg/uploads/65e953b2c33b7.pdf"),
                shareTitle = "Share",
                zoomIsEnabled = false
            )
        }
        composable(PdfScreens.PdfViewerOneElementScreen.toString()) {
            PdfViewer(
                title = "PdfViewer One Element",
                onNavigationIconClick = pdfNavController::navigateUp,
                shareIsVisible = true,
                pdfSourceCategory = PdfSourceCategory.Remote("https://dengi.kg/upload/Limits_ru.pdf"),
            )
        }
        composable(PdfScreens.ActionButtonPdfViewerScreenMultiPages.toString()) {
            PdfViewer(
                onNavigationIconClick = pdfNavController::navigateUp,
                pdfSourceCategory = PdfSourceCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf"),
                navigationIcon = painterResource(R.drawable.chili_ic_close),
                toolbarBackgroundColor = Chili.color.pdfBackgroundColor,
                isDividerVisible = false,
                buttonShareIsVisible = true,
            )
        }
        composable(PdfScreens.ActionButtonPdfViewerScreenOnePage.toString()) {
            PdfViewer(
                onNavigationIconClick = pdfNavController::navigateUp,
                pdfSourceCategory = PdfSourceCategory.Remote("https://dengi.kg/upload/Limits_ru.pdf"),
                isDividerVisible = false,
                toolbarBackgroundColor = Chili.color.pdfBackgroundColor,
                navigationIcon = painterResource(R.drawable.chili_ic_close),
                buttonShareIsVisible = true,
            )
        }
    }
}