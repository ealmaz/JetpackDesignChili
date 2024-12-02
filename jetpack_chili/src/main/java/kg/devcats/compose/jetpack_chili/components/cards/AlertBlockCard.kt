package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliCustomButton
import kg.devcats.compose.jetpack_chili.components.buttons.primaryButtonColors
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun AlertBlockCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    alertState: AlertState,
    subtitle: String? = null,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null,
    isClosable: Boolean = false,
    onCloseClick : (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(alertState.getBgColor())
            .padding(12.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(alertState.getContentColor()),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = alertState.getIcon(),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(8.dp))
            Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
                if (!title.isNullOrEmpty()){
                    Text(
                        text = title,
                        style = Chili.typography.H14_Primary_700.copy(color = alertState.getTextColor())
                    )
                }
                if (!subtitle.isNullOrEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = if (title.isNullOrEmpty()) 0.dp else 4.dp),
                        text = subtitle,
                        style = Chili.typography.H12,
                        color = alertState.getTextColor()
                    )
                }

                if (!buttonText.isNullOrEmpty()) {
                    ChiliCustomButton(
                        text = buttonText,
                        modifier = Modifier.padding(top = 12.dp),
                        colors = primaryButtonColors().copy(containerColor = alertState.getContentColor()),
                        onClick = { onButtonClick?.invoke() }
                    )
                }
            }
            if (isClosable) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onCloseClick?.invoke() },
                    painter = painterResource(R.drawable.chili_ic_close),
                    tint = alertState.getContentColor(),
                    contentDescription = null,
                )
            }
        }
    }
}

enum class AlertState {
    Neutral, Warning, Error;

    @Composable
    fun getIcon(): Painter {
        return when (this) {
            Neutral -> painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_information)
            Warning -> painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_caution)
            Error -> painterResource(kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_error)
        }
    }

    @Composable
    fun getTextColor(): Color {
        return when (this) {
            Neutral -> Chili.color.alertNeutralText
            Warning -> Chili.color.alertWarningText
            Error -> Chili.color.alertErrorText
        }
    }

    @Composable
    fun getBgColor(): Color {
        return when (this) {
            Neutral -> Chili.color.alertNeutralBg
            Warning -> Chili.color.alertWarningBg
            Error -> Chili.color.alertErrorBg
        }
    }

    @Composable
    fun getContentColor(): Color {
        return when (this) {
            Neutral -> Chili.color.alertNeutralContent
            Warning -> Chili.color.alertWarningContent
            Error -> Chili.color.alertErrorContent
        }
    }
}