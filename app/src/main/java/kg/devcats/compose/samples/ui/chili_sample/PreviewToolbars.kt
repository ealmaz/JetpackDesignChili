package kg.devcats.compose.samples.ui.chili_sample

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ripple
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliAppToolbar
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.BonusTag
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliSearchAppToolbar
import kg.devcats.compose.samples.LocalValueShimmering
import kg.devcats.compose.samples.SampleToolbarMenu

@Composable
fun Toolbars(
    navigateUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.screenBackground)
    ) {
        ChiliCenteredAppToolbar(
            modifier = Modifier.statusBarsPadding(),
            title = "Toolbars",
            endFrame = { SampleToolbarMenu() },
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
                .navigationBarsPadding()
        ) {

            ChiliAppToolbar(
                title = "Simple toolbar",
                isDividerVisible = true,
                isNavigationIconVisible = false,
                isLoading = LocalValueShimmering.current
            )

            Spacer(modifier = Modifier.height(8.dp))

            ChiliAppToolbar(
                title = "Simple toolbar",
                isLoading = LocalValueShimmering.current
            )

            Spacer(modifier = Modifier.height(8.dp))

            ChiliAppToolbar(
                title = "Simple toolbar",
                navigationIcon = painterResource(id = R.drawable.chili_ic_close),
                isLoading = LocalValueShimmering.current
            )

            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(
                title = "Centered toolbar",
                navigationIcon = painterResource(id = R.drawable.chili_ic_close),
                isLoading = LocalValueShimmering.current
            )


            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(
                title = "End icon toolbar",
                navigationIcon = painterResource(id = R.drawable.chili_ic_close),
                isLoading = LocalValueShimmering.current,
                endFrame = {
                    Image(
                        painter = painterResource(id = R.drawable.chili_ic_documents_green),
                        contentDescription = ""
                    )
                })

            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(
                title = "Start icon toolbar",
                navigationIcon = painterResource(id = R.drawable.chili_ic_close),
                isLoading = LocalValueShimmering.current,
                startFrame = {
                    Image(
                        painter = painterResource(id = R.drawable.chili_ic_documents_green),
                        contentDescription = ""
                    )
                })

            Spacer(modifier = Modifier.height(8.dp))

            ChiliAppToolbar(
                title = "Transparent toolbar",
                isDividerVisible = true,
                isNavigationIconVisible = false,
                isLoading = LocalValueShimmering.current,
                backgroundColor = Color.Transparent
            )

            Spacer(modifier = Modifier.height(8.dp))

            var expanded by remember { mutableStateOf(false) }

            ChiliCenteredAppToolbar(
                title = "End icon toolbar",
                isNavigationIconVisible = false,
                isLoading = LocalValueShimmering.current,
                endFrame = {
                    Image(painter = painterResource(id = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_more),
                        contentDescription = "",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(bounded = false)
                        ) {
                            expanded = true

                        })
                    popupMenu(expanded) { expanded = false }
                })

            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(
                title = "ToolbarWithBonusTag",
                isNavigationIconVisible = true,
                backgroundColor = Color.Transparent,
                isLoading = LocalValueShimmering.current,
                endFrame = {
                    BonusTag(modifier = Modifier, text = "Бонусы: 500")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(
                title = "ToolbarWithBonusTag",
                isNavigationIconVisible = true,
                backgroundColor = Color.Transparent,
                isLoading = LocalValueShimmering.current,
                endFrame = {
                    BonusTag(modifier = Modifier, enabled = false, text = "Бонусы")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            var isSearchMode by remember { mutableStateOf(false) }
            var searchQuery by remember { mutableStateOf(TextFieldValue()) }
            val focusRequester = remember { FocusRequester() }

            val focusManager = LocalFocusManager.current

            BackHandler(enabled = isSearchMode) {
                isSearchMode = false
                searchQuery = TextFieldValue()
                focusManager.clearFocus()
            }

            ChiliSearchAppToolbar(
                modifier = Modifier.fillMaxWidth(),
                isSearchMode = isSearchMode,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onSearchModeChange = { isSearchMode = it },
                title = "SearchViewToolbar",
                isNavigationIconVisible = true,
                onNavigationIconClick = { navigateUp.invoke() },
                placeholder = "Search...",
                focusRequester = focusRequester
            )

            LaunchedEffect(isSearchMode) {
                if (isSearchMode) {
                    focusRequester.requestFocus()
                }
            }
        }
    }
}

@Composable
fun popupMenu(expanded: Boolean, onDismissRequest: () -> Unit) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(Chili.color.surfaceBackground)
    ) {
        DropdownMenuItem(
            text = { Text("Sample 1") },
            onClick = { onDismissRequest() }
        )
        DropdownMenuItem(
            text = { Text("Sample 2") },
            onClick = { onDismissRequest() /* Handle settings! */ }
        )
        Divider()
        DropdownMenuItem(
            text = { Text("Sample 3") },
            onClick = { onDismissRequest()/* Handle send feedback! */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewToolbars() {
    Toolbars({})
}