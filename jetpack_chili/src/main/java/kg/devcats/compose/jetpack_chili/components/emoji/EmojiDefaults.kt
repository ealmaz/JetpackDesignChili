package kg.devcats.compose.jetpack_chili.components.emoji

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
        outerPlaceholderSize: Dp = 32.dp,
        outerPlaceholderColor: Color = Color.Transparent,
        innerPlaceholderSize: Dp = 22.dp,
        innerPlaceholderColor: Color = Color.Transparent,
        emojiTextStyle: TextStyle = Chili.typography.H10_Primary,
        emoji: String
    ): EmojiParams {
        return EmojiParams(
            outerPlaceholderSize = outerPlaceholderSize,
            outerPlaceholderColor = outerPlaceholderColor,
            innerPlaceholderSize = innerPlaceholderSize,
            innerPlaceholderColor = innerPlaceholderColor,
            emojiTextStyle = emojiTextStyle,
            emoji = emoji
        )
    }

}

data class EmojiParams(
    val outerPlaceholderSize: Dp,
    val outerPlaceholderColor: Color,
    val innerPlaceholderSize: Dp ,
    val innerPlaceholderColor: Color,
    val emojiTextStyle: TextStyle,
    val emoji: String
)