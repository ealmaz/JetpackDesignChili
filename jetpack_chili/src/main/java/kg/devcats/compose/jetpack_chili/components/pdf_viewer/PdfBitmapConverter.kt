package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class PdfBitmapConverter(
    private val context: Context
) {
    private var renderer: PdfRenderer? = null

    suspend fun pdfToBitmaps(contentUri: Uri): List<Bitmap>? = withContext(Dispatchers.IO) {
        renderer?.close()
        try {
            return@withContext context.contentResolver.openFileDescriptor(contentUri, "r")?.use { descriptor ->
                with(PdfRenderer(descriptor)) {
                    renderer = this

                    return@use (0 until pageCount).map { pageIndex ->
                        async(Dispatchers.IO + SupervisorJob()) {
                            openPage(pageIndex).use { page ->
                                val scale = 2 // for high quality
                                val bimapWidth = page.width * scale
                                val bimapHeight = page.height * scale

                                val bitmap = createBitmap(
                                    width = bimapWidth,
                                    height = bimapHeight
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
            return@withContext null
        }
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
}