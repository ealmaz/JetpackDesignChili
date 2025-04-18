package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliAdditionalButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Chili.typography.H14_Primary_500,
    enabled: Boolean = true,
    endIconPainter: Painter? = null,
    endIconContentDescription: String? = null,
    endIconModifier: Modifier = Modifier,
    enabledBackgroundColor: Color = Chili.color.buttonAdditionalContainer,
    disabledBackgroundColor: Color = Chili.color.buttonAdditionalDisabledContainer,
    startIcon: Any? = null,
    isLoading: Boolean = false,
    buttonSize: ButtonSize = ButtonSize.REGULAR,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .clickable(
                enabled = enabled && !isLoading,
                onClick = onClick
            )
            .background(
                if (enabled) enabledBackgroundColor else disabledBackgroundColor
            )
            .padding(PaddingValues(horizontal = buttonSize.horizontalPadding)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                ChiliLoader(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .size(buttonSize.iconSize)
                        .align(Alignment.CenterVertically)
                    ,
                    color = Chili.color.chevronColor,
                    strokeWidth = 2.dp
                )
            } else {
                startIcon?.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = it),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .size(buttonSize.iconSize)
                            .clip(Chili.shapes.RoundedCornerShape),
                    )
                    Spacer(modifier = Modifier.width(buttonSize.iconPadding))
                }
            }
            Text(
                modifier = Modifier.padding(vertical = buttonSize.verticalPadding),
                text = if (isLoading) "" else text,
                maxLines = 1,
                style = textStyle,
                color = if (enabled) Chili.color.buttonAdditionalText else Chili.color.buttonAdditionalDisabledText,
                overflow = TextOverflow.Ellipsis
            )
            endIconPainter?.let {
                Image(
                    modifier = endIconModifier,
                    painter = endIconPainter,
                    contentDescription = endIconContentDescription ?: ""
                )
            }
        }
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun PreviewChiliAdditionalButton() {
    ChiliAdditionalButton(
        text = "AdditiuonalButton",
        modifier = Modifier.fillMaxWidth(),
        endIconPainter = painterResource(id = R.drawable.chili_ic_documents_green)) {}
}
