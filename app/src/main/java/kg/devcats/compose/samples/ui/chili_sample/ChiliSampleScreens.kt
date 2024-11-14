package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.navigation.Screens

@Composable
fun ChiliSampleScreens(
    navController: NavController? = null,
) {
    Column(modifier = Modifier
        .background(Chili.color.surfaceBackground)
        .fillMaxSize()) {
        ChiliCenteredAppToolbar(
            title = "Compose DesignChili",
            isNavigationIconVisible = false,
            isDividerVisible = true,
        )
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)) {
            ChiliPrimaryButton(text = "Typography", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.TextAppearancesScreen)
            }
            ChiliPrimaryButton(text = "Buttons", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.ButtonsScreen)
            }

            ChiliPrimaryButton(text = "Input fields", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.InputFieldsScreen)
            }

            ChiliPrimaryButton(text = "Cell views", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.CellViewsScreen)
            }

            ChiliPrimaryButton(text = "Toolbars", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.ToolbarsScreens)
            }

            ChiliPrimaryButton(text = "Common views", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.CommonViewsScreen)
            }

            ChiliPrimaryButton(text = "Bottom sheets", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.BottomSheetsScreen)
            }

            ChiliPrimaryButton(text = "Cards", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.CardViewsScreen)
            }
        }
    }
    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliSmapleScreens() {
    ChiliSampleScreens()
}
