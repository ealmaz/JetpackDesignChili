package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.manager.PdfRenderManager
import kg.devcats.compose.jetpack_chili.components.zoomablebox.ZoomableBox
import kg.devcats.compose.jetpack_chili.modals.dialog.ChiliDialog
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PdfViewerComponent(
    modifier: Modifier = Modifier,
    pdfSourceCategory: PdfSourceCategory,
    errorText: String,
    zoomIsEnabled: Boolean = true,
    closeDialogButtonText: String? = null,
    onErrorDialogIsClosed: () -> Unit = {},
    onFileUploaded: (uri: Uri) -> Unit = {}
) {
    val context = LocalContext.current
    var pdfState by remember { mutableStateOf<PdfState>(PdfState.Loading) }
    val pdfRenderManager = remember {
        PdfRenderManager(
            context = context,
            listener = { state -> pdfState = state }
        )
    }

    LaunchedEffect(pdfSourceCategory) {
        pdfRenderManager.renderPdfFile(pdfSourceCategory)
    }

    Box(modifier = modifier
        .clipToBounds()
        .background(Chili.color.pdfBackgroundColor),
        contentAlignment = Alignment.Center) {
        when (pdfState) {
            is PdfState.Loading -> {
                ChiliLoader()
            }
            is PdfState.Success -> {
                val result = (pdfState as PdfState.Success)
                val pages = result.pdfBitmapPages
                onFileUploaded(result.pdfFile.toUri())

                ZoomableBox(
                    modifier = Modifier.matchParentSize(),
                    enabled = zoomIsEnabled,
                    contentAlignment = Alignment.TopStart
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(pages) { page ->
                            PdfPage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                page = page
                            )
                        }
                    }
                }
            }
            is PdfState.Error -> {
                ChiliDialog(
                    showDialog = true,
                    onDismiss = {},
                    title = errorText,
                    positiveButtonText = closeDialogButtonText,
                    onPositiveClick = {
                        pdfState = PdfState.Empty
                        onErrorDialogIsClosed()
                    }
                )
            }
            is PdfState.Empty -> {}
        }
    }
}

@Composable
private fun PdfPage(
    modifier: Modifier = Modifier,
    page: Bitmap
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(page.width.toFloat() / page.height.toFloat()),
        model = page,
        contentDescription = null
    )
}

sealed interface PdfSourceCategory {
    data class Remote(val pdfUrl: String) : PdfSourceCategory
    data class LocalFile(val destinationUri: Uri) : PdfSourceCategory
}