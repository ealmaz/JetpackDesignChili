package kg.devcats.compose.jetpack_chili.components.divider

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.clickableWithoutEffect

/**
 * Заголовок для элементов
 * @param modifier Стиль элемента
 * @param params Параметры отображения заголовок (Строится при помощи TitledDividerDefaults)
 */
@Composable
fun TitledDivider(
    modifier: Modifier = Modifier,
    params: TitleDividerParams,
    startPlaceholder: @Composable () -> Unit = {},
    endPlaceholder: @Composable () -> Unit = {}
) {
    var subtitleIsVisible by remember { mutableStateOf(true) }

    Row(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TitledDividerTitleSection(
                    modifier = Modifier.weight(1f),
                    params = params,
                    startPlaceholder = startPlaceholder,
                    subtitleIsVisible = subtitleIsVisible,
                    onChevronClick = { subtitleIsVisible = !subtitleIsVisible }
                )

                endPlaceholder.invoke()
            }

            AnimatedVisibility(subtitleIsVisible) {
                params.subtitle?.let {
                    Text(
                        modifier = Modifier.padding(top = 11.dp),
                        text = params.subtitle,
                        overflow = TextOverflow.Ellipsis,
                        style = params.subtitleTextStyle,
                        maxLines = params.subtitleMaxLines
                    )
                }
            }
        }
    }
}

@Composable
private fun TitledDividerTitleSection(
    modifier: Modifier = Modifier,
    params: TitleDividerParams,
    startPlaceholder: @Composable () -> Unit,
    subtitleIsVisible: Boolean,
    onChevronClick: () -> Unit
) {
    val animatedRotation = animateFloatAsState(
        targetValue = if (subtitleIsVisible) 0f else -180f, label = ""
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        startPlaceholder.invoke()

        Text(
            text = params.title,
            style = params.titleTextStyle,
            maxLines = params.titleMaxLines,
            overflow = TextOverflow.Ellipsis
        )

        RenderIconIfVisible(
            icon = params.notificationIcon,
            isVisible = params.isNotificationVisible
        )

        RenderIconIfVisible(
            modifier = Modifier
                .rotate(animatedRotation.value)
                .clickableWithoutEffect(onClick = onChevronClick),
            icon = painterResource(R.drawable.ic_shevron),
            isVisible = params.isShevronIsVisible,
        )
    }
}

@Composable
internal fun RenderIconIfVisible(
    modifier: Modifier = Modifier,
    icon: Painter,
    isVisible: Boolean
) {
    AnimatedVisibility(isVisible) {
        Image(
            modifier = modifier.padding(horizontal = 4.dp),
            painter = icon,
            contentDescription = "Icon",
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TitledDivider(
        params = TitledDividerDefaults.titledDividerParams(title = "Заголовок")
    )
}