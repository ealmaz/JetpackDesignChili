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
    data object CardViewsScreen : Screens()

    @Serializable
    data object CellViewsScreen : Screens()

    @Serializable
    data object CommonViewsScreen : Screens()

    @Serializable
    data object InputFieldsScreen : Screens()

    @Serializable
    data object TextAppearancesScreen : Screens()

    @Serializable
    data object ToolbarsScreens : Screens()

    @Serializable
    data object PickerScreens : Screens()
}
