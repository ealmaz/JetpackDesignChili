package kg.devcats.compose.samples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.samples.ui.chili_sample.BottomSheetsPreview
import kg.devcats.compose.samples.ui.chili_sample.PreviewCardScreen
import kg.devcats.compose.samples.ui.chili_sample.PreviewCellScreen
import kg.devcats.compose.samples.ui.chili_sample.ChiliSampleScreens
import kg.devcats.compose.samples.ui.chili_sample.PreviewCommonScreen
import kg.devcats.compose.samples.ui.chili_sample.PreviewInputFieldScreen
import kg.devcats.compose.samples.ui.chili_sample.PreviewSnackbars
import kg.devcats.compose.samples.ui.chili_sample.PreviewAutoScrollBanners
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
            PreviewInputFieldScreen { navController.navigateUp() }
        }
        composable(Screens.CellViewsScreen.toString()) {
            PreviewCellScreen { navController.navigateUp() }
        }
        composable(Screens.CardViewsScreen.toString()) {
            PreviewCardScreen { navController.navigateUp() }
        }
        composable(Screens.BottomSheetsScreen.toString()) {
            BottomSheetsPreview { navController.navigateUp() }
        }
        composable(Screens.CommonViewsScreen.toString()) {
            PreviewCommonScreen { navController.navigateUp() }
        }
        composable(Screens.ToolbarsScreens.toString()) {
            Toolbars { navController.navigateUp() }
        }
        composable(Screens.AutoScrollBannersScreens.toString()) {
            PreviewAutoScrollBanners { navController.navigateUp() }
        }
        composable(Screens.SnackbarScreen.toString()) {
            PreviewSnackbars { navController.navigateUp() }
        }
    }

}