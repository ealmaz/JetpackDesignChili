package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pin.ChiliPinInput
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType

@Composable
fun PreviewPinInput(navigateUp: () -> Unit) {
    val pinCode = remember { mutableStateOf("") }
    val code = "2323"
    val pinStatusState = remember { mutableStateOf(PinStatusType.None) }

    LaunchedEffect(pinCode.value) {
        if (pinCode.value.length >= code.length) {
            if (pinCode.value == code) {
                pinStatusState.value = PinStatusType.Success
            } else {
                pinStatusState.value = PinStatusType.Error
            }
        } else {
            pinStatusState.value = PinStatusType.None
        }
    }

    Scaffold(
        topBar = {
            ChiliCenteredAppToolbar(
                title = "Pin Input Field",
                isDividerVisible = true,
                isNavigationIconVisible = true,
                onNavigationIconClick = {
                    navigateUp.invoke()
                })
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ChiliPinInput(
                    modifier = Modifier.padding(top = 40.dp),
                    pinCode = pinCode,
                    pinStatusType = pinStatusState
                )

                Column(
                    modifier = Modifier
                        .padding(40.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "1" },
                            content = { Text("1") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "2" },
                            content = { Text("2") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "3" },
                            content = { Text("3") }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "4" },
                            content = { Text("4") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "5" },
                            content = { Text("5") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "6" },
                            content = { Text("6") }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "7" },
                            content = { Text("7") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "8" },
                            content = { Text("8") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value += "9" },
                            content = { Text("9") }
                        )
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { pinCode.value = pinCode.value.dropLast(1) },
                            content = { Text("<") }
                        )
                    }
                }
            }
        }
    )
}