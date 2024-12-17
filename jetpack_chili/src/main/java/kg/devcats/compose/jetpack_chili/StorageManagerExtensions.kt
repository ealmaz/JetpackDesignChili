package kg.devcats.compose.jetpack_chili

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.core.net.toFile

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