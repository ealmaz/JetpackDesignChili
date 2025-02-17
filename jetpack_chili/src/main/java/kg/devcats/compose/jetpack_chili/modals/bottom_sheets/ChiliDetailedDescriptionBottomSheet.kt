package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliDetailedDescriptionBottomSheet(
    modifier: Modifier = Modifier,
    isShown: Boolean,
    headerText: String? = null,
    subtitleText: String? = null,
    descriptionText: String? = null,
    icon: Painter? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
    onDismissRequest: () -> Unit
) {
    ChiliBottomSheetContainer(
        modifier = modifier,
        isShown = isShown,
        isTopInnerIconVisible = true,
        onDismissRequest = onDismissRequest,
        backgroundColor = Chili.color.customDialogBackgroundColor,
        bottomSheetShape = Chili.shapes.RoundedTopCornerShape,
        closeIcon = painterResource(id = R.drawable.chili_ic_close)
    ) {
        ChiliDetailedDescriptionBottomSheetContent(
            icon = icon,
            headerText = headerText,
            subtitleText = subtitleText,
            descriptionText = descriptionText,
            primaryButtonText = primaryButtonText,
            onPrimaryButtonClick = onPrimaryButtonClick
        )
    }
}

@Composable
fun ColumnScope.ChiliDetailedDescriptionBottomSheetContent(
    icon: Painter? = null,
    headerText: String? = null,
    subtitleText: String? = null,
    descriptionText: String? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit) = {},
) {

    headerText?.let {
        Text(
            text = headerText,
            modifier = Modifier.padding(top = 16.dp),
            style = Chili.typography.H24_Primary_500
        )
    } ?: Spacer(
        modifier = Modifier.height(8.dp)
    )

    subtitleText?.let {
        Text(
            text = subtitleText,
            modifier = Modifier.padding(top = 8.dp),
            style = Chili.typography.H14_Primary
        )
    }

    Row(
        modifier = Modifier.padding(top = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon?.let {
            Image(
                painter = icon, contentDescription = "", modifier = Modifier
                    .size(32.dp)
            )

            Spacer(modifier = Modifier.size(8.dp))
        }

        descriptionText?.let {
            Text(
                text = descriptionText,
                style = Chili.typography.H16_Primary_500
            )
        }

    }

    Row(modifier = Modifier.padding(top = 20.dp, bottom = 16.dp)) {
        primaryButtonText?.let {
            ChiliPrimaryButton(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                onClick = onPrimaryButtonClick
            )
        }
    }

}

@Preview
@Composable
fun PreviewChiliDetailedDescriptionBottomSheet() {
    Column {
        ChiliDetailedDescriptionBottomSheetContent(
            icon = painterResource(id = R.drawable.chili_ic_documents_green),
            headerText = "Заголовок содержит в себе до 60 символов и может быть в 2 строки",
            subtitleText = "Подзаголовок содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            descriptionText = "Описание содержит до 113 символов, что очень приятно, потому что теперь можно написать его аж в целых три строки и развернуть любую мысль ",
            primaryButtonText = "Понятно",
        )
    }
}
