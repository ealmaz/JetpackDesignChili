package kg.devcats.compose.jetpack_chili.components.banner

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AutoScrollBanner(
    modifier: Modifier = Modifier,
    images: List<String>,
    autoScrollDelayMillis: Long = 3000,
    imageHeight: Dp = 80.dp,
    onImageClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()

    val contentPadding by remember {
        derivedStateOf {
            val currentIndex = pagerState.currentPage
            when (currentIndex) {
                0 -> PaddingValues(start = 8.dp, end = 32.dp)
                images.size - 1 -> PaddingValues(start = 32.dp, end = 8.dp)
                else -> PaddingValues(horizontal = 24.dp)
            }
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(autoScrollDelayMillis)
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = contentPadding,
        modifier = modifier
            .fillMaxWidth()
    ) { page ->
        AsyncImage(
            model = images[page],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { onImageClick(page) })
                }
        )
    }
}