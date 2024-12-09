package kg.devcats.compose.samples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.samples.ui.chili_sample.BottomSheetsPreview
import kg.devcats.compose.samples.ui.chili_sample.PreviewCards
import kg.devcats.compose.samples.ui.chili_sample.PreviewCells
import kg.devcats.compose.samples.ui.chili_sample.ChiliSampleScreens
import kg.devcats.compose.samples.ui.chili_sample.PreviewCommon
import kg.devcats.compose.samples.ui.chili_sample.PreviewInputFields
import kg.devcats.compose.samples.ui.chili_sample.PreviewSnackbars
import kg.devcats.compose.samples.ui.chili_sample.PreviewAutoScrollBanners
import kg.devcats.compose.samples.ui.chili_sample.PreviewButtons
import kg.devcats.compose.samples.ui.chili_sample.PreviewDialogs
import kg.devcats.compose.samples.ui.chili_sample.PreviewDividers
import kg.devcats.compose.samples.ui.chili_sample.PreviewPdfViewerNavGraph
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
            PreviewInputFields { navController.navigateUp() }
        }
        composable(Screens.CellsScreen.toString()) {
            PreviewCells { navController.navigateUp() }
        }
        composable(Screens.CardsScreen.toString()) {
            PreviewCards { navController.navigateUp() }
        }
        composable(Screens.BottomSheetsScreen.toString()) {
            BottomSheetsPreview { navController.navigateUp() }
        }
        composable(Screens.CommonScreen.toString()) {
            PreviewCommon { navController.navigateUp() }
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
        composable(Screens.DialogsScreen.toString()) {
            PreviewDialogs { navController.navigateUp() }
        }
        composable(Screens.DividersScreen.toString()) {
            PreviewDividers { navController.navigateUp() }
        }
        composable(Screens.PdfViewerNavGraph.toString()) {
            PreviewPdfViewerNavGraph(navController = navController)
        }
    }

}