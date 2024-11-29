package kg.devcats.compose.jetpack_chili.story

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliSecondaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.black_5
import kg.devcats.compose.jetpack_chili.theme.gray_3_alpha_98
import kg.devcats.compose.jetpack_chili.theme.magenta_1

@Composable
fun ChiliStory(
    stories: List<ChilliStoryModel>,
    onMoveListener: StoryMoveListener? = null,
    onFinishListener: StoryOnFinishListener? = null,
    onClickListener: StoryClickListener? = null
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var currentStoryIndex by remember {
        if (stories.all { it.isViewed == true }) mutableIntStateOf(0)
        else mutableIntStateOf(stories.indexOfFirst { it.isViewed != true })
    }
    var isPaused by remember { mutableStateOf(false) }
    var remainingTime by remember {
        mutableFloatStateOf(
            stories.getOrNull(currentStoryIndex)?.duration?.toFloat() ?: 0f
        )
    }
    val progressValues = remember {
        if (stories.all { it.isViewed == true }) {
            mutableStateListOf(*Array(stories.size) { 0f })
        } else {
            mutableStateListOf(*stories.map { if (it.isViewed == true) 1f else 0f }.toTypedArray())
        }
    }
    val timer = remember { mutableStateOf<CountDownTimer?>(null) }

    var isMediaLoaded by remember { mutableStateOf(false) }
    var currentPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    val currentStory = stories.getOrNull(currentStoryIndex)

    fun startTimer(duration: Float) {
        timer.value?.cancel()
        timer.value = object : CountDownTimer((duration * 1000).toLong(), 16) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished / 1000f
                progressValues[currentStoryIndex] =
                    1f - (remainingTime / (currentStory?.duration ?: 1f).toFloat())
            }

            override fun onFinish() {
                progressValues[currentStoryIndex] = 1f
                if (currentStoryIndex < stories.size - 1) {
                    currentStoryIndex++
                    remainingTime = stories[currentStoryIndex].duration.toFloat()
                    isMediaLoaded = false
                    onMoveListener?.onFinished(currentStoryIndex)
                } else {
                    onMoveListener?.onAllStoriesCompleted()
                    onFinishListener?.onAllStoriesFinished()
                }
            }
        }.start()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(currentStory?.backgroundColor?.let { color ->
                try {
                    Color(android.graphics.Color.parseColor(color))
                } catch (e: Exception) {
                    Color.White
                }
            } ?: Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stories.forEachIndexed { index, _ ->
                LinearProgressIndicator(
                    progress = { progressValues.getOrElse(index) { 0f } },
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .padding(horizontal = 2.dp)
                        .clip(RoundedCornerShape(50)),
                    color = black_5,
                    trackColor = gray_3_alpha_98,
                    drawStopIndicator = {}
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            currentStory?.let { story ->
                when (story.storyType) {
                    ChilliStoryType.IMAGE -> {
                        currentPlayer = null
                        AsyncImage(
                            model = story.mediaUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.BottomCenter,
                            contentScale = if (story.scaleType == StoryScaleType.BOTTOM_HORIZONTAL_CROP) ContentScale.FillWidth else ContentScale.Crop,
                            onSuccess = {
                                isMediaLoaded = true
                                remainingTime = story.duration.toFloat()
                                startTimer(remainingTime)
                            }
                        )
                    }

                    ChilliStoryType.VIDEO -> {
                        AndroidView(
                            factory = {
                                PlayerView(context).apply {
                                    player = ExoPlayer.Builder(context).build().apply {
                                        useController = false
                                        setMediaItem(MediaItem.fromUri(story.mediaUrl!!))
                                        prepare()
                                        playWhenReady = true
                                        volume = 0f
                                        currentPlayer = this
                                        addListener(object : Player.Listener {
                                            override fun onPlaybackStateChanged(playbackState: Int) {
                                                if (playbackState == Player.STATE_READY) {
                                                    isMediaLoaded = true
                                                    remainingTime = story.duration.toFloat()
                                                    startTimer(remainingTime)
                                                }
                                            }
                                        })
                                    }

                                    setShutterBackgroundColor(currentStory.backgroundColor?.let { color ->
                                        try {
                                            Color(android.graphics.Color.parseColor(color))
                                        } catch (e: Exception) {
                                            Color.White
                                        }
                                    }?.toArgb() ?: Color.White.toArgb())
                                }
                            },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }

                    ChilliStoryType.LOTTIE -> {
                        currentPlayer = null
                        val lottieComposition by rememberLottieComposition(
                            LottieCompositionSpec.Url(
                                story.mediaUrl!!
                            )
                        )
                        val progress by animateLottieCompositionAsState(
                            lottieComposition,
                            isPlaying = !isPaused,
                            iterations = LottieConstants.IterateForever
                        )
                        if (lottieComposition != null) {
                            isMediaLoaded = true
                            startTimer(remainingTime)
                        }
                        LottieAnimation(
                            lottieComposition,
                            { progress },
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.BottomCenter,
                            contentScale = if (story.scaleType == StoryScaleType.BOTTOM_HORIZONTAL_CROP) ContentScale.FillWidth else ContentScale.Crop
                        )
                    }
                }

                if (!isMediaLoaded) {
                    timer.value?.cancel()
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = magenta_1
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    timer.value?.cancel()
                                    if (it.x < size.width / 2) {
                                        if (currentStoryIndex > 0) {
                                            progressValues[currentStoryIndex] = 0f
                                            currentStoryIndex--
                                            isMediaLoaded = false
                                            remainingTime =
                                                stories[currentStoryIndex].duration.toFloat()
                                        } else {
                                            onMoveListener?.onPreviousClick()
                                        }
                                    } else {
                                        if (currentStoryIndex < stories.size - 1) {
                                            progressValues[currentStoryIndex] = 1f
                                            currentStoryIndex++
                                            isMediaLoaded = false
                                            remainingTime =
                                                stories[currentStoryIndex].duration.toFloat()
                                            onMoveListener?.onFinished(currentStoryIndex)
                                        } else {
                                            onMoveListener?.onAllStoriesCompleted()
                                            onFinishListener?.onAllStoriesFinished()
                                        }
                                    }
                                },
                                onLongPress = {
                                    isPaused = true
                                    timer.value?.cancel()
                                    currentPlayer?.pause()
                                },
                                onPress = {
                                    val release = tryAwaitRelease()
                                    if (release) {
                                        isPaused = false
                                        startTimer(remainingTime)
                                        currentPlayer?.play()
                                    }
                                }
                            )
                        }
                )

                Column(modifier = Modifier.fillMaxSize()) {
                    IconButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { onMoveListener?.onClose() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.chili_ic_close),
                            contentDescription = null,
                            tint = story.titleTextColor?.let { color ->
                                try {
                                    Color(android.graphics.Color.parseColor(color))
                                } catch (e: Exception) {
                                    Color.Black
                                }
                            } ?: Color.Black
                        )
                    }

                    story.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(horizontal = 16.dp),
                            color = story.titleTextColor?.let { color ->
                                try {
                                    Color(android.graphics.Color.parseColor(color))
                                } catch (e: Exception) {
                                    Color.Black
                                }
                            } ?: Color.Black,
                            textAlign = TextAlign.Start,
                            style = Chili.typography.H24_Primary_500
                        )
                    }

                    story.description?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            color = story.subtitleTextColor?.let { color ->
                                try {
                                    Color(android.graphics.Color.parseColor(color))
                                } catch (e: Exception) {
                                    Color.Black
                                }
                            } ?: Color.Black,
                            textAlign = TextAlign.Start,
                            style = Chili.typography.H16_Primary
                        )
                    }
                }

                when (story.buttonType) {
                    ChilliButtonType.SECONDARY -> {
                        story.buttonText?.let {
                            ChiliSecondaryButton(
                                text = it,
                                onClick = {
                                    if (story.deeplink != null) onClickListener?.onDeeplinkClick(
                                        story.deeplink
                                    )
                                    else onMoveListener?.onClose()
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }

                    ChilliButtonType.ADDITIONAL -> {
                        story.buttonText?.let {
                            ChiliAdditionalButton(
                                text = it,
                                onClick = {
                                    if (story.deeplink != null) onClickListener?.onDeeplinkClick(
                                        story.deeplink
                                    )
                                    else onMoveListener?.onClose()
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    currentPlayer?.pause()
                    isPaused = true
                }
                Lifecycle.Event.ON_RESUME -> {
                    currentPlayer?.play()
                    isPaused = false
                }
                Lifecycle.Event.ON_DESTROY -> {
                    currentPlayer?.release()
                    currentPlayer = null
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            timer.value?.cancel()
            currentPlayer?.release()
        }
    }
}


interface StoryMoveListener {
    fun onAllStoriesCompleted()
    fun onClose()
    fun onFinished(index: Int)
    fun onStart(index: Int)
    fun onPreviousClick() {}
}

interface StoryClickListener {
    fun onDeeplinkClick(deeplink: String)
}

interface StoryOnFinishListener {
    fun onAllStoriesFinished()
    fun onStoryClose()
}