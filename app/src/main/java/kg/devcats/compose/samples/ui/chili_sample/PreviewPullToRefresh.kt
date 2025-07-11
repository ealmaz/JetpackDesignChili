package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliAppToolbar
import kg.devcats.compose.jetpack_chili.components.swipe_refresh.ChiliPullToRefreshBox
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PreviewPullToRefresh(
    navigateUp: () -> Unit,
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(10000)
            isRefreshing = false
        }
    }

    Column {
        ChiliAppToolbar(title = "Pull To refresh...",
            endFrame = { SampleToolbarMenu() }, onNavigationIconClick = { navigateUp() })

        ChiliPullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Chili.color.screenSecondary)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ChiliPrimaryButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = "Stop loading"
                ) {
                    isRefreshing = false
                }
            }

        }

    }

}

