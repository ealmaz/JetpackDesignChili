package kg.devcats.compose.samples.ui.chili_sample

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.modals.dialog.ChiliDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.ChiliOptionDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.CustomContentDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.CustomSimpleDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.DialogOption
import kg.devcats.compose.jetpack_chili.modals.dialog.LoaderDialog
import kg.devcats.compose.jetpack_chili.modals.pickers.ChiliDatePicker
import kg.devcats.compose.jetpack_chili.modals.pickers.ChiliEmojiBottomSheet
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PreviewDialogs(navigateUp: () -> Unit) {
    val context = LocalContext.current

    var showSimpleDialog by remember { mutableStateOf(false) }
    var showSimpleDialogTitle by remember { mutableStateOf(false) }

    var showOptionDialog by remember { mutableStateOf(false) }

    var showSimpleCustomDialog by remember { mutableStateOf(false) }
    var showCustomContentDialog by remember { mutableStateOf(false) }
    var showLoaderDialog by remember { mutableStateOf(false) }
    var showDismissLoaderDialogOnBackPress by remember { mutableStateOf(false) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showEmojiPickerBS by remember { mutableStateOf(false) }

    var selectedOption by remember { mutableStateOf<DialogOption<Int>?>(null) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Alerts",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            endFrame = { SampleToolbarMenu() },
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ChiliPrimaryButton(
                text = "Simple dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showSimpleDialog = true
            }

            ChiliPrimaryButton(
                text = "Simple dialog, just title", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showSimpleDialogTitle = true
            }

            ChiliPrimaryButton(
                text = "Option dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showOptionDialog = true
            }

            ChiliPrimaryButton(
                text = "Simple custom dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showSimpleCustomDialog = true
            }

            ChiliPrimaryButton(
                text = "Custom content dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showCustomContentDialog = true
            }

            ChiliPrimaryButton(
                text = "Loading dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showLoaderDialog = true
                Toast.makeText(context, "Loader shows for 10 seconds", Toast.LENGTH_LONG).show()
                scope.launch {
                    delay(10_000)
                    showLoaderDialog = false
                }
            }

            ChiliPrimaryButton(
                text = "Dismissible Loading dialog on back press", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showDismissLoaderDialogOnBackPress = true
                Toast.makeText(context, "Loader shows for a minute", Toast.LENGTH_SHORT).show()
                scope.launch {
                    delay(60_000)
                    showDismissLoaderDialogOnBackPress = false
                }
            }

            ChiliPrimaryButton(
                text = "Custom date picker dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showDatePickerDialog = true
            }

            ChiliPrimaryButton(
                text = "Emoji picker bottom sheet", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showEmojiPickerBS = true
            }
        }
    }

    ChiliDialog(
        showDialog = showSimpleDialog,
        onDismiss = { showSimpleDialog = false },
        title = "Simple dialog",
        message = "This is a simple dialog",
        positiveButtonText = "OK",
        negativeButtonText = "Cancel",
        onPositiveClick = {
            Toast.makeText(context, "Positive button clicked", Toast.LENGTH_SHORT).show()
        },
        onNegativeClick = {
            Toast.makeText(context, "Negative button clicked", Toast.LENGTH_SHORT).show()
        }
    )

    ChiliDialog(
        showDialog = showSimpleDialogTitle,
        onDismiss = { showSimpleDialogTitle = false },
        message = "This is a simple dialog",
        positiveButtonText = "OK",
        onPositiveClick = {
            Toast.makeText(context, "Positive button clicked", Toast.LENGTH_SHORT).show()
        }
    )

    val options = listOf(
        DialogOption(1, "Опция 1"),
        DialogOption(2, "Опция 2"),
        DialogOption(3, "Опция 3")
    )

    ChiliOptionDialog(
        showDialog = showOptionDialog,
        onDismiss = { showOptionDialog = false },
        options = options,
        title = "Simple dialog",
        onOptionSelected = { selectedOption = it },
        selectedOption = selectedOption
    )

    CustomContentDialog(
        showCustomContentDialog,
        { showCustomContentDialog = false },
        content = {
            Column {
                Text(
                    text = "Custom Content Dialog",
                    style = Chili.typography.H16_Primary_500,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ChiliPrimaryButton(
                    text = "Close dialog",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    showCustomContentDialog = false
                    Toast.makeText(context, "Dialog closed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    )

    CustomSimpleDialog(
        showSimpleCustomDialog,
        { showSimpleCustomDialog = false },
        title = "Custom Material 2 Dialog",
        message = "This is a custom material 2\n\n dialog 500<u>c</u>",
        positiveButtonText = "OK",
        negativeButtonText = "CANCEL",
        onConfirm = {
            Toast.makeText(context, "Confirm button clicked", Toast.LENGTH_SHORT).show()
            showSimpleCustomDialog = false
        },
        onCancel = {
            Toast.makeText(context, "Cancel button clicked", Toast.LENGTH_SHORT).show()
            showSimpleCustomDialog = false
        }
    )

    ChiliDatePicker(showDatePickerDialog,
        title = "Date",
        buttonText = "Done",
        onDateChanged = { day, month, year, date, _ ->
            /*Handle date changes*/
        },
        onDismiss = { showDatePickerDialog = false },
        onButtonClick = { day, month, year, date, _ ->
            Log.d("DatePicker", "Selected date: $day/$month/$year")
            showDatePickerDialog = false
        }
    )

    ChiliEmojiBottomSheet(
        showSheet = showEmojiPickerBS,
        onDismissRequest = { showEmojiPickerBS = false },
        onEmojiSelected = { emoji ->
            Toast.makeText(context, "Selected emoji: $emoji", Toast.LENGTH_SHORT).show()
            showEmojiPickerBS = false
        },
        onEmojiDelete = {
            Toast.makeText(context, "On emoji delete", Toast.LENGTH_SHORT).show()
        }
    )

    if (showLoaderDialog) LoaderDialog()

    if (showDismissLoaderDialogOnBackPress) LoaderDialog {
        Toast.makeText(context, "Loader's hidden", Toast.LENGTH_SHORT).show()
        showDismissLoaderDialogOnBackPress = false
    }
}