package kg.devcats.compose.jetpack_chili.components.pin

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliPinInput(
    modifier: Modifier = Modifier,
    lockItemModifier: Modifier = Modifier,
    maxSize: Int = 4,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(18.dp),
    pinCode: State<String> = mutableStateOf(""),
    pinStatusType: State<PinStatusType> = mutableStateOf(PinStatusType.None)
) {
    LazyRow(
        modifier = modifier,
        userScrollEnabled = false,
        horizontalArrangement = horizontalArrangement
    ) {
        items(maxSize) { index ->
            val isSelected = index < pinCode.value.length
            val backgroundType: LockItemBackgroundType = when(pinStatusType.value) {
                PinStatusType.Error -> LockItemBackgroundType.Error
                PinStatusType.Success -> LockItemBackgroundType.Success
                PinStatusType.None -> {
                    if (isSelected) LockItemBackgroundType.Selected
                    else LockItemBackgroundType.NonSelected
                }
            }

            PinIndicator(
                modifier = lockItemModifier,
                backgroundType = backgroundType
            )
        }
    }
}

@Composable
private fun PinIndicator(
    modifier: Modifier = Modifier,
    backgroundType: LockItemBackgroundType = LockItemBackgroundType.NonSelected
) {
    val color = animateColorAsState(
        backgroundType.getColor(),
        label = ""
    )
    val animateBorder = animateDpAsState(
        if (backgroundType == LockItemBackgroundType.NonSelected) 2.dp
        else (-1).dp,
        label = ""
    )

    Box(
        modifier = modifier
            .size(14.dp)
            .background(color.value, CircleShape)
            .border(animateBorder.value, Chili.color.lockBorderColor, CircleShape)
    )
}

@Preview
@Composable
private fun Preview() {
    ChiliPinInput()
}