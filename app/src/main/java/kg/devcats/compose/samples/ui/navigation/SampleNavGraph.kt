package kg.devcats.compose.samples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.samples.ui.chili_sample.BottomSheetsPreview
import kg.devcats.compose.samples.ui.chili_sample.CardViews
import kg.devcats.compose.samples.ui.chili_sample.CellViews
import kg.devcats.compose.samples.ui.chili_sample.ChiliSampleScreens
import kg.devcats.compose.samples.ui.chili_sample.CommonViews
import kg.devcats.compose.samples.ui.chili_sample.InputFields
import kg.devcats.compose.samples.ui.chili_sample.PreviewButtons
import kg.devcats.compose.samples.ui.chili_sample.TextAppearance
import kg.devcats.compose.samples.ui.chili_sample.Toolbars

@Composable
fun SamplesNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Screens.MainScreen) {
        composable<Screens.MainScreen> {
            ChiliSampleScreens(navController)
        }
        composable<Screens.TextAppearancesScreen> {
            TextAppearance { navController.navigateUp() }
        }
        composable<Screens.ButtonsScreen> {
            PreviewButtons { navController.navigateUp() }
        }
        composable<Screens.InputFieldsScreen> {
            InputFields { navController.navigateUp() }
        }
        composable<Screens.CellViewsScreen> {
            CellViews { navController.navigateUp() }
        }
        composable<Screens.CardViewsScreen> {
            CardViews { navController.navigateUp() }
        }
        composable<Screens.BottomSheetsScreen> {
            BottomSheetsPreview { navController.navigateUp() }
        }
        composable<Screens.CommonViewsScreen> {
            CommonViews { navController.navigateUp() }
        }
        composable<Screens.ToolbarsScreens> {
            Toolbars { navController.navigateUp() }
        }
    }

}