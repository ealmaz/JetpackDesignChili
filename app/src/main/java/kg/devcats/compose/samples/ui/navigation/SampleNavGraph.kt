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
import kg.devcats.compose.samples.ui.chili_sample.PreviewCommons
import kg.devcats.compose.samples.ui.chili_sample.PreviewInputFields
import kg.devcats.compose.samples.ui.chili_sample.PreviewSnackbars
import kg.devcats.compose.samples.ui.chili_sample.PreviewAutoScrollBanners
import kg.devcats.compose.samples.ui.chili_sample.PreviewButtons
import kg.devcats.compose.samples.ui.chili_sample.PreviewDialogs
import kg.devcats.compose.samples.ui.chili_sample.PreviewDividers
import kg.devcats.compose.samples.ui.chili_sample.TextAppearance
import kg.devcats.compose.samples.ui.chili_sample.Toolbars

@Composable
fun SamplesNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Screens.Main.toString()) {
        composable(Screens.Main.toString()) {
            ChiliSampleScreens(navController)
        }
        composable(Screens.TextAppearances.toString()) {
            TextAppearance { navController.navigateUp() }
        }
        composable(Screens.Buttons.toString()) {
            PreviewButtons { navController.navigateUp() }
        }
        composable(Screens.InputFields.toString()) {
            PreviewInputFields { navController.navigateUp() }
        }
        composable(Screens.Cells.toString()) {
            PreviewCells { navController.navigateUp() }
        }
        composable(Screens.Cards.toString()) {
            PreviewCards { navController.navigateUp() }
        }
        composable(Screens.BottomSheets.toString()) {
            BottomSheetsPreview { navController.navigateUp() }
        }
        composable(Screens.Common.toString()) {
            PreviewCommons { navController.navigateUp() }
        }
        composable(Screens.Toolbars.toString()) {
            Toolbars { navController.navigateUp() }
        }
        composable(Screens.AutoScrollBanners.toString()) {
            PreviewAutoScrollBanners { navController.navigateUp() }
        }
        composable(Screens.Snackbars.toString()) {
            PreviewSnackbars { navController.navigateUp() }
        }
        composable(Screens.Dialogs.toString()) {
            PreviewDialogs { navController.navigateUp() }
        }
        composable(Screens.Dividers.toString()) {
            PreviewDividers { navController.navigateUp() }
        }
    }

}