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

    NavHost(navController = navController, startDestination = Screens.MainScreen.toString()) {
        composable(Screens.MainScreen.toString()) {
            ChiliSampleScreens(navController)
        }
        composable(Screens.TextAppearancesScreen.toString()) {
            TextAppearance { navController.navigateUp() }
        }
        composable(Screens.ButtonsScreen.toString()) {
            PreviewButtons { navController.navigateUp() }
        }
        composable(Screens.InputFieldsScreen.toString()) {
            InputFields { navController.navigateUp() }
        }
        composable(Screens.CellViewsScreen.toString()) {
            CellViews { navController.navigateUp() }
        }
        composable(Screens.CardViewsScreen.toString()) {
            CardViews { navController.navigateUp() }
        }
        composable(Screens.BottomSheetsScreen.toString()) {
            BottomSheetsPreview { navController.navigateUp() }
        }
        composable(Screens.CommonViewsScreen.toString()) {
            CommonViews { navController.navigateUp() }
        }
        composable(Screens.ToolbarsScreens.toString()) {
            Toolbars { navController.navigateUp() }
        }
    }

}