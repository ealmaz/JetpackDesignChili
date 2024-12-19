package kg.devcats.compose.jetpack_chili.components.pin

sealed class PinStatusType {
    data class Error(val repeat: Int = 3, val duration: Int = 50) : PinStatusType()
    data class Success(val repeat: Int = 1, val duration: Int = 200) : PinStatusType()
    data object None : PinStatusType()
}