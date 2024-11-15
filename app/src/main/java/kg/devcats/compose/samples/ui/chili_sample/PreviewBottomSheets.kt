package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliBottomSheetContainer
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliChooseActionBSAction
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliChooseActionBottomSheet
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliDetailActionsBottomSheet
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliDetailBottomSheet
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliInfoBottomSheet
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.green_3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetsPreview(
    navigateUp: () -> Unit,
) {

    var containerBS by remember { mutableStateOf(false) }

    ChiliBottomSheetContainer(
        isShown = containerBS,
        onDismissRequest = {containerBS = false}
    ) {
        Box(modifier = Modifier
            .background(green_3)
            .fillMaxWidth()
            .height(250.dp), contentAlignment = Alignment.Center) {
            Text(text = "Bottom sheet container", style = Chili.typography.H20_Primary)
        }
    }

    var detailBsState by remember { mutableStateOf(false) }

    ChiliDetailBottomSheet(
        isShown = detailBsState,
        icon = painterResource(id = R.drawable.chili_ic_documents_green),
        headerText = "Заголовок содержит в себе до 60 символов и может быть в 2 строки",
        bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
        secondaryButtonText = "Ясно",
        onPrimaryButtonClick = { detailBsState = false },
        onSecondaryButtonClick = { detailBsState = false },
        primaryButtonText = "Понятно",
        onDismissRequest = {detailBsState = false}
    )

    var detailActionBsState by remember { mutableStateOf(false) }

    ChiliDetailActionsBottomSheet(
        isShown = detailActionBsState,
        icon = painterResource(id = R.drawable.chili_ic_documents_green),
        bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
        secondaryButtonText = "Ясно",
        onPrimaryButtonClick = { detailActionBsState = false },
        onSecondaryButtonClick = { detailActionBsState = false },
        primaryButtonText = "Понятно",
        onDismissRequest = {detailActionBsState = false}
    )

    var infoBottomSheetState by remember { mutableStateOf(false) }

    ChiliInfoBottomSheet(
        isShown = infoBottomSheetState,
        icon = painterResource(id = R.drawable.chili_ic_documents_green),
        bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
        secondaryButtonText = "Ясно",
        onPrimaryButtonClick = { infoBottomSheetState = false },
        onSecondaryButtonClick = { infoBottomSheetState = false },
        primaryButtonText = "Понятно",
        onDismissRequest = {infoBottomSheetState = false}
    )


    var chooseAction by remember { mutableStateOf(false) }

    ChiliChooseActionBottomSheet(
        isShown = chooseAction,
        onDismissRequest = {chooseAction = false},
        actions = listOf(
            ChiliChooseActionBSAction("Сегодня") { chooseAction = false },
            ChiliChooseActionBSAction("За последюнюю неделю") { chooseAction = false },
            ChiliChooseActionBSAction("За последний месяц") { chooseAction = false },
            ChiliChooseActionBSAction("Выбрать период") { chooseAction = false },
            ChiliChooseActionBSAction("Отмена") { chooseAction = false }
        )
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.screenBackground)) {
        ChiliCenteredAppToolbar(title = "BottomSheetsPreview", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)) {
            ChiliPrimaryButton(text = "Container bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {containerBS = true }
            ChiliPrimaryButton(text = "Detail bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {detailBsState = true }
            ChiliPrimaryButton(text = "Detail action bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {detailActionBsState = true }
            ChiliPrimaryButton(text = "Info bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {infoBottomSheetState = true }
            ChiliPrimaryButton(text = "Choose action", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) { chooseAction = true }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomSheetsPreview() {
    BottomSheetsPreview({})
}