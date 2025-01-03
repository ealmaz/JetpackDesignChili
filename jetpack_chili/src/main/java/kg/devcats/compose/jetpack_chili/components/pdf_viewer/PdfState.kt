package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.graphics.Bitmap
import java.io.File

sealed interface PdfState {

    data object Loading: PdfState

    data class Success(val pdfFile: File, val pdfBitmapPages: List<Bitmap>): PdfState

    data class Error(val e: Exception): PdfState

    data object Empty: PdfState
}