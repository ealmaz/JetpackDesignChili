package kg.devcats.compose.jetpack_chili.modals.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String,
    message: String,
    positiveButtonText: String = "OK",
    negativeButtonText: String = "Cancel",
    onPositiveClick: () -> Unit = {},
    onNegativeClick: () -> Unit = {}
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = title, style = Chili.typography.H16)
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                TextButton(onClick = {
                    onPositiveClick()
                    onDismiss()
                }) {
                    Text(text = positiveButtonText.uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onNegativeClick()
                    onDismiss()
                }) {
                    Text(text = negativeButtonText.uppercase())
                }
            }
        )
    }
}

@Composable
fun <T> ChiliOptionDialog(
    showDialog: Boolean,
    options: List<DialogOption<T>>,
    selectedOption: DialogOption<T>?,
    onOptionSelected: (DialogOption<T>) -> Unit,
    onDismiss: () -> Unit,
    title: String
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = title, style = Chili.typography.H16) },
            text = {
                Column {
                    options.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOptionSelected(option) }
                                .padding(8.dp)
                        ) {
                            RadioButton(
                                selected = option == selectedOption,
                                onClick = { onOptionSelected(option) }
                            )
                            Text(
                                text = option.description ?: "",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onDismiss) {
                    Text(text = "OK")
                }
            }
        )
    }
}

data class DialogOption<T>(
    val value: T,
    val description: String?
)

