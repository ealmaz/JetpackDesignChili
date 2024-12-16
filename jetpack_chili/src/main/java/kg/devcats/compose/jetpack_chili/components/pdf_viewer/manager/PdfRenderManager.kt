package kg.devcats.compose.jetpack_chili.components.pdf_viewer.manager

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toFile
import androidx.core.net.toUri
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfDownloadListener
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfSourceCategory
import kg.devcats.compose.jetpack_chili.components.pdf_viewer.PdfState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class PdfRenderManager(
    private val context: Context,
    private val listener: (PdfState) -> Unit
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val pdfBitmapConverter = PdfBitmapConverter(context = context)
    private val pdfRemoteDownloader = PdfRemoteDownloader(context = context)

    private suspend fun loadRemoteFile(url: String) {
        val file = pdfRemoteDownloader.downloadPdf(pdfUrl = url)
            ?: return listener(PdfState.Error(IllegalArgumentException("Cannot load Pdf file")))

        loadLocalFile(file.toUri())
    }

    private fun loadLocalFile(uri: Uri) {
        try {
            val file = uri.toFile()
            if (!file.exists()) {
                listener(PdfState.Error(e = IllegalArgumentException("File must exist")))
                return
            }

            coroutineScope.launch {
                val renderedPages = pdfBitmapConverter.pdfToBitmaps(file.toUri())
                if (renderedPages == null) {
                    listener(PdfState.Error(e = IllegalArgumentException("Cannot open PDF file")))
                } else {
                    listener(PdfState.Success(pdfFile = file, pdfBitmapPages = renderedPages))
                }
            }
        } catch (e: Exception) {
            listener(PdfState.Error(e))
        }
    }

    suspend fun renderPdfFile(pdfSourceCategory: PdfSourceCategory) {
        when (pdfSourceCategory) {
            is PdfSourceCategory.Remote -> loadRemoteFile(url = pdfSourceCategory.pdfUrl)
            is PdfSourceCategory.LocalFile -> loadLocalFile(pdfSourceCategory.destinationUri)
        }
    }
}