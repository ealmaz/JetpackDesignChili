package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.divider.Divider
import kg.devcats.compose.jetpack_chili.components.divider.TitledDivider
import kg.devcats.compose.jetpack_chili.components.divider.TitledDividerActionType
import kg.devcats.compose.jetpack_chili.components.divider.TitledDividerDefaults
import kg.devcats.compose.jetpack_chili.components.divider.TitledDividerWithAction
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
            .verticalScroll(rememberScrollState())
    ) {
        ChiliCenteredAppToolbar(
            title = "Dividers",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = { navigateUp() }
        )
        Column(modifier = Modifier.padding(16.dp)) {

            ShadowRoundedBox {
                Text("Simple Dividers (title, subtitle, actionText):", style = Chili.typography.H24_Primary_700, modifier = Modifier.padding(horizontal = 16.dp))
            }

            Divider(
                title = "Заголовок",
                subtitle = "Подзаголовок",
                actionText = "Кнопка",
                onActionClick = { println("Action clicked for Divider 1") },
                modifier = Modifier.padding(top = 16.dp)
            )
            Divider(
                title = "Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок",
                subtitle = "Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок",
                titleSubtitleSpaceHeight = 6.dp,
                onActionClick = { println("Action clicked for Divider 1") },
                modifier = Modifier.padding(top = 16.dp)
            )
            Divider(
                title = "Заголовок",
                actionText = "Кнопка",
                onActionClick = { println("Action clicked for Divider 2") },
                modifier = Modifier.padding(top = 16.dp)
            )

            ShadowRoundedBox (modifier = Modifier.padding(top = 32.dp)) {
                Text("TitledDivider (custom):", style = Chili.typography.H24_Primary_700, modifier = Modifier.padding(horizontal = 16.dp))
            }

            TitledDivider(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок"
                )
            )
            TitledDividerWithAction(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок"
                ),
                endActionList = listOf(
                    TitledDividerActionType.Text(
                        text = "Кнопка",
                        onActionClick = { context.showToast("Text") }
                    )
                )
            )
            TitledDivider(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    isNotificationVisible = true,
                    isShevronIsVisible = true
                )
            )
            TitledDividerWithAction(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    isShevronIsVisible = true
                ),
                endActionList = listOf(
                    TitledDividerActionType.Icon(
                        modifier = Modifier,
                        icon = painterResource(id = kg.devcats.compose.samples.R.drawable.chili_ic_switcher),
                        onActionClick = { context.showToast("Icon") }
                    ),
                    TitledDividerActionType.Text(
                        modifier = Modifier,
                        text = "Кнопка",
                        onActionClick = { context.showToast("Text") }
                    )
                )
            )
            TitledDividerWithAction(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок",
                    subtitle = "Подзаголовок"
                ),
                endActionList = listOf(
                    TitledDividerActionType.Text(
                        modifier = Modifier,
                        text = "Кнопка",
                        onActionClick = { context.showToast("Text") }
                    )
                )
            )
            TitledDividerWithAction(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок",
                    subtitle = "Подзаголовок"
                ),
                endActionList = listOf(
                    TitledDividerActionType.Icon(
                        modifier = Modifier,
                        icon = painterResource(kg.devcats.compose.samples.R.drawable.chili_ic_switcher),
                        onActionClick = { context.showToast("Icon") }
                    )
                )
            )
            TitledDivider(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок",
                    subtitle = "Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок",
                )
            )

            TitledDividerWithAction(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок Заголовок",
                    subtitle = "Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок Подзаголовок",
                ),
                endActionList = listOf(
                    TitledDividerActionType.Text(
                        modifier = Modifier,
                        text = "Кнопка",
                        onActionClick = { context.showToast("Text Click") }
                    )
                )
            )

            TitledDivider(
                modifier = Modifier.padding(top = 16.dp),
                params = TitledDividerDefaults.titledDividerParams(
                    title = "Заголовок"
                ),
                startPlaceholder = {
                    Image(
                        modifier = Modifier.padding(end = 8.dp),
                        painter = painterResource(R.drawable.chili_ic_documents_green),
                        contentDescription = ""
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}