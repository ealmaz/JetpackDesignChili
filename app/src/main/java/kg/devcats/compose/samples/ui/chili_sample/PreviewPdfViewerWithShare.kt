package kg.devcats.compose.samples.ui.chili_sample

import android.net.Uri
import androidx.compose.runtime.Composable
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfLoadCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfViewer

@Composable
fun PreviewPdfViewerWithShare(navigateUp: () -> Unit) {

    PdfViewer(
        title = "PdfViewer With Share",
        onNavigationIconClick = navigateUp,
        shareIsVisible = true,
        pdfFileCategory = PdfLoadCategory.LocalFile(Uri.EMPTY),
        errorPdfLoadText = "Не удалось загрузить PDF файл",
        errorDialogCloseText = "Ок"
    )
}