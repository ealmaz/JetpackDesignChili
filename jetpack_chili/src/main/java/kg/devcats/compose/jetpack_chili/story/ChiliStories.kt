package kg.devcats.compose.jetpack_chili.story

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.min

@Composable
fun ChiliStories(
    storiesBlocks: List<ChilliStoryBlock>,
    onMoveListener: StoryMoveListener,
    onStoryMoveListener: StoryMoveListener,
    onStoryClickListener: StoryClickListener? = null,
    initialStoryBlockIndex: Int = 0,
    onPageChanged: (Int) -> Unit = {}
) {

    var currentBlockIndex by remember { mutableIntStateOf(initialStoryBlockIndex) }
    val pagerState =
        rememberPagerState(initialPage = currentBlockIndex, pageCount = { storiesBlocks.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        currentBlockIndex = pagerState.currentPage
        onPageChanged(pagerState.currentPage)
    }

    VerticalDraggable (
        modifier = Modifier
            .fillMaxSize(),
        { onStoryMoveListener.onClose() }
    ) {

        CubePager (
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState
        ) { page ->
            ChiliStory(
                stories = storiesBlocks[page].stories,
                onMoveListener = object : StoryMoveListener {
                    override fun onAllStoriesCompleted() {
                        coroutineScope.launch {
                            if (page < storiesBlocks.size - 1) {
                                pagerState.animateScrollToPage(page + 1)
                            } else {
                                onStoryMoveListener.onAllStoriesCompleted()
                            }
                        }
                    }

                    override fun onClose() {
                        onStoryMoveListener.onClose()
                    }

                    override fun onFinished(index: Int) {
                        onMoveListener.onFinished(index)
                    }

                    override fun onStart(index: Int) {
                        onMoveListener.onStart(index)
                    }

                    override fun onPreviousClick() {
                        coroutineScope.launch {
                            if (page > 0) pagerState.animateScrollToPage(page - 1)
                        }
                    }
                },
                onClickListener = onStoryClickListener
            )
        }
    }
}


@Composable
fun CubePager(
    modifier: Modifier,
    state: PagerState,
    content: @Composable (page: Int) -> Unit
) {
    val scale by remember {
        derivedStateOf {
            1f - (state.currentPageOffsetFraction.absoluteValue) * .3f
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        HorizontalPager(
            state = state,
            modifier = modifier
                .fillMaxSize()
                .scale(1f, scaleY = scale)
        ) { page ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pageOffset = state.offsetForPage(page)
                        val offScreenRight = pageOffset < 0f
                        val deg = 90f
                        val interpolated = FastOutLinearInEasing.transform(pageOffset.absoluteValue)
                        rotationY = min(interpolated * if (offScreenRight) deg else -deg, 90f)

                        transformOrigin = TransformOrigin(
                            pivotFractionX = if (offScreenRight) 0f else 1f,
                            pivotFractionY = .5f
                        )
                    }
                    .drawWithContent {
                        val pageOffset = state.offsetForPage(page)

                        this.drawContent()
                        drawRect(
                            Color.Black.copy(
                                (pageOffset.absoluteValue * .7f)
                            )
                        )
                    }
                    .background(Color.LightGray)
            ) {
                content(page)
            }
        }
    }
}

fun PagerState.offsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}