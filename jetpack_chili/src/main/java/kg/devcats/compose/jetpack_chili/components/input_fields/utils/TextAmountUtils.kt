package kg.devcats.compose.jetpack_chili.components.input_fields.utils


import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.InputFieldDefaults


internal fun amountValueChange(inputText: String, addDecimals: Boolean = true): String {
    val processedText = replaceDecimalDot(inputText)
    val cleanedText = cleanInputText(processedText)
    val (integerPart, decimalPart) = splitIntoParts(cleanedText)
    return buildFinalText(integerPart, decimalPart, addDecimals, cleanedText)
}

internal fun replaceDecimalDot(inputText: String): String = inputText.replace(
    InputFieldDefaults.DECIMAL_DOT,
    InputFieldDefaults.DECIMAL_COMMA
)

internal fun cleanInputText(processedText: String): String {
    val stringBuilder = StringBuilder()
    var hasDecimalPoint = false

    processedText.forEach { char ->
        when {
            char.isDigit() -> stringBuilder.append(char)

            char == InputFieldDefaults.DECIMAL_COMMA && !hasDecimalPoint && stringBuilder.isNotEmpty() -> {
                stringBuilder.append(InputFieldDefaults.DECIMAL_COMMA)
                hasDecimalPoint = true
            }
        }
    }

    return stringBuilder.toString()
}

internal fun splitIntoParts(cleanedText: String): Pair<String, String> {
    val parts = cleanedText.split(InputFieldDefaults.DECIMAL_COMMA)
    val integerPart = parts.getOrElse(InputFieldDefaults.DEFAULT_PART_INDEX) {
        InputFieldDefaults.DEFAULT_INTEGER_PART
    }
    val decimalPart = if (parts.size > 1) {
        parts[1].take(InputFieldDefaults.MAX_DECIMAL_DIGITS)
    } else {
        InputFieldDefaults.DEFAULT_INTEGER_PART
    }
    return integerPart to decimalPart
}

internal fun buildFinalText(
    integerPart: String,
    decimalPart: String,
    addDecimals: Boolean,
    cleanedText: String,
): String = when {
    decimalPart.isNotEmpty() -> "$integerPart${InputFieldDefaults.DECIMAL_COMMA}$decimalPart"
    addDecimals &&
            cleanedText.contains(
                InputFieldDefaults.DECIMAL_COMMA
            ) -> "$integerPart${InputFieldDefaults.DECIMAL_COMMA}"

    else -> integerPart
}