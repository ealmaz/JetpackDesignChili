package kg.devcats.compose.jetpack_chili.components.swipe_refresh

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kg.devcats.compose.jetpack_chili.theme.Chili

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiliPullToRefreshBox(
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    indicatorContainerColor: Color = Chili.color.contentPrimary,
    indicatorColor: Color = Chili.color.loaderAccentColor,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {

    PullToRefreshBox(
        state = state,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = indicatorContainerColor,
                color = indicatorColor,
                state = state
            )
        },
    ) {
        Box(modifier = modifier) {
            content()
        }
    }
}

