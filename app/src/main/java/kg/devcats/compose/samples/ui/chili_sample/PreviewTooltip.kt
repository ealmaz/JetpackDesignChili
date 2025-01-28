package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.tooltip.Tooltip
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PreviewTooltip(
    navigateUp: () -> Unit,
) {
    val isTooltipVisible = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.screenBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Tooltips",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            }
        )

        Button(
            enabled = !isTooltipVisible.value,
            onClick = {
                isTooltipVisible.value = true
                      },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Show tooltip")
        }

        if (isTooltipVisible.value)
        Tooltip(
            text = "Ваши бонусные карты",
            onDismiss = {
                coroutineScope.launch {
                    delay(500)
                    isTooltipVisible.value = false
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}




