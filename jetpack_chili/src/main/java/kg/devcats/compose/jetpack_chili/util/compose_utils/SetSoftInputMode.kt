package kg.devcats.compose.jetpack_chili.util.compose_utils

import android.view.ViewTreeObserver
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.annotation.IntDef
import kg.devcats.compose.jetpack_chili.extensions.findComponentActivity

@Composable
fun SetSoftInputMode(
    @SoftInputMode softInputMode: Int? = null,
    @SoftInputMode defaultSoftInputMode: Int = SOFT_INPUT_ADJUST_NOTHING,
) {
    val context = LocalContext.current
    val window = remember { context.findComponentActivity()?.window }
    val rootView = remember { window?.decorView?.rootView }
    val viewTreeObserver = remember { rootView?.viewTreeObserver }
    var layoutListener: ViewTreeObserver.OnGlobalLayoutListener? = remember { null }

    DisposableEffect(softInputMode) {

        layoutListener?.let {
            viewTreeObserver?.removeOnGlobalLayoutListener(it)
            layoutListener = null
        }

        softInputMode?.let {
            layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
                window?.setSoftInputMode(softInputMode)
            }
            viewTreeObserver?.addOnGlobalLayoutListener(layoutListener)
        } ?: window?.setSoftInputMode(defaultSoftInputMode)

        onDispose {
            layoutListener?.let {
                viewTreeObserver?.removeOnGlobalLayoutListener(it)
                layoutListener = null
            }
            window?.setSoftInputMode(defaultSoftInputMode)
        }
    }
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    SOFT_INPUT_ADJUST_NOTHING,
    SOFT_INPUT_ADJUST_RESIZE,
    SOFT_INPUT_ADJUST_PAN,
    SOFT_INPUT_ADJUST_UNSPECIFIED
)
annotation class SoftInputMode