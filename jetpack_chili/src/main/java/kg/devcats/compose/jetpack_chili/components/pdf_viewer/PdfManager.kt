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
import java.io.FileOutputStream
import java.io.IOException

class PdfManager(
    val context: Context,
    val listener: PdfDownloadListener
) {
    var renderer: PdfRenderer? = null

    suspend fun downloadPdf(pdfUrl: String, fileName: String) {
        withContext(Dispatchers.IO) {
            try {
                val pdfBytes = downloadPdfBytes(pdfUrl)
                savePdfToFile(pdfBytes, fileName)
            } catch (e: Exception) {
                listener.onDownloadFailed(e)
            }
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

    suspend fun pdfToBitmaps(contentUri: Uri): List<Bitmap> = withContext(Dispatchers.IO) {
        renderer?.close()
        try {
            context.contentResolver.openFileDescriptor(contentUri, "r")?.use { descriptor ->
                with(PdfRenderer(descriptor)) {
                    renderer = this

                    return@withContext (0 until pageCount).map { pageIndex ->
                        async {
                            openPage(pageIndex).use { page ->
                                val bitmap = createBitmap(
                                    width = page.width,
                                    height = page.height
                                )

                                page.render(
                                    bitmap,
                                    null,
                                    null,
                                    PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                                )

                                bitmap
                            }
                        }
                    }.awaitAll()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext emptyList()
    }

    private fun createBitmap(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )

        Canvas(bitmap).apply {
            drawColor(Color.WHITE)
            drawBitmap(bitmap, 0f, 0f, null)
        }
        return bitmap
    }

    companion object {
        const val PDF_FOLDER_DIRECTORY = "pdf_files"
    }
}