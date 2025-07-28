package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputField
import kg.devcats.compose.jetpack_chili.components.input_fields.input_interceptors.MaskInputInterceptor
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.tooltip.Tooltip
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewTooltip(
    navigateUp: () -> Unit,
) {
    val isTooltipVisible = remember { mutableStateOf(true) }
    val pasteTooltipState = rememberTooltipState()
    val coroutineScope = rememberCoroutineScope()

    val inputMask = "+996 XXX XXX XXX"
    val maskInterceptor = remember { MaskInputInterceptor(inputMask) }
    var inputText by remember { mutableStateOf(maskInterceptor.intercept(TextFieldValue())) }

    fun showTooltip() = coroutineScope.launch {
        pasteTooltipState.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.screenBackground)
    ) {
        ChiliCenteredAppToolbar(
            modifier = Modifier.statusBarsPadding(),
            title = "Tooltips",
            isDividerVisible = true,
            endFrame = { SampleToolbarMenu() },
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            }
        )

        Button(
            enabled = !isTooltipVisible.value,
            onClick = {
                isTooltipVisible.value = true
                      },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Show tooltip")
        }

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip(
                    modifier = Modifier
                        .clickable { pasteTooltipState.dismiss() },
                    shadowElevation = 8.dp,
                    containerColor = Chili.color.surfaceBackground,
                    caretSize = DpSize(8.dp, 8.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .widthIn(min = 152.dp, max = 328.dp)
                            .padding(8.dp),
                        text = "Вставить",
                        textAlign = TextAlign.Center,
                        color = Chili.color.toggleIconColor,
                        style = Chili.typography.H14_Secondary
                    )
                }
            },
            enableUserInput = false,
            state = pasteTooltipState
        ) {
            ChiliInputField(
                value = inputText,
                modifier = Modifier.padding(horizontal = 16.dp),
                onRightActionIconClick = {},
                rightActionIcon = painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_contact),
                isInputFieldEmpty = inputText.text == inputMask,
                keyboardType = KeyboardType.Number,
                onLongClick = ::showTooltip,
                onDoubleClick = ::showTooltip
            ) {
                inputText = maskInterceptor.intercept(it)
            }
        }

        if (isTooltipVisible.value)
        Tooltip(
            tooltipText = "Ваши бонусные карты",
            onClosed = {
                coroutineScope.launch {
                    delay(500)
                    isTooltipVisible.value = false
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .navigationBarsPadding()
        )
    }
}




