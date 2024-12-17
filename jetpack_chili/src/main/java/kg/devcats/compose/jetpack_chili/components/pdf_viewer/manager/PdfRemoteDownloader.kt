package kg.devcats.compose.jetpack_chili.components.pdf_viewer.manager

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

class PdfRemoteDownloader(
    val context: Context
) {
    private val client = OkHttpClient()

    suspend fun downloadPdf(pdfUrl: String): File? = withContext(Dispatchers.IO) {
        try {
            val pdfBytes = downloadPdfBytes(pdfUrl)
            val fileName = pdfUrl.split("/").last()
            val destinationFile = savePdfToFile(pdfBytes, fileName)
            return@withContext destinationFile
        } catch (e: Exception) {
            return@withContext null
        }
    }

    private fun downloadPdfBytes(pdfUrl: String): ByteArray {
        val request = Request.Builder()
            .url(pdfUrl)
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return response.body?.bytes() ?: throw IOException("Response body is null")
        } else {
            throw IOException("Unexpected code $response")
        }
    }

    private fun savePdfToFile(pdfBytes: ByteArray, fileName: String): File {
        val destinationFolder = File(context.filesDir, PDF_FOLDER_DIRECTORY).apply { mkdir() }
        val destinationFile = File(destinationFolder, fileName).apply { createNewFile() }

        destinationFile.writeBytes(pdfBytes)
        return destinationFile
    }

    companion object {
        const val PDF_FOLDER_DIRECTORY = "pdf_files"
    }
}