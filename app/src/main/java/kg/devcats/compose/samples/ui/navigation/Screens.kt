package kg.devcats.compose.samples.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object MainScreen : Screens()

    @Serializable
    data object ButtonsScreen : Screens()

    @Serializable
    data object BottomSheetsScreen : Screens()

    @Serializable
    data object CardsScreen : Screens()

    @Serializable
    data object CellsScreen : Screens()

    @Serializable
    data object CommonScreen : Screens()

    @Serializable
    data object InputFieldsScreen : Screens()

    @Serializable
    data object TextAppearancesScreen : Screens()

    @Serializable
    data object ToolbarsScreens : Screens()

    @Serializable
    data object AutoScrollBannersScreens : Screens()

    @Serializable
    data object SnackbarScreen : Screens()

    @Serializable
    data object DialogsScreen : Screens()

    @Serializable
    data object DividersScreen : Screens()

    @Serializable
    data object KeyboardNavGraph : Screens()

    @Serializable
    data object NumberKeyboardScreen : Screens()

    @Serializable
    data object PdfViewerNavGraph : Screens()

    @Serializable
    data object PinInputFieldScreen : Screens()

    @Serializable
    data object LockScreen : Screens()

    @Serializable
    data object AccountCardExamplesScreen : Screens()

    @Serializable
    data object TooltipScreen : Screens()

    @Serializable
    data object PullToRefreshScreen : Screens()
}

sealed class KeyboardScreens {
    @Serializable
    data object KeyboardSampleScreens : KeyboardScreens()

    @Serializable
    data object ChiliKeyboardScreen : KeyboardScreens()
}

sealed class PdfScreens {
    @Serializable
    data object PdfViewerSampleScreens : PdfScreens()

    @Serializable
    data object PdfViewerPortraitScreen : PdfScreens()

    @Serializable
    data object PdfViewerAlbumScreen : PdfScreens()

    @Serializable
    data object PdfViewerWithShareScreen : PdfScreens()

    @Serializable
    data object PdfViewerWithoutZoomScreen : PdfScreens()

    @Serializable
    data object PdfViewerOneElementScreen : PdfScreens()

    @Serializable
    data object ActionButtonPdfViewerScreenMultiPages : PdfScreens()

    @Serializable
    data object ActionButtonPdfViewerScreenOnePage : PdfScreens()
}

sealed class LockScreens {
    @Serializable
    data object PinLockSampleScreen : LockScreens()

    @Serializable
    data object CreatePinCodeScreen: LockScreens()

    @Serializable
    data object LoginPinCodeScreen : LockScreens()

    @Serializable
    data object CustomPinCodeScreen : LockScreens()
}