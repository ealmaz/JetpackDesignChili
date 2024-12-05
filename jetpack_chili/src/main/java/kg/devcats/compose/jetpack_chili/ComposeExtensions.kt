package kg.devcats.compose.jetpack_chili

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import java.io.File

@Composable
fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

@Composable
fun TextStyle.adjustFontWeight(isBold: Boolean): TextStyle {
    return this.copy(
        fontWeight = if (isBold) FontWeight.Bold else this.fontWeight ?: FontWeight.Normal
    )
}

fun Modifier.setIsPressedEffect(
    isPressed: MutableState<Boolean>,
    onClick: () -> Unit,
    isSurfaceClickable: Boolean = true,
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }

    if (isSurfaceClickable) {
        val currentIsPressed by interactionSource.collectIsPressedAsState()
        isPressed.value = currentIsPressed
    }

    return@composed clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = { if (isSurfaceClickable) onClick() }
    )
}

fun Modifier.clickableWithoutEffect(
    onClick: () -> Unit
): Modifier = composed {
    return@composed clickable(
        interactionSource = null,
        indication = null,
        onClick = onClick
    )
}

fun Context.sharePdfFile(uri: Uri) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"

        val contentUri = FileProvider.getUriForFile(
            this@sharePdfFile,
            "${packageName}.fileprovider",
            uri.toFile()
        )

        putExtra(Intent.EXTRA_STREAM, contentUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(shareIntent, "Share file with"))
}