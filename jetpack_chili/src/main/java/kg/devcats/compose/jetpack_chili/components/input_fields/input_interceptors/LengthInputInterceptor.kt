package kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors

import androidx.compose.ui.text.input.TextFieldValue

class LengthInputInterceptor(val maxLength: Int) {

    fun intercept(textFieldValue: TextFieldValue): TextFieldValue {
        return textFieldValue.copy(text = textFieldValue.text.take(maxLength))
    }

}