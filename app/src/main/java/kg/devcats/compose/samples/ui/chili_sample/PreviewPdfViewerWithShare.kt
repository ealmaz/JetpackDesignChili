package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.runtime.Composable
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfLoadCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfViewer

@Composable
fun PreviewPdfViewerWithShare(navigateUp: () -> Unit) {

    PdfViewer(
        title = "PdfViewer With Share",
        onNavigationIconClick = navigateUp,
        shareIsVisible = true,
        pdfFileCategory = PdfLoadCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf"),
        errorPdfLoadText = "Не удалось загрузить PDF файл",
        errorDialogCloseText = "Ок"
    )
}