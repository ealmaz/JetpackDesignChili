package kg.devcats.compose.samples.ui.chili_sample.pin_lock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pin.ChiliPinInputField
import kg.devcats.compose.jetpack_chili.components.pin.PinItemConfig
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.black_1
import kg.devcats.compose.jetpack_chili.theme.black_7
import kg.devcats.compose.jetpack_chili.theme.blue_1
import kg.devcats.compose.jetpack_chili.theme.blue_3
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PreviewPinInput(navigateUp: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ChiliCenteredAppToolbar(
                modifier = Modifier.statusBarsPadding(),
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
                    .padding(contentPadding)
                    .background(Chili.color.surfaceBackground)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("1") },
                )
                HorizontalDivider()

                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("12") },
                )
                HorizontalDivider()

                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("123") },
                )
                HorizontalDivider()

                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("1234") },
                )
                HorizontalDivider()

                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("12345") },
                    maxSize = 5
                )
                HorizontalDivider()

                val successStatusState = remember { mutableStateOf<PinStatusType>(PinStatusType.Success()) }
                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("1234") },
                    maxSize = 4,
                    pinStatusType = successStatusState,
                    successAnimFinished = {
                        coroutineScope.launch {
                            successStatusState.value = PinStatusType.None
                            delay(1000)
                            successStatusState.value = PinStatusType.Success()
                        }
                    }
                )
                HorizontalDivider()

                val errorStatusState = remember { mutableStateOf<PinStatusType>(PinStatusType.Error()) }
                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    pinCode = remember { mutableStateOf("1234") },
                    maxSize = 4,
                    pinStatusType = errorStatusState,
                    errorAnimFinished = {
                        coroutineScope.launch {
                            errorStatusState.value = PinStatusType.None
                            delay(1000)
                            errorStatusState.value = PinStatusType.Error()
                        }
                    }
                )
                HorizontalDivider()

                ChiliPinInputField(
                    modifier = Modifier.padding(top = 16.dp, bottom = 40.dp),
                    lockItemModifier = Modifier.size(19.dp),
                    pinCode = remember { mutableStateOf("12") },
                    maxSize = 4,
                    pinItemConfig = PinItemConfig.create(
                        size = 19.dp,
                        borderWidth = 1.dp,
                        borderColor = black_7,
                        selectedColor = blue_1
                    )
                )
            }
        }
    )
}