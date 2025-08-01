package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliInfoBottomSheet(
    modifier: Modifier = Modifier,
    isShown: Boolean,
    icon: Painter? = null,
    headerText: String? = null,
    bodyText: String? = null,
    shape: Shape = Chili.shapes.RoundedCornerShape,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit) = {},
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
    onDismissRequest: () -> Unit
) {
    ChiliBottomSheetContainer(
        modifier = modifier.windowInsetsPadding(WindowInsets.navigationBars),
        isShown = isShown,
        bottomSheetShape = shape,
        onDismissRequest = onDismissRequest
    ) {
        ChiliInfoBottomSheetContent(
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
fun ColumnScope.ChiliInfoBottomSheetContent(
    icon: Painter? = null,
    headerText: String? = null,
    bodyText: String? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit) = {},
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
) {
    Row {
        icon?.let { Image(painter = icon, contentDescription = "", modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(0.dp, 16.dp, 16.dp, 16.dp)
            .size(60.dp))
        }
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            headerText?.let {Text(text = headerText.parseHtml(), modifier = Modifier.padding(top = 8.dp), style = Chili.typography.H16_Primary_500) }
            bodyText?. let { Text(text = bodyText.parseHtml(), modifier = Modifier.padding(top = 8.dp), style = Chili.typography.H14_Primary) }
        }
    }
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        secondaryButtonText?.let { ChiliAdditionalButton(text = it, modifier = Modifier.fillMaxWidth(), onClick = onSecondaryButtonClick) }
        if (secondaryButtonText != null && primaryButtonText != null) Spacer(modifier = Modifier.height(12.dp))
        primaryButtonText?.let { ChiliPrimaryButton(text = it, modifier = Modifier.fillMaxWidth(), onClick = onPrimaryButtonClick) }
    }
}

@Preview
@Composable
fun PreviewChiliInfoBottomSheet() {
    Column {
        ChiliInfoBottomSheetContent(
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
fun PreviewChiliInfoBottomSheet2() {
    Column {
        ChiliInfoBottomSheetContent(
            icon = painterResource(id = R.drawable.chili_ic_documents_green),
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            secondaryButtonText = "Ясно",
            primaryButtonText = "Понятно",
        )
    }
}


@Preview
@Composable
fun PreviewChiliInfoBottomSheet3() {
    Column {
        ChiliInfoBottomSheetContent(
            headerText = "Заголовок содержит в себе до 60 символов и может быть в 2 строки",
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            secondaryButtonText = "Ясно",
        )
    }
}

@Preview
@Composable
fun PreviewChiliInfoBottomSheet4() {
    Column {
        ChiliInfoBottomSheetContent(
            headerText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            bodyText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            primaryButtonText = "Понятно",
        )
    }
}
