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
fun AlertBlockCardView(
    modifier: Modifier = Modifier,
    title: String,
    infoState: InfoState,
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
            .background(infoState.getBgColor())
            .padding(12.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(infoState.getContentColor()),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = infoState.getIcon(),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(8.dp))
            Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
                Text(
                    text = title,
                    style = Chili.typography.H14_Primary_700.copy(color = infoState.getTextColor())
                )
                if (!subtitle.isNullOrEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = subtitle,
                        style = Chili.typography.H12,
                        color = infoState.getTextColor()
                    )
                }

                if (!buttonText.isNullOrEmpty()) {
                    ChiliCustomButton(
                        text = buttonText,
                        modifier = Modifier.padding(top = 12.dp),
                        colors = primaryButtonColors().copy(containerColor = infoState.getContentColor()),
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
                    tint = infoState.getContentColor(),
                    contentDescription = null,
                )
            }
        }
    }
}

enum class InfoState {
    Neutral, Warning, Error;

    @Composable
    fun getIcon(): Painter {
        return when (this) {
            Neutral -> painterResource(R.drawable.chili_ic_information)
            Warning -> painterResource(R.drawable.chili_ic_caution)
            Error -> painterResource(R.drawable.chili_ic_error)
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