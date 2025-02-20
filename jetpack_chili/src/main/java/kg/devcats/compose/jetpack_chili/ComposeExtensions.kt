package kg.devcats.compose.jetpack_chili

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import java.util.concurrent.atomic.AtomicLong

@Composable
fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

@Composable
fun TextStyle.adjustFontWeight(isBold: Boolean): TextStyle {
    return this.copy(
        fontWeight = if (isBold) FontWeight.Bold else this.fontWeight ?: FontWeight.Normal
    )
}

fun Modifier.setIsPressedEffect(
    isPressed: MutableState<Boolean>,
    onClick: () -> Unit,
    isSurfaceClickable: Boolean = true,
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }

    if (isSurfaceClickable) {
        val currentIsPressed by interactionSource.collectIsPressedAsState()
        isPressed.value = currentIsPressed
    }

    return@composed clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = { if (isSurfaceClickable) onClick() }
    )
}

fun Modifier.clickableWithoutEffect(
    onClick: () -> Unit
): Modifier = composed {
    return@composed clickable(
        interactionSource = null,
        indication = null,
        onClick = onClick
    )
}

@Composable
fun Modifier.rippleClickable(
    enabled: Boolean = true,
    rippleColor: Color = Chili.color.keyColor,
    bounded: Boolean = false,
    radius: Dp = 32.dp,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return clickable(
        interactionSource = interactionSource,
        indication = if (enabled) ripple(
            color = rippleColor,
            bounded = bounded,
            radius = radius
        ) else null,
        onClick = { if (enabled) onClick() },
    )
}

@Composable
fun Modifier.setIsAlpha(enabled: Boolean, alpha: Float = 0.5f): Modifier {
    return alpha(if (enabled) 1f else alpha)
}

@Composable
fun Modifier.setRoundedShapeByPosition(isFirst: Boolean = false, isLast: Boolean = false): Modifier {
    val shape = when {
        isFirst && isLast -> Chili.shapes.RoundedCornerShape
        isFirst -> Chili.shapes.RoundedTopCornerShape
        isLast ->  Chili.shapes.RoundedBottomCornerShape
        else -> Chili.shapes.CornerNone
    }
    return clip(shape)
}

private val lastClickTime = AtomicLong(0L)

fun Modifier.singleClickable(
    debounceTime: Long = 1000L,
    onClick: () -> Unit
): Modifier {
    return this.clickable {
        val now = SystemClock.uptimeMillis()
        if (now - lastClickTime.get() > debounceTime) {
            lastClickTime.set(now)
            onClick()
        }
    }
}

// For Buttons

fun singleClickable(
    debounceTime: Long = 1000L,
    onClick: () -> Unit
) {
    val now = SystemClock.uptimeMillis()
    if (now - lastClickTime.get() > debounceTime) {
        lastClickTime.set(now)
        onClick()
    }
}