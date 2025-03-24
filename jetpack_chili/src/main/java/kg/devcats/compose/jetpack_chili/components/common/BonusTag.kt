package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun BonusTag(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val enabledColor = if (enabled) Color.White.copy(alpha = 0f) else Color.White.copy(alpha = 0.5f)
    val alphaForDisableState = if (enabled) 1f else 0.5f
    Box(modifier = modifier
            .clip(RoundedCornerShape(21.dp))
            .paint(
                painter = painterResource(R.drawable.chilli_bonus_tag_bg),
                contentScale = ContentScale.FillWidth
            )
        .background(enabledColor)
        .run {
            if (onClick != null && enabled) clickable { onClick.invoke() }
            else this
        }
        ,contentAlignment = Alignment.CenterStart) {
        Row(modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(end = 2.dp),
                text = text,
                style = Chili.typography.H12_Primary_500.copy(color = Color.White)
            )
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.chilli_ic_bonus),
                contentDescription = null,
                alpha = alphaForDisableState
            )
        }
    }
}