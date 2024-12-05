package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.runtime.Composable
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfLoadCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfViewer

@Composable
fun PreviewPdfViewerAlbum(navigateUp: () -> Unit) {
    PdfViewer(
        title = "PdfViewer Album",
        onNavigationIconClick = navigateUp,
        pdfFileCategory = PdfLoadCategory.Remote("https://fiu.gov.kg/uploads/65e953b2c33b7.pdf"),
        errorPdfLoadText = "Не удалось загрузить PDF файл",
        errorDialogCloseText = "Ок"
    )
}