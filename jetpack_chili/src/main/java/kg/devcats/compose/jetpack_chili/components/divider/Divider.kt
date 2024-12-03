package kg.devcats.compose.jetpack_chili.components.divider

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun Divider(
    title: String,
    subtitle: String? = null,
    actionText: String? = null,
    titleMaxLines: Int = 2,
    titleTextStyle: TextStyle = Chili.typography.H20_Primary_700,
    subtitleTextStyle: TextStyle = Chili.typography.H14_Primary,
    subtitleMaxLines: Int = 2,
    actionTextStyle: TextStyle = Chili.typography.H16_Action_500,
    actionTextMaxLines: Int = 1,
    titleSubtitleSpaceHeight: Dp = 11.dp,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 6.dp)
        ) {
            Text(
                text = title,
                style = titleTextStyle,
                maxLines = titleMaxLines,
                overflow = TextOverflow.Ellipsis
            )
            subtitle?.let {
                Spacer(modifier = Modifier.height(titleSubtitleSpaceHeight))
                Text(
                    text = it,
                    style = subtitleTextStyle,
                    maxLines = subtitleMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        actionText?.let {
            Text(
                text = it,
                style = actionTextStyle,
                maxLines = actionTextMaxLines,
                modifier = Modifier.clickable { onActionClick?.invoke() }
            )
        }
    }
}