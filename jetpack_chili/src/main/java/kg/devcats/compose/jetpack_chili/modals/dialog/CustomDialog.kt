package kg.devcats.compose.jetpack_chili.modals.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun CustomContentDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 8.dp,
    padding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(cornerRadius),
                    color = Chili.color.surfaceBackground,
                ) {
                    Column(
                        modifier = modifier.padding(padding)
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

@Composable
fun CustomSimpleDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit = {},
    title: String? = null,
    message: String? = null,
    isCancelable: Boolean = true,
    usePlatformDefaultWidth: Boolean = true,
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {}
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = usePlatformDefaultWidth,
                dismissOnBackPress = isCancelable,
                dismissOnClickOutside = isCancelable
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Chili.color.customDialogBackgroundColor,
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        title?.let {
                            Text(
                                text = it,
                                style = Chili.typography.H16_Primary_500,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        message?.let {
                            Text(
                                text = it,
                                style = Chili.typography.H16_Secondary,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            negativeButtonText?.let {
                                TextButton(onClick = onCancel) {
                                    Text(
                                        text = it,
                                        style = Chili.typography.H14_Primary_500,
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            positiveButtonText?.let {
                                TextButton(onClick = onConfirm) {
                                    Text(
                                        text = it,
                                        style = Chili.typography.H14_Primary_500,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
