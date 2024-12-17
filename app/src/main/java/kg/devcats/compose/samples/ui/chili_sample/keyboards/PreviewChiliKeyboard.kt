package kg.devcats.compose.samples.ui.chili_sample.keyboards

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliInputField
import kg.devcats.compose.jetpack_chili.components.keyboards.ActionButtonType
import kg.devcats.compose.jetpack_chili.components.keyboards.ChiliKeyboard
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar

@Composable
fun PreviewChiliKeyboard(navigateUp: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        ChiliCenteredAppToolbar(
            title = "ChiliKeyboard",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            })

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Input Field
            ChiliInputField(
                value = inputText,
                modifier = Modifier.padding(bottom = 16.dp),
                message = "Enter digits below:",
                placeholder = "Type here",
            ) { newValue ->
                inputText = newValue
            }

            // Keyboard
            ChiliKeyboard(
                enableInput = true,
                actionButtonText = "Forgot?",
                actionButtonType = ActionButtonType.TEXT,
                onActionTextClick = {
                    Toast.makeText(context, "Action Clicked", Toast.LENGTH_SHORT).show()
                },
                onInputChange = { newText ->
                    inputText = newText
                }
            )
        }
    }
}