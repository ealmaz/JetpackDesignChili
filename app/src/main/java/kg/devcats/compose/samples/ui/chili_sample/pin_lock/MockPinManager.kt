package kg.devcats.compose.samples.ui.chili_sample.pin_lock

class MockPinManager {
    private var pinCode = ""

    fun savePinCode(pin: String) { pinCode = pin }

    fun checkPin(pin: String) = pin == pinCode && pin.isNotEmpty()
}