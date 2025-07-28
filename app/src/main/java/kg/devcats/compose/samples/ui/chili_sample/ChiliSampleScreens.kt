package kg.devcats.compose.samples.ui.chili_sample

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.SampleToolbarMenu
import kg.devcats.compose.samples.StoryActivity
import kg.devcats.compose.samples.ui.navigation.Screens

@Composable
fun ChiliSampleScreens(
    navController: NavController? = null,
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .background(Chili.color.surfaceBackground)
        .fillMaxSize()
        .statusBarsPadding()
    ) {
        ChiliCenteredAppToolbar(
            title = "Compose DesignChili",
            isNavigationIconVisible = false,
            isDividerVisible = true,
            endFrame = { SampleToolbarMenu() }
        )
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp)) {
            ChiliPrimaryButton(text = "Typography", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.TextAppearancesScreen.toString())
            }
            ChiliPrimaryButton(text = "Buttons", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.ButtonsScreen.toString())
            }

            ChiliPrimaryButton(text = "Input fields", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.InputFieldsScreen.toString())
            }

            ChiliPrimaryButton(text = "Cell views", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.CellsScreen.toString())
            }

            ChiliPrimaryButton(text = "Toolbars", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.ToolbarsScreens.toString())
            }

            ChiliPrimaryButton(text = "Common views", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.CommonScreen.toString())
            }

            ChiliPrimaryButton(text = "Bottom sheets", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.BottomSheetsScreen.toString())
            }

            ChiliPrimaryButton(text = "Cards", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.CardsScreen.toString())
            }

            ChiliPrimaryButton(text = "Stories", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                context.startActivity(Intent(context, StoryActivity::class.java))
            }

            ChiliPrimaryButton(text = "Auto Scroll Banners", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.AutoScrollBannersScreens.toString())
            }

            ChiliPrimaryButton(text = "Snackbar", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.SnackbarScreen.toString())
            }

            ChiliPrimaryButton(text = "Dialogs", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.DialogsScreen.toString())
            }

            ChiliPrimaryButton(text = "Divider", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.DividersScreen.toString())
            }

            ChiliPrimaryButton(text = "PdfViewer", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.PdfViewerNavGraph.toString())
            }

            ChiliPrimaryButton(text = "Keyboards", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.KeyboardNavGraph.toString())
            }

            ChiliPrimaryButton(text = "Pin Input Field", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.PinInputFieldScreen.toString())
            }

            ChiliPrimaryButton(text = "Lock Screen", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.LockScreen.toString())
            }

            ChiliPrimaryButton(text = "Tooltip", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.TooltipScreen.toString())
            }

            ChiliPrimaryButton(text = "Pull To Refresh", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                navController?.navigate(Screens.PullToRefreshScreen.toString())
            }

            Spacer(
                Modifier.windowInsetsBottomHeight(
                    WindowInsets.systemBars
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliSmapleScreens() {
    ChiliSampleScreens()
}
