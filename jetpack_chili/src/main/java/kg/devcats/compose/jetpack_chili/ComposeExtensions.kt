package kg.devcats.compose.jetpack_chili

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.localbroadcastmanager.content.LocalBroadcastManager

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
private fun Modifier.setupAsSecure(onVibrated: (Boolean) -> Unit): Modifier {
    val context = LocalContext.current
    val broadcastManager = LocalBroadcastManager.getInstance(context)

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isSecuredNow = (context?.applicationContext as? OnApplicationSecureGestureListener)
                    ?.isSecuredNow() ?: false
                onVibrated.invoke(isSecuredNow)
            }
        }

        broadcastManager.registerReceiver(receiver, IntentFilter("UPDATE_SECURE_STATE"))

        onDispose {
            broadcastManager.unregisterReceiver(receiver)
        }
    }
    return this
}