package kg.devcats.compose.jetpack_chili.components.banner

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun AutoScrollBannerView(
    images: List<String>,
    autoScrollDelayMillis: Long = 1000,
    onImageClick: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth - 64.dp

    val contentPadding by remember {
        derivedStateOf {
            val currentIndex = listState.firstVisibleItemIndex
            when (currentIndex) {
                0, images.size - 1 -> PaddingValues(horizontal = 0.dp)
                else -> PaddingValues(horizontal = 24.dp)
            }
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(autoScrollDelayMillis)
            val nextIndex = if (listState.firstVisibleItemIndex < images.size - 1) {
                listState.firstVisibleItemIndex + 1
            } else {
                0
            }
            coroutineScope.launch {
                listState.animateScrollToItem(nextIndex)
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { isScrolling ->
                if (!isScrolling) {
                    val offset = listState.firstVisibleItemScrollOffset
                    val currentIndex = listState.firstVisibleItemIndex
                    val targetIndex = if (offset > 0.3 * (listState.layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0)) {
                        currentIndex + 1
                    } else {
                        currentIndex
                    }.coerceIn(0, images.size - 1)
                    coroutineScope.launch {
//                        listState.animateScrollToItem(targetIndex)
                    }
                }
            }
    }

    LazyRow(
        state = listState,
        contentPadding = contentPadding,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(images.size) { index ->
            val startPadding = if(index == 0) 16.dp else 8.dp
            val endPadding = if(index == images.size - 1) 16.dp else 8.dp
            Box(
                modifier = Modifier
                    .padding(start = startPadding, end = endPadding)
                    .width(itemWidth)
            ) {
                SnappingCarouselItem(
                    imageUrl = images[index],
                    onClick = { onImageClick(index) },
                )
            }
        }
    }
}

@Composable
fun SnappingCarouselItem(
    imageUrl: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { onClick() })
                }
        )
    }
}
