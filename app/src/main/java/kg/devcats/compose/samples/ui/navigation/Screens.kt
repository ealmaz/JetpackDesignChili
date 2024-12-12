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
    data object PinInputFieldScreen : Screens()
}
