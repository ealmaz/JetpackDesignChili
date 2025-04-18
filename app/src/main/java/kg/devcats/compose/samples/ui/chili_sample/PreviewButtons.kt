package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ButtonSize
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliCustomButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliDoubledButtons
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliLoaderButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliQuickActionButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliSecondaryButton
import kg.devcats.compose.jetpack_chili.components.buttons.primaryButtonColors
import kg.devcats.compose.jetpack_chili.components.cells.DetailedInfoCell
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewButtons(navigateUp: () -> Unit,) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.surfaceBackground)) {
        ChiliCenteredAppToolbar(title = "Buttons", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)) {

            var isLoading by remember { mutableStateOf(false) }

            ChiliLoaderButton(text = "Loader button", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), isLoading = isLoading) {
                isLoading = true
            }

            ChiliComponentButton(text = "Stop loader", modifier = Modifier.align(Alignment.End)) {
                isLoading = false
            }

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(text = "Primary buttons and their states", modifier = Modifier.fillMaxWidth())
            PrimaryButtons()

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(text = "Secondary buttons and their states", modifier = Modifier.fillMaxWidth())
            SecondaryButtons()

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            Text(text = "Additional buttons and their states", modifier = Modifier.fillMaxWidth())
            AdditionalButtons()

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            ChiliComponentButton(text = "Component button",) {}
            ChiliComponentButton(text = "Component button", modifier = Modifier
                .padding(top = 4.dp), enabled = false) {}

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            val context = LocalContext.current
            Row {
                ChiliQuickActionButton(
                    text = "ChiliQuickActionButton",
                    iconResource = R.drawable.chili_ic_bell,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        context.showToast("ChiliQuickActionButton")
                    }
                )
                ChiliQuickActionButton(
                    text = "ChiliQuickActionButton",
                    iconResource = R.drawable.chili_ic_bell,
                    modifier = Modifier.weight(1f),
                    isEnabled = false,
                    onClick = {
                        context.showToast("ChiliQuickActionButton")
                    }
                )
            }

            Divider(Modifier.padding(top = 16.dp, bottom = 16.dp))

            ChiliCustomButton(
                text = "Custom button",
                colors = primaryButtonColors().copy(containerColor = Chili.color.alertWarningContent),
                onClick = { context.showToast() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box (modifier = Modifier
                .background(Chili.color.screenBackground)
                .padding(16.dp)){
                Column(modifier = Modifier
                    .clip(Chili.shapes.RoundedCornerShape)
                    .background(Chili.color.cellViewBackground)) {
                    DetailedInfoCell(icon = painterResource(R.drawable.chili_ic_documents_green), title = "Double buttons", value = "6 200 c", subTitle = "Below this cell")
                    ChiliDoubledButtons(startButtonText = "Продлить", endButtonText = "Погасить")
                }
            }
        }
    }
}

@Composable
fun PrimaryButtons() {
    var isLoadingPrimary by remember { mutableStateOf(false) }

    Column {
        ChiliPrimaryButton(
            icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFmr0SDe-UnpQ6zuObL3Dn0QZOdDTRbkcPQ&s",
            text = "Primary button c url", isLoading = isLoadingPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            isLoadingPrimary = !isLoadingPrimary
        }
        ChiliPrimaryButton(
            icon = R.drawable.chili_ic_documents_green,
            text = "Primary button c drawable res",
            isLoading = isLoadingPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            isLoadingPrimary = !isLoadingPrimary
        }
        ChiliPrimaryButton(
            icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFmr0SDe-UnpQ6zuObL3Dn0QZOdDTRbkcPQ&s",
            text = "Small btn, url icon",
            isLoading = isLoadingPrimary,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoadingPrimary = !isLoadingPrimary
        }
        ChiliPrimaryButton(
            icon = R.drawable.chili_ic_documents_green,
            text = "Small btn, drawable",
            isLoading = isLoadingPrimary,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoadingPrimary = !isLoadingPrimary
        }

        ChiliPrimaryButton(
            text = "Small btn w/o icon",
            isLoading = isLoadingPrimary,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoadingPrimary = !isLoadingPrimary
        }

        ChiliPrimaryButton(text = "Primary button", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp), enabled = false) {}

        ChiliComponentButton(text = "Stop loader", modifier = Modifier.align(Alignment.End)) {
            isLoadingPrimary = false
        }
    }
}

@Composable
fun SecondaryButtons() {
    var isLoading by remember { mutableStateOf(false) }

    Column {
        ChiliSecondaryButton(
            icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFmr0SDe-UnpQ6zuObL3Dn0QZOdDTRbkcPQ&s",
            text = "Secondary button c url", isLoading = isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            isLoading = !isLoading
        }
        ChiliSecondaryButton(
            icon = R.drawable.chili_ic_documents_green,
            text = "Secondary button c drawable res",
            isLoading = isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            isLoading = !isLoading
        }
        ChiliSecondaryButton(
            icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFmr0SDe-UnpQ6zuObL3Dn0QZOdDTRbkcPQ&s",
            text = "Small btn, url icon",
            isLoading = isLoading,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoading = !isLoading
        }
        ChiliSecondaryButton(
            icon = R.drawable.chili_ic_documents_green,
            text = "Small btn, drawable",
            isLoading = isLoading,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoading = !isLoading
        }

        ChiliSecondaryButton(
            text = "Small btn w/o icon",
            isLoading = isLoading,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoading = !isLoading
        }

        ChiliSecondaryButton(
            text = "Secondary button", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), enabled = false) {}

        ChiliComponentButton(text = "Stop loader", modifier = Modifier.align(Alignment.End)) {
            isLoading = false
        }
    }
}

@Composable
fun AdditionalButtons() {
    var isLoading by remember { mutableStateOf(false) }
    Column {
        ChiliAdditionalButton(
            startIcon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFmr0SDe-UnpQ6zuObL3Dn0QZOdDTRbkcPQ&s",
            text = "Additional button c url", isLoading = isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            isLoading = !isLoading
        }
        ChiliAdditionalButton(
            startIcon = R.drawable.chili_ic_documents_green,
            text = "Additional button c drawable res",
            isLoading = isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            isLoading = !isLoading
        }
        ChiliAdditionalButton(
            startIcon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFmr0SDe-UnpQ6zuObL3Dn0QZOdDTRbkcPQ&s",
            text = "Small btn, url icon",
            isLoading = isLoading,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoading = !isLoading
        }
        ChiliAdditionalButton(
            startIcon = R.drawable.chili_ic_documents_green,
            text = "Small btn, drawable",
            isLoading = isLoading,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoading = !isLoading
        }

        ChiliAdditionalButton(
            text = "Small btn w/o icon",
            isLoading = isLoading,
            buttonSize = ButtonSize.SMALL,
            modifier = Modifier
                .width(150.dp)
                .padding(top = 8.dp)

        ) {
            isLoading = !isLoading
        }

        ChiliAdditionalButton(text = "Additional button", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            enabled = false) {}

        ChiliAdditionalButton(text = "Additional button with end icon", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            endIconPainter = painterResource(id = R.drawable.chili_ic_documents_green),
            endIconModifier = Modifier.padding(start = 8.dp)
        ) {}

        ChiliComponentButton(text = "Stop loader", modifier = Modifier.align(Alignment.End)) {
            isLoading = false
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPreviewButtons() {
    PreviewButtons({})
}