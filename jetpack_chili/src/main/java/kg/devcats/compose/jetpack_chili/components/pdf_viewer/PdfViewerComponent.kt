package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.core.net.toUri
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.components.zoomable.rememberZoomState
import kg.devcats.compose.jetpack_chili.components.zoomable.zoomable
import kg.devcats.compose.jetpack_chili.modals.dialog.ChiliDialog
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun PdfViewerComponent(
    modifier: Modifier = Modifier,
    pdfFile: PdfLoadCategory,
    errorText: String,
    closeDialogButtonText: String? = null,
    errorDialogIsClosed: () -> Unit = {},
    fileUploaded: (uri: Uri) -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val pdfBitmapConverter = remember { PdfBitmapConverter(context = context) }
    var pdfState by remember { mutableStateOf<PdfState>(PdfState.Loading) }
    var errorDialog by remember { mutableStateOf(false) }

    val pdfDownloadListener = object : PdfDownloadListener {
        override fun onDownloadSuccess(file: File) {
            if (file.isAbsolute) {
                coroutineScope.launch {
                    fileUploaded.invoke(file.toUri())
                    val renderedPages = pdfBitmapConverter.pdfToBitmaps(file.toUri())
                    pdfState = PdfState.Success(pdfFile = file, pdfBitmapPages = renderedPages)
                }
            } else {
                pdfState = PdfState.Error(e = IllegalArgumentException("file must be absolute"))
            }
        }

        override fun onDownloadFailed(e: Exception) {
            pdfState = PdfState.Error(e = e)
        }
    }

    val pdfRemoteDownloader = remember {
        PdfRemoteDownloader(context = context, listener = pdfDownloadListener)
    }

    when (pdfFile) {
        is PdfLoadCategory.Remote -> {
            LaunchedEffect(Unit) {
                pdfRemoteDownloader.downloadPdf(pdfUrl = pdfFile.pdfUrl)
            }
        }

        is PdfLoadCategory.LocalFile -> {
            try {
                pdfDownloadListener.onDownloadSuccess(pdfFile.destinationUri.toFile())
            } catch (e: Exception) {
                pdfState = PdfState.Error(e)
            }
        }
    }

    Box(modifier = modifier.clipToBounds(), contentAlignment = Alignment.Center) {
        when (pdfState) {
            is PdfState.Loading -> {
                ChiliLoader()
            }
            is PdfState.Success -> {
                val pages = (pdfState as PdfState.Success).pdfBitmapPages

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zoomable(rememberZoomState())
                ) {
                    items(pages) { page ->
                        PdfPage(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            page = page
                        )
                    }
                }
            }
            is PdfState.Error -> errorDialog = true
        }
        ChiliDialog(
            showDialog = errorDialog,
            onDismiss = { errorDialog = false },
            title = errorText,
            positiveButtonText = closeDialogButtonText,
            onPositiveClick = {
                errorDialog = false
                errorDialogIsClosed()
            }
        )
    }
}

@Composable
fun PdfPage(
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

sealed interface PdfLoadCategory {
    data class Remote(val pdfUrl: String) : PdfLoadCategory
    data class LocalFile(val destinationUri: Uri) : PdfLoadCategory
}