package kg.devcats.compose.jetpack_chili.components.input_fields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliDoubleInputField(
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    message: String? = null,
    messageStyle: TextStyle = Chili.typography.H12_Secondary,
    firstFieldSuffix: AnnotatedString? = null,
    secondFieldSuffix: AnnotatedString? = null,
    onFocusChange: ((hasFocus: Boolean, isFirstFieldAffected: Boolean) -> Unit)? = null,
    firstFieldMaxLengthBeforeComma: Int = Int.MAX_VALUE,
    firstFieldMaxLengthAfterComma: Int = 2,
    secondFieldMaxLengthBeforeComma: Int = Int.MAX_VALUE,
    secondFieldMaxLengthAfterComma: Int = 2,
    fieldBackgroundColor: Color = Chili.color.inputFieldBackground,
    isFirstFieldEnabled: Boolean = true,
    isSecondFieldEnabled: Boolean = true,
    firstFieldTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    secondFieldTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    firstFieldValue: TextFieldValue,
    secondFieldValue: TextFieldValue,
    onInputOnFirstField: (TextFieldValue) -> Unit,
    onInputOnSecondField: (TextFieldValue) -> Unit,
) {
    val fieldColor = if (errorMessage == null) fieldBackgroundColor else Chili.color.inputFieldErrorBackground
    Column(modifier = modifier) {
        Row {
            ChiliAmountInputField(
                inputBgColor = fieldColor,
                value = firstFieldValue,
                modifier = Modifier.weight(1f).onFocusChanged { onFocusChange?.invoke(it.hasFocus, true) },
                suffix = firstFieldSuffix,
                keyboardType = KeyboardType.Number,
                maxLenBeforeComma = firstFieldMaxLengthBeforeComma,
                maxLenAfterComma = firstFieldMaxLengthAfterComma,
                onValueChange = onInputOnFirstField,
                isClearButtonEnabled = false,
                enabled = isFirstFieldEnabled,
                textStyle = firstFieldTextStyle
            )
            Spacer(modifier = Modifier.width(8.dp))
            ChiliAmountInputField(
                inputBgColor = fieldColor,
                value = secondFieldValue,
                modifier = Modifier.weight(1f).onFocusChanged { onFocusChange?.invoke(it.hasFocus, false) },
                suffix = secondFieldSuffix,
                keyboardType = KeyboardType.Number,
                maxLenBeforeComma = secondFieldMaxLengthBeforeComma,
                maxLenAfterComma = secondFieldMaxLengthAfterComma,
                onValueChange = onInputOnSecondField,
                isClearButtonEnabled = false,
                enabled = isSecondFieldEnabled,
                textStyle = secondFieldTextStyle
            )
        }
        if (!errorMessage.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                text = errorMessage.parseHtml(),
                style = messageStyle.copy(color = Chili.color.errorText)
            )
        } else if (!message.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                text = message.parseHtml(),
                style = messageStyle
            )
        }
    }
}

@Composable
@Preview
private fun ChiliDoubleInputFieldPreview() {
    Column {
        ChiliDoubleInputField(
            message = "В этом месяце можно продать на 87 000,00 c",
            firstFieldValue = TextFieldValue("1000.123123"),
            secondFieldValue = TextFieldValue("1000.23123"),
            firstFieldSuffix = "<u>c</u>".parseHtml(),
            firstFieldMaxLengthAfterComma = 6,
            secondFieldMaxLengthAfterComma = 6,
            onInputOnFirstField = {},
            onInputOnSecondField = {}
        )
        Spacer(modifier = Modifier.height(16.dp))

        ChiliDoubleInputField(
            message = "В этом месяце можно продать на 87 000,00 c",
            firstFieldValue = TextFieldValue("1000.123123"),
            secondFieldValue = TextFieldValue("1000.23123"),
            firstFieldSuffix = "<u>c</u>".parseHtml(),
            firstFieldMaxLengthAfterComma = 6,
            secondFieldMaxLengthAfterComma = 6,
            errorMessage = "Минимальный объём покупки 4 000,00с",
            onInputOnFirstField = {},
            onInputOnSecondField = {}
        )
    }
}
