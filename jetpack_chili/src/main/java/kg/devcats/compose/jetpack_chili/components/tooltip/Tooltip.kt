package kg.devcats.compose.jetpack_chili.components.tooltip

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun Tooltip(
    text: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
    val background = Color.White
    var isTooltipVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isTooltipVisible,
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = {
                        isTooltipVisible = false
                        onDismiss()
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.align(Alignment.TopCenter)
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            background,
                            shape = Chili.shapes.RoundedCornerShape
                        )
                ) {
                    Text(
                        text = text,
                        color = Color.Black,
                        modifier = Modifier.padding(12.dp),
                        style = Chili.typography.H15_Primary_500
                    )
                    Image(
                        painter = painterResource(id = R.drawable.chili_ic_close_gray),
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(16.dp)
                    )
                }

                Canvas(
                    modifier = Modifier
                        .size(height = 10.dp, width = 20.dp)
                ) {
                    val trianglePath = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width / 2, size.height)
                        lineTo(size.width, 0f)
                        close()
                    }
                    drawPath(trianglePath, background)
                }
            }
        }
    }
}




