package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    isExpanded: Boolean,
    onExpandChange: ((Boolean) -> Unit)? = null,
    titleTextStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleTextStyle: TextStyle = Chili.typography.H12_Secondary,
    titleMaxLines: Int = 1,
    subtitleMaxLines: Int = 1,
    headerPaddingValues: PaddingValues = PaddingValues(12.dp),
    isDividerVisible: Boolean = true,
    isLoading: Boolean = false,
    cardStartFrame: (@Composable () -> Unit)? = null,
    expandedContent: @Composable () -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = ""
    )

    Surface(
        modifier = modifier.clip(Chili.shapes.RoundedCornerShape),
        color = Chili.color.cardViewBackground,
    ) {
        Column {
            ExpandableCardHeader(
                title = title,
                subtitle = subtitle,
                titleTextStyle = titleTextStyle,
                subtitleTextStyle = subtitleTextStyle,
                titleMaxLines = titleMaxLines,
                subtitleMaxLines = subtitleMaxLines,
                headerPaddingValues = headerPaddingValues,
                cardStartFrame = cardStartFrame,
                rotationAngle = rotationAngle,
                isLoading = isLoading,
                onClick = { onExpandChange?.invoke(!isExpanded) }
            )

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    if (isDividerVisible) HorizontalDivider()
                    expandedContent()
                }
            }
        }
    }
}

@Composable
private fun ExpandableCardHeader(
    title: String,
    subtitle: String?,
    titleTextStyle: TextStyle,
    subtitleTextStyle: TextStyle,
    titleMaxLines: Int,
    subtitleMaxLines: Int,
    cardStartFrame: (@Composable () -> Unit)?,
    headerPaddingValues: PaddingValues,
    rotationAngle: Float,
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(headerPaddingValues),
        verticalAlignment = Alignment.CenterVertically
    ) {
        cardStartFrame?.invoke()

        Column(modifier = Modifier.weight(1f)) {
            if (isLoading) {
                Spacer(modifier = Modifier.height(8.dp))
                Shimmer(height = 6.dp, width = 160.dp)
                if (!subtitle.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Shimmer(height = 6.dp, width = 80.dp)
                }
                Spacer(modifier = Modifier.height(12.dp))
            } else {
                Text(
                    text = title.parseHtml(),
                    style = titleTextStyle,
                    maxLines = titleMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
                if (!subtitle.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle.parseHtml(),
                        style = subtitleTextStyle,
                        maxLines = subtitleMaxLines,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Icon(
            painter = painterResource(R.drawable.chili4_ic_chevron_down),
            contentDescription = null,
            modifier = Modifier.rotate(rotationAngle),
            tint = Chili.color.chevronColor
        )
    }
}

@Composable
@Preview
fun ExpandableCardPreview() {
    var isExpanded by remember { mutableStateOf(true) }
    ExpandableCard(
        isLoading = false,
        title = "Title",
        subtitle = "subtitle",
        isExpanded = isExpanded,
        onExpandChange = { isExpanded = it },
        expandedContent = {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Expanded content")
            }
        }
    )
}