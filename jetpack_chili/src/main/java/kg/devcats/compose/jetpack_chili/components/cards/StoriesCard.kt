package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_2
import kg.devcats.compose.jetpack_chili.theme.magenta_1

@Composable
fun StoriesCard(
    modifier: Modifier = Modifier,
    imageLink: String,
    storiesStatus: StoriesStatus = StoriesStatus.UnViewed,
    iconSize: IconSize,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {
    val borderColor = if (storiesStatus is StoriesStatus.UnViewed) magenta_1 else gray_2
    val width = when (iconSize) {
        IconSize.SMALL -> 88.dp
        IconSize.MEDIUM -> 128.dp
        IconSize.LARGE -> 163.dp
    }
    Box(
        modifier = modifier
            .height(78.dp)
            .width(width)
            .border(2.dp, color = borderColor, shape = RoundedCornerShape(15.dp))
    ) {
        if (isLoading) {
            Shimmer(modifier = modifier)
        } else {
            AsyncImage(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(Chili.shapes.RoundedCornerShape)
                    .clickable { onClick.invoke() },
                model = imageLink,
                contentScale = ContentScale.Fit,
                contentDescription = null,
            )
        }
    }
}

enum class IconSize {
    SMALL, MEDIUM, LARGE
}

sealed class StoriesStatus {
    data object Viewed : StoriesStatus()
    data object UnViewed : StoriesStatus()
}