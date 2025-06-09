package kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.InputFieldDefaults.MAX_DIGITS_AFTER_COMMA
import kotlin.math.min

object InputFieldDefaults {

    const val DECIMAL_COMMA = ','
    const val DECIMAL_DOT = '.'
    const val MAX_DIGITS_AFTER_COMMA = 2
    const val MAX_DIGITS_BEFORE_COMMA = 6
    const val SPACE = ' '
    const val DEFAULT_INTEGER_PART = ""
    const val DEFAULT_PART_INDEX = 0
    const val CHUNK_SIZE = 3

    const val ZERO = "0"
}

class AmountInputVisualTransformator(
    private val addDecimals: Boolean = true,
    private val suffix: AnnotatedString? = null,
    private val maxDigitsAfterComma: Int = MAX_DIGITS_AFTER_COMMA
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text
            .replace(
                InputFieldDefaults.DECIMAL_DOT,
                InputFieldDefaults.DECIMAL_COMMA
            ).filter { it.isDigit() || it == InputFieldDefaults.DECIMAL_COMMA }

        val parts = inputText.split(InputFieldDefaults.DECIMAL_COMMA)
        val integerPart =
            formatIntegerPart(
                parts.getOrElse(InputFieldDefaults.DEFAULT_PART_INDEX) {
                   InputFieldDefaults.DEFAULT_INTEGER_PART
                }
            )
        val decimalPart = formatDecimalPart(parts, maxDigitsAfterComma = maxDigitsAfterComma)

        val formattedText = buildFormattedText(integerPart, decimalPart, addDecimals, text)
        val annotatedString = if (suffix.isNullOrEmpty()) {
                AnnotatedString(formattedText)
            } else {
                buildAnnotatedString {
                    append("$formattedText  ")
                    append(suffix)
                }
        }
        val offsetMapping = createOffsetMapping(annotatedString, text)

        return TransformedText(annotatedString, offsetMapping)
    }
}

private fun formatIntegerPart(integerPart: String): String = integerPart
    .reversed()
    .chunked(InputFieldDefaults.CHUNK_SIZE)
    .joinToString(InputFieldDefaults.SPACE.toString())
    .reversed()

private fun formatDecimalPart(parts: List<String>, maxDigitsAfterComma: Int): String = if (parts.size > 1) {
    parts[1].take(maxDigitsAfterComma)
} else {
    InputFieldDefaults.DEFAULT_INTEGER_PART
}

private fun buildFormattedText(
    integerPart: String,
    decimalPart: String,
    addDecimals: Boolean,
    originalText: AnnotatedString,
): String = when {
    addDecimals && decimalPart.isNotEmpty() -> {
        "$integerPart${InputFieldDefaults.DECIMAL_COMMA}$decimalPart"
    }

    addDecimals && originalText.contains(InputFieldDefaults.DECIMAL_COMMA) -> {
        "$integerPart${InputFieldDefaults.DECIMAL_COMMA}"
    }

    else -> {
        integerPart
    }
}

private fun createOffsetMapping(
    annotatedString: AnnotatedString,
    originalText: AnnotatedString,
): OffsetMapping {
    return object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var transformedOffset = 0
            var originalOffset = 0

            while (originalOffset < offset && transformedOffset < annotatedString.length) {
                if (annotatedString[transformedOffset] != InputFieldDefaults.SPACE) {
                    originalOffset++
                }
                transformedOffset++
            }

            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var transformedOffset = 0
            var originalOffset = 0

            while (transformedOffset < offset && originalOffset < originalText.length) {
                if (annotatedString[transformedOffset] != InputFieldDefaults.SPACE) {
                    originalOffset++
                }
                transformedOffset++
            }

            return min(originalOffset, originalText.length)
        }
    }
}

fun TextFieldValue.handleZero(previousValue: TextFieldValue) : TextFieldValue {
    val regex = """[^a-zA-Z0-9]""".toRegex()
    if (regex.containsMatchIn(this.text)) return this
    return if (this.text.isBlank() || this.text == InputFieldDefaults.ZERO) {
        TextFieldValue(InputFieldDefaults.ZERO, TextRange(1))
    } else if (previousValue.text == InputFieldDefaults.ZERO && previousValue.selection.start == 0) {
        TextFieldValue(this.text.dropLast(1), selection = TextRange(this.text.length))
    } else if (previousValue.text == InputFieldDefaults.ZERO) {
        TextFieldValue(this.text.drop(1), selection = TextRange(this.text.length))
    } else {
        this
    }
}