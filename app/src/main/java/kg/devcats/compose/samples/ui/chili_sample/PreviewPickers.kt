package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.modals.picker.WheelDatePicker
import kg.devcats.compose.jetpack_chili.theme.Chili

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PickerPreview(navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.screenBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Pickers",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp, bottom = 64.dp)
        ) {
            WheelDatePicker()
        }
    }
}
