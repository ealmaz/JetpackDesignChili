package kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import kg.devcats.compose.jetpack_chili.theme.gray_1

class MaskInputInterceptor(newMask: String) {

    private var mask = EMPTY_MASK
    private var allowedInputSymbols: String = ALLOWED_SYMBOLS
    private var representation = REPRESENTATION

    private var selectionPosition = mask.indexOf(representation)

    private var selectionStartLimit = 0
    private var selectionEndLimit = 0

    private var lastInterceptedValue = ""

    init {
        setupNewMask(newMask)
    }

    fun intercept(newValue: TextFieldValue): TextFieldValue {
        if (mask == "*") return newValue
        selectionPosition = newValue.selection.start

        val isDelete = newValue.text.length < lastInterceptedValue.length
        val inputText = StringBuilder(newValue.text)
        val clearedText = clearMaskSymbols(inputText.toString()).clearForbiddenSymbols()
        val maskedText = mergeStrings(clearedText, isDelete)

        val lastMaskSym = maskedText.indexOfFirst { it == representation }.takeIf { it != -1 } ?: maskedText.length
        selectionEndLimit = lastMaskSym
        val result = buildAnnotatedString {
            append(maskedText)
            addStyle(SpanStyle(color = gray_1), lastMaskSym, maskedText.length)
            toAnnotatedString()
        }


        val startSelectionPosition = when {
            selectionPosition < selectionStartLimit -> selectionStartLimit
            selectionPosition > selectionEndLimit -> selectionEndLimit
            else -> selectionPosition
        }
        lastInterceptedValue = result.text
        return TextFieldValue(result, TextRange(startSelectionPosition, startSelectionPosition))
    }

    fun setupNewMask(newMask: String = mask) {
        if (mask != newMask) {
            mask = newMask.takeIf { it.isNotEmpty() } ?: "*"
            updateSelectionLimits()
        }
    }

    private fun updateSelectionLimits() {
        selectionStartLimit = mask.indexOfFirst { it == representation }
        selectionEndLimit = -1
    }



    fun clearMaskSymbols(text: String): String {
        return text.filter { it != representation }
    }

    private fun String.clearForbiddenSymbols(): String {
        if (allowedInputSymbols == "*") return this
        return this.filter { allowedInputSymbols.contains(it) }
    }

    private fun mergeStrings(inputText: String, isDelete: Boolean): String {
        val maskedText = StringBuilder()
        var maskIndex = 0
        var textIndex = 0
        while (true) {
            when {
                textIndex >= inputText.length && maskIndex >= mask.length -> break
                textIndex < inputText.length && maskIndex >= mask.length -> break
                textIndex >= inputText.length && maskIndex < mask.length -> {
                    maskedText.append(mask.subSequence(maskIndex, mask.length))
                    break
                }
            }
            when {
                mask[maskIndex] == representation -> {
                    maskedText.append(inputText[textIndex])
                    textIndex++
                    maskIndex++
                }
                mask[maskIndex] == inputText[textIndex] -> {
                    maskedText.append(inputText[textIndex])
                    textIndex++
                    maskIndex++
                }
                else -> {
                    val s = mask[maskIndex]
                    maskedText.append(s)
                    if (maskIndex + 1 == selectionPosition && !isDelete) {
                        selectionPosition++
                    }
                    maskIndex++
                }
            }
        }
        return maskedText.toString()
    }

    companion object {
        const val EMPTY_MASK = "*"
        const val REPRESENTATION = 'X'
        const val ALLOWED_SYMBOLS = "1234567890"
    }
}