package kg.devcats.compose.samples.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object Main : Screens()

    @Serializable
    data object Buttons : Screens()

    @Serializable
    data object BottomSheets : Screens()

    @Serializable
    data object Cards : Screens()

    @Serializable
    data object Cells : Screens()

    @Serializable
    data object Common : Screens()

    @Serializable
    data object InputFields : Screens()

    @Serializable
    data object TextAppearances : Screens()

    @Serializable
    data object Toolbars : Screens()

    @Serializable
    data object AutoScrollBanners : Screens()

    @Serializable
    data object Snackbars : Screens()

    @Serializable
    data object Dialogs : Screens()

    @Serializable
    data object Dividers : Screens()
}
