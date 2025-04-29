package kg.devcats.compose.jetpack_chili

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.fromHtml
import kg.devcats.compose.jetpack_chili.util.Constants.HTML_REGEX

fun String.parseHtml(): AnnotatedString {
    return if (this.contains(HTML_REGEX)) {
        val processedText = this.replace("\n", "<br>")
        AnnotatedString.fromHtml(processedText)
    } else {
        buildAnnotatedString { append(this@parseHtml) }
    }
}

fun String.hexToColor(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: IllegalArgumentException) {
        Color.Transparent
    }
}