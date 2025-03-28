package kg.devcats.compose.jetpack_chili.components.emoji

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

object EmojiDefaults {

    /**
     * Creates a params for NestedBoxWithEmoji that represents the default container.
     */
    @Composable
    fun emojiParams(
        outerPlaceholderColor: Color = Color.Transparent,
        innerPlaceholderSize: Dp = 22.dp,
        innerPlaceholderColor: Color = Color.Transparent,
        emojiTextStyle: TextStyle = Chili.typography.H10_Primary,
        emoji: String?,
        emojiIconPlaceHolder: Painter? = null,
        emojiIconPlaceHolderSize: Dp = 10.dp
    ): EmojiParams {
        return EmojiParams(
            outerPlaceholderColor = outerPlaceholderColor,
            innerPlaceholderSize = innerPlaceholderSize,
            innerPlaceholderColor = innerPlaceholderColor,
            emojiTextStyle = emojiTextStyle,
            emoji = emoji,
            emojiIconPlaceHolder = emojiIconPlaceHolder,
            emojiIconPlaceHolderSize = emojiIconPlaceHolderSize
        )
    }

}

data class EmojiParams(
    val outerPlaceholderColor: Color,
    val innerPlaceholderSize: Dp ,
    val innerPlaceholderColor: Color,
    val emojiTextStyle: TextStyle,
    val emoji: String?,
    val emojiIconPlaceHolder: Painter?,
    val emojiIconPlaceHolderSize: Dp
)