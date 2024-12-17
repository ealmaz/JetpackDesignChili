package kg.devcats.compose.jetpack_chili.components.pdf_viewer.manager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PdfBitmapConverter(
    private val context: Context
) {

    suspend fun pdfToBitmaps(contentUri: Uri): List<Bitmap>? {
        return try {
            context.contentResolver.openFileDescriptor(contentUri, "r")?.use { descriptor ->
                PdfRenderer(descriptor).use { renderer ->
                    convertPagesToBitmaps(renderer)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend fun convertPagesToBitmaps(renderer: PdfRenderer): List<Bitmap> = coroutineScope {
        return@coroutineScope (0 until renderer.pageCount).map { pageIndex ->
            async { convertPageToBitmap(renderer, pageIndex) }
        }.awaitAll()
    }

    private fun convertPageToBitmap(renderer: PdfRenderer, pageIndex: Int): Bitmap {
        renderer.openPage(pageIndex).use { page ->
            val bitmapWidth = page.width * BITMAP_SCALE
            val bitmapHeight = page.height * BITMAP_SCALE

            val bitmap = createBitmap(bitmapWidth, bitmapHeight)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

            return bitmap
        }
    }

    private fun createBitmap(width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).drawColor(Color.WHITE)
        }
    }

    companion object {
        const val BITMAP_SCALE = 2
    }
}
