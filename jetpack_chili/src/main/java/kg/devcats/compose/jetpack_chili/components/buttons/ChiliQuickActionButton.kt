package kg.devcats.compose.jetpack_chili.components.buttons

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.SurfaceClickActionState
import kg.devcats.compose.jetpack_chili.setSurfaceClick
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlinx.coroutines.CoroutineScope

@SuppressLint("ReturnFromAwaitPointerEventScope")
@Composable
fun ChiliQuickActionButton(
    text: String,
    @DrawableRes iconResource: Int,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    var state by remember { mutableStateOf<SurfaceClickActionState>(SurfaceClickActionState.Default) }

    val backgroundColor = when {
        !enabled -> Chili.color.quickActionIconBackgroundDisabledColor
        state is SurfaceClickActionState.Pressed -> Chili.color.quickActionIconBackgroundClickedColor
        state is SurfaceClickActionState.Default -> Chili.color.quickActionIconBackgroundDefaultColor
        else -> Chili.color.quickActionIconBackgroundDefaultColor
    }
    val iconColor = when {
        !enabled -> Chili.color.quickActionIconDisabledColor
        state is SurfaceClickActionState.Pressed -> Chili.color.quickActionIconClickedColor
        state is SurfaceClickActionState.Default -> Chili.color.quickActionIconDefaultColor
        else -> Chili.color.quickActionIconDefaultColor
    }

    val textStyle =
        if (enabled) Chili.typography.H14_Primary else Chili.typography.H14_Primary.copy(color = Chili.color.quickActionButtonDisabledTextColor)

    Column(
        modifier = modifier.setSurfaceClick(
            condition = enabled,
            coroutineScope = coroutineScope,
            onPressedState = {
                state = SurfaceClickActionState.Pressed
                onClick.invoke()
            },
            onDefaultState = { state = SurfaceClickActionState.Default }
        )
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