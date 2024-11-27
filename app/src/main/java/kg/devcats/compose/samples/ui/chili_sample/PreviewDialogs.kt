package kg.devcats.compose.samples.ui.chili_sample

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.modals.dialog.ChiliDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.ChiliOptionDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.DialogOption
import kg.devcats.compose.jetpack_chili.modals.dialog.OptionCustomDialog
import kg.devcats.compose.jetpack_chili.modals.dialog.SimpleCustomDialog
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PreviewDialogs(navigateUp: () -> Unit) {
    val context = LocalContext.current

    var showSimpleDialog by remember { mutableStateOf(false) }
    var showOptionDialog by remember { mutableStateOf(false) }

    var showSimpleCustomDialog by remember { mutableStateOf(false) }
    var showOptionCustomDialog by remember { mutableStateOf(false) }

    var selectedOption by remember { mutableStateOf<DialogOption<Int>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Alerts",
            isDividerVisible = true,
            isNavigationIconVisible = true,
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
                text = "Option custom dialog", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                showOptionCustomDialog = true
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

    SimpleCustomDialog(
        showSimpleCustomDialog,
        { showSimpleCustomDialog = false },
        {
            showSimpleCustomDialog = false
            Toast.makeText(context, "Dialog closed", Toast.LENGTH_SHORT).show()
        }
    )

    OptionCustomDialog(
        showOptionCustomDialog,
        { showOptionCustomDialog = false},
        {
            showOptionCustomDialog = false
            Toast.makeText(context, "$it option selected", Toast.LENGTH_SHORT).show()
        }
    )
}