package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.gray_11
import kg.devcats.compose.jetpack_chili.theme.green_8

@Composable
fun ChiliLinearProgressIndicator(
    modifier: Modifier = Modifier,
    steps: Int,
    heightProgressBar: Dp = 6.dp,
    currentStep: Int = 0,
    trackColor: Color = gray_11,
    color: Color = green_8
) {
    Row(
        modifier = modifier.height(heightProgressBar),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(steps) { step ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(if (step < currentStep) color else trackColor, CircleShape)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ChiliLinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        steps = 6,
        currentStep = 3,
        trackColor = gray_11,
        color = green_8
    )
}