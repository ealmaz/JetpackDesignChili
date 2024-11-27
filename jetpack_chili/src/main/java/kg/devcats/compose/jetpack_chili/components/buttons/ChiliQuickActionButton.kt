package kg.devcats.compose.jetpack_chili.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.setIsPressedEffect
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliQuickActionButton(
    text: String,
    @DrawableRes iconResource: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {

    val isPressed = remember { mutableStateOf(false) }

    val backgroundColor = when {
        !isEnabled -> Chili.color.quickActionIconBackgroundDisabledColor
        isPressed.value -> Chili.color.quickActionIconBackgroundClickedColor
        else -> Chili.color.quickActionIconBackgroundDefaultColor
    }
    val iconColor = when {
        !isEnabled -> Chili.color.quickActionIconDisabledColor
        isPressed.value -> Chili.color.quickActionIconClickedColor
        else -> Chili.color.quickActionIconDefaultColor
    }

    val textStyle =
        if (isEnabled) Chili.typography.H14_Primary else Chili.typography.H14_Primary.copy(color = Chili.color.quickActionButtonDisabledTextColor)

    Column(
        modifier = modifier.setIsPressedEffect(isPressed, onClick, isEnabled)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterHorizontally)
                .background(color = backgroundColor, shape = Chili.shapes.RoundedCornerShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = iconResource),
                tint = iconColor,
                contentDescription = ""
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            text = text,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ChiliQuickActionButtonPreview() {
    ChiliQuickActionButton("В избранное", R.drawable.chili_ic_bell)
}