package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import java.io.File

interface PdfDownloadListener {

    fun onSuccess(file: File)

    fun onFailed(e: Exception)
}