package kg.devcats.compose.jetpack_chili.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.input_fields.ChiliPlainInputField
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliSearchAppToolbar(
    modifier: Modifier = Modifier,
    isSearchMode: Boolean,
    searchQuery: TextFieldValue,
    onSearchQueryChange: (TextFieldValue) -> Unit = {},
    onSearchModeChange: (Boolean) -> Unit = {},
    placeholder: String? = null,
    navigationIcon: Painter = painterResource(id = R.drawable.chili4_ic_back_arrow_rounded),
    isNavigationIconVisible: Boolean = true,
    onNavigationIconClick: () -> Unit = {},
    title: String = "",
    backgroundColor: Color = Chili.color.toolbarBackground,
    isDividerVisible: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
) {
    Surface(modifier = modifier, color = backgroundColor) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val showNavigationIcon = isSearchMode || isNavigationIconVisible

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (showNavigationIcon) {
                        IconButton(
                            painter = navigationIcon,
                            contentDescription = "Back",
                            onClick = {
                                if (isSearchMode) {
                                    onSearchModeChange(false)
                                    onSearchQueryChange(TextFieldValue())
                                } else {
                                    onNavigationIconClick()
                                }
                            }
                        )
                    } else {
                        Spacer(modifier = Modifier.size(24.dp))
                    }
                }

                Box(
                    modifier = Modifier.weight(5f),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSearchMode) {
                        ChiliPlainInputField(
                            value = searchQuery,
                            onValueChange = onSearchQueryChange,
                            placeholder = placeholder,
                            isInputCenteredAlign = false,
                            focusRequester = focusRequester,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                        )
                    } else {
                        Text(
                            modifier = Modifier.padding(vertical = 18.dp),
                            text = title,
                            style = Chili.typography.H16_Primary_500
                        )
                    }
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (isSearchMode) {
                        if (searchQuery.text.isNotEmpty()) {
                            IconButton(
                                painter = painterResource(id = R.drawable.chili_ic_close),
                                contentDescription = "Clear Search",
                                onClick = { onSearchQueryChange(TextFieldValue()) }
                            )
                        } else {
                            Spacer(modifier = Modifier.size(24.dp))
                        }
                    } else {
                        IconButton(
                            painter = painterResource(id = R.drawable.chili4_ic_search),
                            contentDescription = "Search",
                            onClick = { onSearchModeChange(true) }
                        )
                    }
                }
            }

            if (isDividerVisible) HorizontalDivider()
        }
    }
}

@Composable
private fun IconButton(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .padding(16.dp)
            .size(24.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false)
            )
    )
}


@Preview
@Composable
fun PreviewChiliSearchAppToolbar_NotCentered_NoNav() {
    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }

    ChiliSearchAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        isSearchMode = isSearchMode,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        onSearchModeChange = { isSearchMode = it },
        title = "Header",
        isNavigationIconVisible = false,
        placeholder = "Search..."
    )
}

@Preview
@Composable
fun PreviewChiliSearchAppToolbar_Centered_WithNav() {
    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }

    ChiliSearchAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        isSearchMode = isSearchMode,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        onSearchModeChange = { isSearchMode = it },
        title = "Header",
        isNavigationIconVisible = true,
        placeholder = "Search..."
    )
}

@Preview
@Composable
fun PreviewChiliSearchAppToolbar_SearchMode_NoNav() {
    var isSearchMode by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("Typed text")) }

    ChiliSearchAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        isSearchMode = isSearchMode,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        onSearchModeChange = { isSearchMode = it },
        title = "Header",
        isNavigationIconVisible = false,
        placeholder = "Search..."
    )
}