package kg.devcats.compose.jetpack_chili.extensions

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

fun Spanned.toAnnotatedString(): AnnotatedString {
    val builder = AnnotatedString.Builder()

    var currentIndex = 0
    val text = this.toString()
    val spans = getSpans(0, length, Any::class.java)

    while (currentIndex < text.length) {
        val nextSpanStart = spans
            .map { getSpanStart(it) }
            .filter { it >= currentIndex }
            .minOrNull() ?: text.length

        if (nextSpanStart > currentIndex) {
            builder.append(text.substring(currentIndex, nextSpanStart))
        }

        for (span in spans) {
            val start = getSpanStart(span)
            val end = getSpanEnd(span)

            if (start == nextSpanStart) {
                val spanText = text.substring(start, end)
                when (span) {
                    is StyleSpan -> {
                        when (span.style) {
                            Typeface.BOLD -> builder.withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(spanText)
                            }
                            Typeface.ITALIC -> builder.withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(spanText)
                            }
                            else -> builder.append(spanText)
                        }
                    }
                    is ForegroundColorSpan -> builder.withStyle(SpanStyle(color = Color(span.foregroundColor))) {
                        append(spanText)
                    }
                    else -> builder.append(spanText)
                }
                currentIndex = end
                break
            }
        }

        if (currentIndex < nextSpanStart) {
            currentIndex = nextSpanStart
        }
    }

    return builder.toAnnotatedString()
}