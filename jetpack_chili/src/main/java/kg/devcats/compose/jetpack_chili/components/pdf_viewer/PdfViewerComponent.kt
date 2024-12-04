package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.components.zoomable.ZoomableBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun PdfViewerComponent(
    modifier: Modifier = Modifier,
    pdfFile: PdfChooseCategory
) {
    val context = LocalContext.current
    var pdfUri by remember { mutableStateOf<Uri?>(null) }
    var renderedPages by remember { mutableStateOf<List<Bitmap>>(emptyList()) }

    val pdfDownloadListener = object : PdfDownloadListener {
        override fun onDownloadSuccess(file: File) {
            pdfUri = file.toUri()
        }

        override fun onDownloadFailed(e: Exception) {
        }
    }

    val pdfManager = remember {
        PdfManager(context = context, listener = pdfDownloadListener)
    }

    when (pdfFile) {
        is PdfChooseCategory.Remote -> {
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    pdfManager.downloadPdf(pdfUrl = pdfFile.pdfUrl, pdfFile.pdfUrl.split("/").last())
                }
            }
        }

        is PdfChooseCategory.LocalFile -> {
            pdfUri = pdfFile.destinationUri
        }
    }

    LaunchedEffect(pdfUri) {
        pdfUri?.let { uri ->
            renderedPages = pdfManager.pdfToBitmaps(uri)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (renderedPages.isEmpty()) {
            ChiliLoader()
        } else {
            ZoomableBox(modifier = Modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(renderedPages) { page ->
                            PdfPage(
                                modifier = Modifier.fillMaxSize(),
                                page = page
                            )
                        }
                    }
                }
            }
        }
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

sealed interface PdfChooseCategory {
    data class Remote(val pdfUrl: String) : PdfChooseCategory
    data class LocalFile(val destinationUri: Uri) : PdfChooseCategory
}