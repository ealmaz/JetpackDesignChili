package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import java.io.File

interface PdfDownloadListener {

    fun onDownloadSuccess(file: File)

    fun onDownloadFailed(e: Exception)
}