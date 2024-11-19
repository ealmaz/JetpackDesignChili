package kg.devcats.compose.samples.ui.chili_sample

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliLoaderButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliQuickActionButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliSecondaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PreviewButtons(navigateUp: () -> Unit,) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.surfaceBackground)) {
        ChiliCenteredAppToolbar(title = "Buttons", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)) {

            var isLoading by remember { mutableStateOf(false) }

            ChiliLoaderButton(text = "Loader button", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), isLoading = isLoading) {
                isLoading = true
            }

            ChiliComponentButton(text = "Stop loader", modifier = Modifier.align(Alignment.End)) {
                isLoading = false
            }

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            ChiliPrimaryButton(text = "Primary button", modifier = Modifier.fillMaxWidth()) {}
            ChiliPrimaryButton(text = "Primary button", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), enabled = false) {}

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            ChiliSecondaryButton(text = "Secondary button", modifier = Modifier.fillMaxWidth()) {}
            ChiliSecondaryButton(text = "Secondary button", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), enabled = false) {}

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            ChiliAdditionalButton(text = "Additional button", modifier = Modifier.fillMaxWidth()) {}
            ChiliAdditionalButton(text = "Additional button", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), enabled = false) {}

            ChiliAdditionalButton(text = "Additional button with icon", modifier = Modifier.fillMaxWidth().padding(top = 16.dp), endIconPainter = painterResource(
                id = R.drawable.chili_ic_documents_green), endIconModifier = Modifier.padding(start = 8.dp)) {}

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            ChiliComponentButton(text = "Component button",) {}
            ChiliComponentButton(text = "Component button", modifier = Modifier
                .padding(top = 4.dp), enabled = false) {}

            Row {
                val context = LocalContext.current
                ChiliQuickActionButton(
                    text = "ChiliQuickActionButton",
                    iconResource = R.drawable.chili_ic_bell,
                    modifier = Modifier.weight(1f),
                    coroutineScope = rememberCoroutineScope(),
                    onClick = {
                        Toast.makeText(context, "Clicked ChiliQuickActionButton", Toast.LENGTH_SHORT).show()
                    })
                ChiliQuickActionButton(
                    text = "ChiliQuickActionButton",
                    iconResource = R.drawable.chili_ic_bell,
                    modifier = Modifier.weight(1f),
                    coroutineScope = rememberCoroutineScope(),
                    enabled = false,
                    onClick = {
                        Toast.makeText(context, "Clicked ChiliQuickActionButton", Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPreviewButtons() {
    PreviewButtons({})
}