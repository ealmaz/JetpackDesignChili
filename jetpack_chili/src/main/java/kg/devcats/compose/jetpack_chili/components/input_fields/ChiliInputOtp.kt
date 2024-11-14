package kg.devcats.compose.jetpack_chili.components.input_fields


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.LengthInputInterceptor
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.magenta_1

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChiliInputOtp(
    modifier: Modifier = Modifier,
    otpLength: Int = 6,
    otpItemPadding: Dp = 12.dp,
    error: String? = null,
    message: String? = null,
    action: String? = null,
    onActionClick: () -> Unit = {},
    isActionEnabled: Boolean = true,
    onInputOtp: (String) -> Unit
) {

    var inputFiledText by remember { mutableStateOf(TextFieldValue("")) }

    val focusRequester = remember { FocusRequester() }

    var hasFocus by remember { mutableStateOf(false) }

    val lengthInputInterceptor = remember { LengthInputInterceptor(otpLength) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Chili.color.surfaceBackground)
            .clickable(
                onClick = {
                    focusRequester.requestFocus()
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            )
    ) {
        ChiliPlainInputField(
            value = inputFiledText,
            focusRequester = focusRequester,
            keyboardType = KeyboardType.Number,
            modifier = modifier.onFocusChanged { hasFocus = it.hasFocus }
        ) {
            inputFiledText = lengthInputInterceptor
                .intercept(it)
                .copy(selection = TextRange(it.text.length))
            onInputOtp.invoke(inputFiledText.text)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Chili.color.surfaceBackground)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(otpItemPadding)
            ) {

                val last = inputFiledText.text.lastIndex

                repeat(otpLength) {
                    OtpItemView(
                        modifier = Modifier.weight(1f),
                        text = inputFiledText.text.getOrNull(it)?.toString(),
                        isSelected = hasFocus && (it == last + 1 || (last == otpLength - 1 && last == it)),
                        isErrorState = error != null
                    )
                }

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (message != null || error != null) {
                    Text(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        style = if (error == null) Chili.typography.H14_Secondary else Chili.typography.H14_Error,
                        text = error.takeIf { !it.isNullOrBlank() } ?: message ?: ""
                    )
                }
                action?.let {
                    Box(modifier = Modifier.weight(1f)) {
                        ChiliComponentButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = it,
                            textStyle = Chili.typography.H16.copy(textAlign = TextAlign.End),
                            enabled = isActionEnabled,
                            onClick = onActionClick
                        )
                    }
                }
            }

        }



    }
}

@Composable
private fun OtpItemView(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    text: String? = "",
    isErrorState: Boolean = false
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .clip(Chili.shapes.RoundedCornerShape)
            .background(if (isErrorState) Chili.color.inputFieldErrorBackground else Chili.color.inputFieldBackground)
            .run {
                if (isSelected) border(
                    1.dp,
                    magenta_1,
                    Chili.shapes.RoundedCornerShape
                ) else this
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text ?: "", style = Chili.typography.H16_Primary_500)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun PreviewChiliInputOtp() {
    ChiliInputOtp(
        message = "Message",
        action = "Action",
        isActionEnabled = false
    ) {}
}

