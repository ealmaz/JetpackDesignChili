package kg.devcats.compose.jetpack_chili.components.emoji

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun NestedBoxWithEmoji(
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    params: EmojiParams,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(Chili.shapes.RoundedCornerShape)
            .background(color = params.outerPlaceholderColor),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(params.innerPlaceholderSize)
                .clip(CircleShape)
                .background(params.innerPlaceholderColor),
            contentAlignment = Alignment.Center
        ) {
            if (params.emoji != null){
                Text(
                    text = params.emoji,
                    style = params.emojiTextStyle,
                    textAlign = TextAlign.Center
                )
            } else if(params.emojiIconPlaceHolder!= null) {
                Icon(
                    modifier = Modifier.size(params.emojiIconPlaceHolderSize),
                    painter = params.emojiIconPlaceHolder,
                    contentDescription = "")
            }
        }
    }
}