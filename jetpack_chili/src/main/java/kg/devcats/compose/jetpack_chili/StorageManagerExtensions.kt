package kg.devcats.compose.jetpack_chili

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import java.io.File

fun Context.shareFile(uri: Uri, fileType: String, title: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = fileType

        val contentUri = FileProvider.getUriForFile(
            this@shareFile,
            "${packageName}.fileprovider",
            uri.toFile()
        )

        putExtra(Intent.EXTRA_STREAM, contentUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(shareIntent, title))
}

fun Context.sharePdfFile(uri: Uri, title: String? = null) {
    val originalFile = uri.toFile()
    val targetFile =
        if (originalFile.extension.equals("pdf", ignoreCase = true))
            originalFile
        else
            originalFile.copyTo(File(cacheDir, "${originalFile.nameWithoutExtension}.pdf"), overwrite = true)

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"

        val contentUri = FileProvider.getUriForFile(
            this@sharePdfFile,
            "${packageName}.fileprovider",
            targetFile
        )

        putExtra(Intent.EXTRA_STREAM, contentUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(shareIntent, title))
}