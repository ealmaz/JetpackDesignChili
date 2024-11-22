package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.components.shimmer.ShimmerView
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun MultiIconedTitleCellView(
    modifier: Modifier = Modifier,
    title: String,
    icons: List<Any> = emptyList(),
    titleStyle: TextStyle = Chili.typography.H16_Primary_700,
    additionalInfo: String = "",
    additionalInfoStyle: TextStyle = Chili.typography.H16.copy(color = Chili.color.buttonSecondaryText),
    onAdditionalInfoClick: (() -> Unit)? = null,
    onSurfaceClick: (() -> Unit)? = null,
    isLoading: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSurfaceClick?.invoke() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = titleStyle,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                maxLines = 1
            )
            Text(
                text = additionalInfo,
                style = additionalInfoStyle,
                modifier = Modifier
                    .clickable { onAdditionalInfoClick?.invoke() }
                    .padding(vertical = 12.dp),
                maxLines = 1
            )
        }
        MultiIconedRow(icons = icons, isLoading = isLoading)
    }
}

@Composable
fun MultiIconedRow(
    modifier: Modifier = Modifier,
    icons: List<Any> = emptyList(),
    isLoading: Boolean = false
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((-4).dp)
    ) {
        if (isLoading) {
            items(7) {
                ShimmerView(
                    height = 30.dp, width = 30.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Chili.color.surfaceBackground)
                        .padding(1.dp)
                )
            }
        } else {
            items(icons) { imageLink ->
                AsyncImage(
                    model = imageLink,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Chili.color.surfaceBackground)
                        .padding(1.dp)
                        .size(30.dp),
                    contentDescription = null
                )
            }
        }
    }
}