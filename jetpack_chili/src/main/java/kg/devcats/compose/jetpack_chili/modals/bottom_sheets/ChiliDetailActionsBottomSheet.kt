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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliDetailActionsBottomSheet(
    modifier: Modifier = Modifier,
    hideOnSwipe: Boolean = true,
    isCloseIconVisible: Boolean = true,
    isTopIconVisible: Boolean = false,
    isTopInnerIconVisible: Boolean = false,
    topIconColor: Color? = null,
    isShown: Boolean,
    icon: Painter? = null,
    iconSize: Dp? = null,
    headerText: String? = null,
    headerTextAnnotatedString: AnnotatedString? = null,
    headerTextStyle: TextStyle? = null,
    headerTextAlign: TextAlign? = null,
    bodyText: String? = null,
    bodyTextAnnotatedString: AnnotatedString? = null,
    bodyTextStyle: TextStyle? = null,
    bodyTextAlign: TextAlign? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit) = {},
    secondaryButtonTextStyle: TextStyle? = null,
    secondaryButtonTextColor: Color? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
    primaryButtonTextStyle: TextStyle? = null,
    onDismissRequest: () -> Unit,
) {
    ChiliBottomSheetContainer(
        modifier = modifier,
        isShown = isShown,
        hideOnSwipe = hideOnSwipe,
        isCloseIconVisible = isCloseIconVisible,
        isTopIconVisible = isTopIconVisible,
        isTopInnerIconVisible = isTopInnerIconVisible,
        topIconColor= topIconColor,
        onDismissRequest = onDismissRequest
    ) {
        ChiliDetailActionsBottomSheetContent(
            icon = icon,
            iconSize = iconSize,
            headerText = headerText,
            headerTextAnnotatedString = headerTextAnnotatedString,
            headerTextStyle = headerTextStyle,
            headerTextAlign = headerTextAlign,
            bodyText = bodyText,
            bodyTextAnnotatedString = bodyTextAnnotatedString,
            bodyTextStyle = bodyTextStyle,
            bodyTextAlign = bodyTextAlign,
            secondaryButtonText = secondaryButtonText,
            secondaryButtonTextStyle = secondaryButtonTextStyle,
            secondaryButtonTextColor = secondaryButtonTextColor,
            onSecondaryButtonClick = onSecondaryButtonClick,
            primaryButtonText = primaryButtonText,
            primaryButtonTextStyle = primaryButtonTextStyle,
            onPrimaryButtonClick = onPrimaryButtonClick
        )
    }
}

@Composable
fun ColumnScope.ChiliDetailActionsBottomSheetContent(
    icon: Painter? = null,
    iconSize: Dp? = null,
    headerText: String? = null,
    headerTextAnnotatedString: AnnotatedString? = null,
    headerTextStyle: TextStyle? = null,
    headerTextAlign: TextAlign? = null,
    bodyText: String? = null,
    bodyTextAnnotatedString: AnnotatedString? = null,
    bodyTextStyle: TextStyle? = null,
    bodyTextAlign: TextAlign? = null,
    secondaryButtonText: String? = null,
    secondaryButtonTextStyle: TextStyle? = null,
    secondaryButtonTextColor: Color? = null,
    onSecondaryButtonClick: (() -> Unit) = {},
    primaryButtonText: String? = null,
    primaryButtonTextStyle: TextStyle? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
) {
    icon?.let {
        Image(painter = icon, contentDescription = "", modifier = Modifier
            .size(iconSize ?: 60.dp)
            .align(Alignment.CenterHorizontally))
    }
    headerTextAnnotatedString?.let {
        Text(
            text = it,
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            style = headerTextStyle ?: Chili.typography.H16_Primary_500,
            textAlign = headerTextAlign
        )
    } ?: headerText?.let {
        Text(
            text = it,
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            style = headerTextStyle ?: Chili.typography.H16_Primary_500,
            textAlign = headerTextAlign
        )
    } ?: Spacer(
        modifier = Modifier.height(8.dp)
    )
    bodyTextAnnotatedString?.let {
        Text(
            text = it,
            modifier = Modifier.padding(top = 8.dp),
            style = bodyTextStyle ?: Chili.typography.H14_Primary,
            textAlign = bodyTextAlign
        )
    } ?: bodyText?.let {
        Text(
            text = it,
            modifier = Modifier.padding(top = 8.dp),
            style = bodyTextStyle ?: Chili.typography.H14_Primary,
            textAlign = bodyTextAlign
        )
    }
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
        secondaryButtonText?.let {
            ChiliAdditionalButton(
                text = it, modifier = Modifier.fillMaxWidth(),
                onClick = onSecondaryButtonClick,
                textStyle = secondaryButtonTextStyle,
                textColorOnEnabled = secondaryButtonTextColor
            )
        }
        if (secondaryButtonText != null && primaryButtonText != null) Spacer(modifier = Modifier.height(12.dp))
        primaryButtonText?.let {
            ChiliPrimaryButton(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                onClick = onPrimaryButtonClick,
                textStyle = primaryButtonTextStyle
            )
        }
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
