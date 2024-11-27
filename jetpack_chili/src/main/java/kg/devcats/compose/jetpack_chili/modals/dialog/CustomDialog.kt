package kg.devcats.compose.jetpack_chili.modals.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    padding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                shape = RoundedCornerShape(cornerRadius),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                modifier = modifier
                    .padding(horizontal = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth()
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun SimpleCustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onActionClick: () -> Unit,
) {
    CustomDialog(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        cornerRadius = 12.dp,
        padding = 24.dp
    ) {
        Text(
            text = "Это кастомный диалог с углами и внутренними отступами.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onActionClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Закрыть")
        }
    }
}

@Composable
fun OptionCustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onActionClick: (String) -> Unit,
) {

    var text by remember { mutableStateOf("") }
    CustomDialog(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        cornerRadius = 16.dp,
        padding = 12.dp
    ) {
        Text(
            text = "Выберите элемент:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        val items = listOf("Элемент 1", "Элемент 2", "Элемент 3")
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = text == item,
                    onClick = {
                        text = item
                        onActionClick(item)
                    }
                )
                Text(text = item, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {

                onActionClick(text) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Закрыть")
        }
    }
}
