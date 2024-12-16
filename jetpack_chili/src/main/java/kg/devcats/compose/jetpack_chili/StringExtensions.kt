package kg.devcats.compose.jetpack_chili

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml

fun String.parseHtml(): AnnotatedString {
    val processedText = this.replace("\n", "<br>")
    return AnnotatedString.fromHtml(processedText)
}

fun String.hexToColor(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: IllegalArgumentException) {
        Color.Transparent
    }
}