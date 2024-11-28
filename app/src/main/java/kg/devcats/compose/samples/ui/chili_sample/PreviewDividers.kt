package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.divider.DividerView
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewDividers(navigateUp: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Dividers",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = { navigateUp() }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            DividerView(
                title = "Заголовок",
                subtitle = "Подзаголовок",
                actionText = "Кнопка",
                onActionClick = { println("Action clicked for Divider 1") },
                modifier = Modifier.padding(top = 16.dp)
            )
            DividerView(
                title = "Заголовок",
                subtitle = "Подзаголовок",
                actionText = "Кнопка",
                isNotificationVisible = true,
                endIconPainter = painterResource(id = kg.devcats.compose.samples.R.drawable.chili_ic_switcher),
                onEndIconClick = { context.showToast("End Icon clicked for Divider 2") },
                onActionClick = { context.showToast("Action clicked for Divider 2") },
                modifier = Modifier.padding(top = 16.dp)
            )
            DividerView(
                title = "Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок",
                subtitle = "Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок",
                titleSubtitleSpaceHeight = 6.dp,
                onActionClick = { println("Action clicked for Divider 1") },
                modifier = Modifier.padding(top = 16.dp)
            )
            DividerView(
                title = "Заголовок",
                actionText = "Кнопка",
                onActionClick = { println("Action clicked for Divider 2") },
                modifier = Modifier.padding(top = 16.dp)
            )
            DividerView(
                title = "Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок",
                onActionClick = { println("Action clicked for Divider 2") },
                modifier = Modifier.padding(top = 16.dp)
            )
            DividerView(
                title = "Заголовок",
                actionText = "Кнопка",
                isNotificationVisible = true,
                endIconPainter = painterResource(id = kg.devcats.compose.samples.R.drawable.chili_ic_switcher),
                onEndIconClick = { context.showToast("End Icon clicked for Divider 3") },
                onActionClick = { context.showToast("Action clicked for Divider 3") },
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}