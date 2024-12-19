package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_8
import kg.devcats.compose.jetpack_chili.theme.magenta_1
import kg.devcats.compose.jetpack_chili.theme.white_1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiliSlider(
    modifier: Modifier = Modifier,
    title: String? = null,
    minValue: Int = 0,
    maxValue: Int = 100,
    step: Int = 1,
    currentValue: Int = 50,
    displayValueFormatter: (Int) -> CharSequence = { it.toString() },
    onValueChange: (Int) -> Unit,
    titleTextStyle: TextStyle = Chili.typography.H16_Primary_500,
    captionTextStyle: TextStyle = Chili.typography.H14_Secondary,
    valueTextStyle: TextStyle = Chili.typography.H16_Secondary_700,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val validatedCurrentValue = currentValue.coerceIn(minValue, maxValue)

    val correctedValue = remember(validatedCurrentValue, minValue, maxValue, step) {
        var v = validatedCurrentValue
        if (v % step != 0) v = minValue
        if (v > maxValue) v = maxValue
        if (v < minValue) v = minValue
        v
    }

    var sliderValue by remember { mutableFloatStateOf(correctedValue.toFloat()) }

    LaunchedEffect(correctedValue) {
        if (sliderValue.toInt() != correctedValue) {
            sliderValue = correctedValue.toFloat()
        }
    }

    val formattedCurrentValue = displayValueFormatter(sliderValue.toInt())
    val formattedMinValue = displayValueFormatter(minValue)
    val formattedMaxValue = displayValueFormatter(maxValue)

    val decrement: () -> Unit = {
        val newVal = (sliderValue.toInt() - step).coerceIn(minValue, maxValue)
        sliderValue = newVal.toFloat()
        onValueChange(newVal)
    }

    val increment: () -> Unit = {
        val newVal = (sliderValue.toInt() + step).coerceIn(minValue, maxValue)
        sliderValue = newVal.toFloat()
        onValueChange(newVal)
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!title.isNullOrEmpty()) {
                Text(
                    text = title,
                    style = titleTextStyle,
                )
            } else {
                Spacer(modifier = Modifier.alpha(0f))
            }
            Text(
                text = formattedCurrentValue.toString(),
                style = valueTextStyle
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = decrement,
                enabled = enabled && sliderValue.toInt() > minValue,
                modifier = Modifier.size(28.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chili_ic_minus),
                    contentDescription = "Minus"
                )
            }

            Slider(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                thumb = {
                    SliderDefaults.Thumb(
                        interactionSource = interactionSource,
                        modifier = Modifier.size(24.dp).border(1.dp, gray_8, CircleShape),
                        colors = SliderDefaults.colors(
                            thumbColor = white_1,
                        )
                    )
                },
                track = {
                    SliderDefaults.Track(
                        sliderState = it,
                        modifier = Modifier.height(2.dp),
                        drawStopIndicator = null,
                        thumbTrackGapSize = 0.dp,
                        colors = SliderDefaults.colors(
                            activeTickColor = Color.Transparent,
                            inactiveTickColor = Color.Transparent,
                            activeTrackColor = magenta_1,
                            inactiveTrackColor = Chili.color.sliderInactiveTrackColor
                        )
                    )
                },
                value = sliderValue,
                onValueChange = {
                    val steppedValue = it.roundToStep(step, minValue, maxValue)
                    sliderValue = steppedValue.toFloat()
                    onValueChange(steppedValue)
                },
                valueRange = if (maxValue <= minValue) {
                    0f..minValue.toFloat()
                } else {
                    minValue.toFloat()..maxValue.toFloat()
                },
                steps = if (step > 0 && maxValue > minValue) {
                    maxOf(0, ((maxValue - minValue) / step) - 1)
                } else {
                    0
                },
                enabled = enabled && maxValue > minValue,
            )

            IconButton(
                onClick = increment,
                enabled = enabled && sliderValue.toInt() < maxValue,
                modifier = Modifier.size(28.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chili_ic_plus),
                    contentDescription = "Plus"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedMinValue.toString(),
                style = captionTextStyle
            )

            Text(
                text = formattedMaxValue.toString(),
                style = captionTextStyle
            )
        }
    }
}

private fun Float.roundToStep(step: Int, min: Int, max: Int): Int {
    val intVal = this.toInt()
    val clamped = intVal.coerceIn(min, max)
    return if (clamped % step != 0) min else clamped
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliSlider() {
    var currentValue by remember { mutableIntStateOf(2000) }

    Column {
        ChiliSlider(
            title = "Сумма чего-то",
            minValue = 1000,
            maxValue = 5000,
            step = 1000,
            currentValue = currentValue,
            displayValueFormatter = { "$it c" },
            onValueChange = { newVal -> currentValue = newVal }
        )
    }
}
