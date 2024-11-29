package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.adjustFontWeight
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.setIsPressedEffect
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ProductCell(
    title: String? = null,
    subtitle: String? = null,
    additionalText: String? = null,
    icon: Painter? = null,
    overlayIcon: Painter? = null,
    isMain: Boolean = false,
    isBlocked: Boolean = false,
    blockedAlpha: Float = 0.4f,
    titleMaxLines: Int = 3,
    subtitleMaxLines: Int = 1,
    additionalTextMaxLines: Int = 2,
    titleTextAppearance: TextStyle = Chili.typography.H17_Primary,
    subtitleTextAppearance: TextStyle = Chili.typography.H13_Value,
    additionalTextAppearance: TextStyle = Chili.typography.H15_Primary,
    iconWidth: Dp = 60.dp,
    iconHeight: Dp = 40.dp,
    additionalTextVerticalAlign: Alignment.Vertical = Alignment.Top,
    overlayAlpha: Float = 0.4f,
    overlay: Painter = painterResource(R.drawable.chili_ic_card_overlay),
    isClickable: Boolean = true,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val isPressed = remember { mutableStateOf(false) }
    val contentAlpha = if (isBlocked) blockedAlpha else 1f

    Row(
        modifier = modifier
            .setIsPressedEffect(isPressed, onClick, isClickable)
            .fillMaxWidth(),
    ) {
        if (isLoading) {
            //shimmers
            Shimmer(width = iconWidth, height = iconHeight, roundRadius = 6.dp)
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp)
            ) {
                Shimmer(width = 120.dp, height = 8.dp, roundRadius = 8.dp)
                Spacer(modifier = Modifier.height(12.dp))
                Shimmer(width = 90.dp, height = 8.dp, roundRadius = 8.dp)
            }

        } else {
            //icon
            Box(
                modifier = Modifier
                    .size(width = iconWidth, height = iconHeight)
            ) {
                icon?.let {
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                overlayIcon?.let {
                    Image(
                        painter = overlay,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(overlayAlpha)
                    )
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(18.dp)
                    )
                }
            }

            //title-subtitle
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
                    .align(if (subtitle.isNullOrEmpty()) Alignment.CenterVertically else Alignment.Top)
            ) {
                if (!title.isNullOrEmpty()) {
                    Row {
                        Text(
                            text = title,
                            style = titleTextAppearance.adjustFontWeight(isPressed.value),
                            maxLines = titleMaxLines,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.alpha(contentAlpha)
                        )
                        if (isMain) {
                            Image(
                                painter = painterResource(R.drawable.chili_ic_star),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .size(14.dp)
                                    .align(Alignment.CenterVertically)
                                    .alpha(contentAlpha)
                            )
                        }
                    }
                }
                if (!subtitle.isNullOrEmpty()) {
                    Text(
                        text = subtitle,
                        style = subtitleTextAppearance.adjustFontWeight(isPressed.value),
                        maxLines = subtitleMaxLines,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .alpha(1f)
                    )
                }
            }

            //additional text
            if (!additionalText.isNullOrEmpty()) {
                Text(
                    text = additionalText.parseHtml(),
                    style = additionalTextAppearance.adjustFontWeight(isPressed.value),
                    textAlign = TextAlign.End,
                    maxLines = additionalTextMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .alpha(contentAlpha)
                        .align(if (subtitle.isNullOrEmpty()) Alignment.CenterVertically else additionalTextVerticalAlign)
                )
            }
        }
    }
}



