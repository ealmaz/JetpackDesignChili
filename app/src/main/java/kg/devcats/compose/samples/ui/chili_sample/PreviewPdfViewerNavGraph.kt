package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfLoadCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfViewer
import kg.devcats.compose.samples.ui.navigation.PdfScreens

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
                pdfFileCategory = PdfLoadCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf"),
                errorPdfLoadText = "Не удалось загрузить PDF файл",
                errorDialogCloseText = "Ок",
            )
        }
        composable(PdfScreens.PdfViewerAlbumScreen.toString()) {
            PdfViewer(
                title = "PdfViewer Album",
                onNavigationIconClick = pdfNavController::navigateUp,
                pdfFileCategory = PdfLoadCategory.Remote("https://fiu.gov.kg/uploads/65e953b2c33b7.pdf"),
                errorPdfLoadText = "Не удалось загрузить PDF файл",
                errorDialogCloseText = "Ок",
            )
        }
        composable(PdfScreens.PdfViewerWithShareScreen.toString()) {
            PdfViewer(
                title = "PdfViewer With Share",
                onNavigationIconClick = pdfNavController::navigateUp,
                shareIsVisible = true,
                pdfFileCategory = PdfLoadCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf"),
                errorPdfLoadText = "Не удалось загрузить PDF файл",
                errorDialogCloseText = "Ок",
                shareTitle = "Share"
            )
        }
        composable(PdfScreens.PdfViewerWithoutZoomScreen.toString()) {
            PdfViewer(
                title = "PdfViewer Without Zoom",
                onNavigationIconClick = pdfNavController::navigateUp,
                shareIsVisible = true,
                pdfFileCategory = PdfLoadCategory.Remote("https://fiu.gov.kg/uploads/65e953b2c33b7.pdf"),
                errorPdfLoadText = "Не удалось загрузить PDF файл",
                errorDialogCloseText = "Ок",
                shareTitle = "Share",
                zoomIsEnabled = false
            )
        }
        composable(PdfScreens.PdfViewerOneElementScreen.toString()) {
            PdfViewer(
                title = "PdfViewer One Element",
                onNavigationIconClick = pdfNavController::navigateUp,
                shareIsVisible = true,
                pdfFileCategory = PdfLoadCategory.Remote("https://dengi.kg/upload/Limits_ru.pdf"),
                errorPdfLoadText = "Не удалось загрузить PDF файл",
                errorDialogCloseText = "Ок",
                shareTitle = "Share"
            )
        }
    }
}