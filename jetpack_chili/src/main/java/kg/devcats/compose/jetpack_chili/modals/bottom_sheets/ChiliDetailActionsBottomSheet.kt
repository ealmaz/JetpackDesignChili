package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliDetailActionsBottomSheet(
    isShown: Boolean,
    icon: Painter? = null,
    headerText: String? = null,
    bodyText: String? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit) = {},
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
    onDismissRequest: () -> Unit
) {
    ChiliBottomSheetContainer(isShown, onDismissRequest = onDismissRequest) {
        ChiliDetailActionsBottomSheetContent(
            icon = icon,
            headerText = headerText,
            bodyText = bodyText,
            secondaryButtonText = secondaryButtonText,
            onSecondaryButtonClick = onSecondaryButtonClick,
            primaryButtonText = primaryButtonText,
            onPrimaryButtonClick = onPrimaryButtonClick
        )
    }
}

@Composable
fun ColumnScope.ChiliDetailActionsBottomSheetContent(
    icon: Painter? = null,
    headerText: String? = null,
    bodyText: String? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit) = {},
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
) {
    icon?.let { Image(painter = icon, contentDescription = "", modifier = Modifier
        .size(60.dp)
        .align(Alignment.CenterHorizontally)) }
    headerText?.let {Text(text = headerText, modifier = Modifier.padding(top = 16.dp), style = Chili.typography.H16_Primary_500) } ?: Spacer(modifier = Modifier.height(8.dp)
    )
    bodyText?. let { Text(text = bodyText, modifier = Modifier.padding(top = 8.dp), style = Chili.typography.H14_Primary) }
    Column(modifier = Modifier.padding(top = 16.dp)) {
        secondaryButtonText?.let { ChiliAdditionalButton(text = it, modifier = Modifier.fillMaxWidth(), onClick = onSecondaryButtonClick) }
        if (secondaryButtonText != null && primaryButtonText != null) Spacer(modifier = Modifier.height(12.dp))
        primaryButtonText?.let { ChiliPrimaryButton(text = it, modifier = Modifier.fillMaxWidth(), onClick = onPrimaryButtonClick) }
    }
}

@Preview
@Composable
fun PreviewChiliDetailActionsBottomSheet() {
    Column {
        ChiliDetailActionsBottomSheetContent(
            icon = painterResource(id = R.drawable.chili_ic_documents_green),
            headerText = "Заголовок содержит в себе до 60 символов и может быть в 2 строки",
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            secondaryButtonText = "Ясно",
            primaryButtonText = "Понятно",
        )
    }
}

@Preview
@Composable
fun PreviewChiliDetailActionsBottomSheet2() {
    Column {
        ChiliDetailActionsBottomSheetContent(
            icon = painterResource(id = R.drawable.chili_ic_documents_green),
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            secondaryButtonText = "Ясно",
            primaryButtonText = "Понятно",
        )
    }
}


@Preview
@Composable
fun PreviewChiliDetailActionsBottomSheet3() {
    Column {
        ChiliDetailActionsBottomSheetContent(
            headerText = "Заголовок содержит в себе до 60 символов и может быть в 2 строки",
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            secondaryButtonText = "Ясно",
        )
    }
}

@Preview
@Composable
fun PreviewChiliDetailActionsBottomSheet4() {
    Column {
        ChiliDetailActionsBottomSheetContent(
            headerText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            primaryButtonText = "Понятно",
        )
    }
}
