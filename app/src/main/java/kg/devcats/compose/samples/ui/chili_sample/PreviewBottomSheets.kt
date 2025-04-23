package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliDetailedDescriptionBottomSheet
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliInfoBottomSheet
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.black_5
import kg.devcats.compose.jetpack_chili.theme.gray_8
import kg.devcats.compose.jetpack_chili.theme.green_3

@Composable
fun BottomSheetsPreview(
    navigateUp: () -> Unit,
) {
    var containerBSTopDrawable by remember { mutableStateOf(false) }

    ChiliBottomSheetContainer(
        isShown = containerBSTopDrawable,
        isTopInnerIconVisible = true,
        onDismissRequest = {containerBSTopDrawable = false}
    ) {
        Box(modifier = Modifier
            .background(green_3)
            .fillMaxWidth(), contentAlignment = Alignment.Center) {
            LazyColumn {
                items(30) {
                    Text(modifier = Modifier.padding(vertical = 8.dp), text = "Bottom sheet container", style = Chili.typography.H20_Primary)
                }
            }
        }
    }

    var containerBS by remember { mutableStateOf(false) }

    ChiliBottomSheetContainer(
        isShown = containerBS,
        isTopInnerIconVisible = true,
        onDismissRequest = {containerBS = false}
    ) {
        Box(modifier = Modifier
            .padding(bottom = 16.dp)
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
    var detailActionWithInnerDrugIconBsState by remember { mutableStateOf(false) }

    ChiliDetailActionsBottomSheet(
        isShown = detailActionWithInnerDrugIconBsState,
        hideOnSwipe = false,
        isTopInnerIconVisible = true,
        isCloseIconVisible = true,
        icon = painterResource(id = R.drawable.chili_ic_documents_green),
        iconSize = 100.dp,
        headerTextAnnotatedString = stringResource(id = kg.devcats.compose.samples.R.string.example_with_colored_text, "Вы ").parseHtml(),
        headerTextStyle = Chili.typography.H16_Primary_500.copy(textAlign = TextAlign.Center),
        bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
        bodyTextStyle = Chili.typography.H16_Primary_500.copy(textAlign = TextAlign.End),
        secondaryButtonText = "Ясно",
        onPrimaryButtonClick = { detailActionWithInnerDrugIconBsState = false },
        onSecondaryButtonClick = { detailActionWithInnerDrugIconBsState = false },
        primaryButtonText = "Понятно",
        onDismissRequest = {detailActionWithInnerDrugIconBsState = false}
    )

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
        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
        isShown = infoBottomSheetState,
        icon = painterResource(id = R.drawable.chili_ic_documents_green),
        bodyText = "New Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль <u>500</u> ",
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
            ChiliChooseActionBSAction("Сегодня", Chili.color.errorText) { chooseAction = false },
            ChiliChooseActionBSAction("За последюнюю неделю") { chooseAction = false },
            ChiliChooseActionBSAction("За последний месяц") { chooseAction = false },
            ChiliChooseActionBSAction("Выбрать период") { chooseAction = false },
            ChiliChooseActionBSAction("Отмена") { chooseAction = false }
        )
    )

    var detailedInfoBottomSheetState by remember { mutableStateOf(false) }

    ChiliDetailedDescriptionBottomSheet(
        isShown = detailedInfoBottomSheetState,
        icon = painterResource(id = R.drawable.chili_ic_documents_green),
        headerText = "Заголовок содержит в себе до 60 символов и может быть в 2 строки",
        subtitleText = "Подзаголовок содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
        descriptionText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
        onPrimaryButtonClick = { detailedInfoBottomSheetState = false },
        primaryButtonText = "Понятно",
        onDismissRequest = { detailedInfoBottomSheetState = false }
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
            ChiliPrimaryButton(text = "Container bottom sheet with top drawable", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {containerBSTopDrawable = true }
            ChiliPrimaryButton(text = "Container bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {containerBS = true }
            ChiliPrimaryButton(text = "Detail bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {detailBsState = true }
            ChiliPrimaryButton(text = "Detail action bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {detailActionBsState = true }
            ChiliPrimaryButton(text = "Detail action bottom sheet with inner drug icon", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {detailActionWithInnerDrugIconBsState = true }
            ChiliPrimaryButton(text = "Info bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {infoBottomSheetState = true }
            ChiliPrimaryButton(text = "Choose action", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) { chooseAction = true }
            ChiliPrimaryButton(text = "Detailed info bottom sheet", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) { detailedInfoBottomSheetState = true }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomSheetsPreview() {
    BottomSheetsPreview({})
}