package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.red_1
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun CircledBadgeText(
    modifier: Modifier = Modifier,
    badgeText: String? = null,
    textStyle: TextStyle = Chili.typography.H14_Primary.copy(color = white_1),
    badgeColor: Color = red_1,
    badgeSize: Dp = 24.dp
) {
    badgeText?.takeIf { it.isNotEmpty() }?.let {
        Box(
            modifier = modifier
                .size(badgeSize)
                .background(
                    color = badgeColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = it,
                style = textStyle,
                maxLines = 1,
                overflow = TextOverflow.Clip,

            )
        }
    }
}