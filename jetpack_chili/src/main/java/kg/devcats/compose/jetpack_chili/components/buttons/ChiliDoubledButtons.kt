package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliDoubledButtons(
    modifier: Modifier = Modifier,
    startButtonText: String? = null,
    startButtonTextRes: Int? = null,
    endButtonText: String? = null,
    endButtonTextRes: Int? = null,
    onStartButtonClick: (() -> Unit)? = null,
    onEndButtonClick: (() -> Unit)? = null,
    enabledTextColor: Color = Chili.color.buttonComponentText,
    disabledTextColor: Color = Chili.color.buttonComponentDisabledText,
    enabled: Boolean = true,
    startButtonEnabled: Boolean = true,
    endButtonEnabled: Boolean = true
) {
    Column(modifier = modifier) {
        HorizontalDivider(thickness = 0.5.dp, color = Chili.color.dividerColor)
        Row(modifier = Modifier.height(IntrinsicSize.Min), horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .clickable(enabled = enabled && startButtonEnabled) { onStartButtonClick?.invoke() }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = modifier.wrapContentSize(),
                    text = startButtonTextRes?.let { stringResource(it) } ?: startButtonText ?: "",
                    style = Chili.typography.H16_Action,
                    color = if (enabled && startButtonEnabled) enabledTextColor else disabledTextColor,
                    textAlign = TextAlign.Center
                )
            }
            VerticalDivider(thickness = 0.5.dp, color = Chili.color.dividerColor)
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .clickable(enabled = enabled && endButtonEnabled) { onEndButtonClick?.invoke() }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = modifier.wrapContentSize(),
                    text =  endButtonTextRes?.let { stringResource(it) } ?: endButtonText ?: "",
                    style = Chili.typography.H16_Action_500,
                    color = if (enabled && endButtonEnabled) enabledTextColor else disabledTextColor,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
@Preview
fun ChiliDoubledButtonPreview() {
    ChiliDoubledButtons(
        startButtonText = "Продлить",
        endButtonText = "Погасить",
    )
}