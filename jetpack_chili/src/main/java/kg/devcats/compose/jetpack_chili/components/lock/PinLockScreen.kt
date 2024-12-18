package kg.devcats.compose.jetpack_chili.components.lock

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.keyboards.PinKeyboard
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.components.pin.ChiliPinInputField
import kg.devcats.compose.jetpack_chili.components.pin.PinStatusType
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PinLockScreen(
    modifier: Modifier = Modifier,
    versionText: String,
    titleText: String,
    code: String,
    onActionTextClick: () -> Unit = { },
    actionText: String? = null,
    isToolbarVisible: Boolean = false,
    onSuccess: () -> Unit = { },
    onError: () -> Unit = { },
    navigateUp: () -> Unit
) {
    val pinCode = remember { mutableStateOf("") }
    val pinStatusState = remember { mutableStateOf(PinStatusType.None) }
    val shouldBeCleared = remember { mutableStateOf(false) }

    LaunchedEffect(pinCode.value) {
        pinStatusState.value = when {
            pinCode.value.length == code.length -> if (pinCode.value == code) PinStatusType.Success else PinStatusType.Error
            else -> PinStatusType.None
        }
    }

    Scaffold(
        topBar = {
            if (isToolbarVisible) {
                ChiliCenteredAppToolbar(
                    title = "",
                    onNavigationIconClick = {navigateUp()}
                )
            }
        },
        content = { contentPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(Chili.color.surfaceBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    if (!isToolbarVisible) {
                        Image(
                            painter = painterResource(id = R.drawable.chili_ic_logo_moy_o),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        Text(
                            text = versionText,
                            style = Chili.typography.H13_Primary,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    } else Spacer(modifier = Modifier.height(53.dp))
                }

                Text(
                    text = titleText,
                    style = Chili.typography.H24_Primary_700,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 28.dp, start = 40.dp, end = 40.dp)
                )

                ChiliPinInputField(
                    modifier = Modifier.padding(top = 48.dp),
                    pinCode = pinCode,
                    pinStatusType = pinStatusState,
                    errorAnimFinished = {
                        pinCode.value = ""
                        shouldBeCleared.value = true
                        onError()
                    },
                    successAnimFinished = {
                        pinCode.value = ""
                        shouldBeCleared.value = true
                        onSuccess()
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                PinKeyboard(
                    actionButtonText = actionText,
                    onActionTextClick = onActionTextClick,
                    onInputChange = { newText ->
                        pinCode.value = newText
                        shouldBeCleared.value = false
                    },
                    isClearInputValue = shouldBeCleared.value,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }
        }
    )

}