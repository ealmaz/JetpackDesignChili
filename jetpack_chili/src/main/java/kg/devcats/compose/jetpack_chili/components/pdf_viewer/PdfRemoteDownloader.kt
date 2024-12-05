package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

class PdfRemoteDownloader(
    val context: Context,
    val listener: PdfDownloadListener
) {

    suspend fun downloadPdf(pdfUrl: String) = withContext(Dispatchers.IO) {
        try {
            val pdfBytes = downloadPdfBytes(pdfUrl)
            val fileName = pdfUrl.split("/").last()
            savePdfToFile(pdfBytes, fileName)
        } catch (e: Exception) {
            listener.onDownloadFailed(e)
        }
    }

    private suspend fun downloadPdfBytes(pdfUrl: String): ByteArray = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(pdfUrl)
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return@withContext response.body?.bytes() ?: throw IOException("Response body is null")
        } else {
            throw IOException("Unexpected code $response")
        }
    }

    private fun savePdfToFile(pdfBytes: ByteArray, fileName: String) {
        val destinationFolder = File(context.filesDir, PDF_FOLDER_DIRECTORY).apply { mkdir() }
        val destinationFile = File(destinationFolder, fileName).apply { createNewFile() }

        destinationFile.writeBytes(pdfBytes)
        listener.onDownloadSuccess(destinationFile)
    }

    companion object {
        const val PDF_FOLDER_DIRECTORY = "pdf_files"
    }
}