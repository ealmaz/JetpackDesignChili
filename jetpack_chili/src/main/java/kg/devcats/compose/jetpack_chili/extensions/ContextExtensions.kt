package kg.devcats.compose.jetpack_chili.extensions

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

fun Context.findComponentActivity(): ComponentActivity? {
    return generateSequence(this) { (it as? ContextWrapper)?.baseContext }
        .filterIsInstance<ComponentActivity>()
        .firstOrNull()
}