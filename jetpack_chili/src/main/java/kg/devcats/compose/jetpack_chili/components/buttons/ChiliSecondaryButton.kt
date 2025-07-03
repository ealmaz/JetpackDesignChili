package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.rippleClickable
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliSecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Chili.typography.H14_Primary_500,
    enabled: Boolean = true,
    rippleColor: Color = Chili.color.buttonSecondaryRipple,
    textColor: Color = if (enabled) Chili.color.buttonSecondaryText else Chili.color.disabledText,
    pressedTextColor: Color = textColor.copy(alpha = 0.5f),
    icon: Any? = null,
    isLoading: Boolean = false,
    buttonSize: ButtonSize =  RegularButtonSize(),
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentTextColor = when {
        !enabled -> Chili.color.disabledText
        isPressed -> pressedTextColor
        else -> textColor
    }

    Box(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .rippleClickable(
                enabled = enabled && !isLoading,
                rippleColor = rippleColor,
                onClick = onClick,
                bounded = true,
                radius = Dp.Unspecified,
                interactionSource = interactionSource
            )
            .background(Chili.color.buttonSecondaryContainer)
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
                        .align(Alignment.CenterVertically),
                    color = Chili.color.buttonSecondaryText,
                    strokeWidth = 2.dp
                )
            } else {
                icon?.let {
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
                color = currentTextColor,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliSecondaryButton() {
    ChiliSecondaryButton(text = "Secondary button", modifier = Modifier.fillMaxWidth()) {}
}
