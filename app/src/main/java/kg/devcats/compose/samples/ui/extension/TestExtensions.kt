package kg.devcats.compose.samples.ui.extension

import android.content.Context
import android.widget.Toast

fun Context.showToast(title: String = "Компонент") {
    Toast.makeText(this, "$title clicked", Toast.LENGTH_SHORT).show()
}