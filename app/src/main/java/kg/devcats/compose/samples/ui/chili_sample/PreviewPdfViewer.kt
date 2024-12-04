package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfChooseCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfViewerComponent

@Composable
fun PreviewPdfViewer(navigateUp: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ChiliCenteredAppToolbar(
            title = "PdfViewer",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            }
        )

        PdfViewerComponent(
            modifier = Modifier.fillMaxSize(),
            pdfFile = PdfChooseCategory.Remote("https://www.akchabulak.kg/storage/documents/ru/Oferta.pdf")
        )
    }
}