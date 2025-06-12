package kg.devcats.compose.jetpack_chili.modals.pickers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.emoji2.text.EmojiCompat
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiliEmojiBottomSheet(
    showSheet: Boolean,
    onDismissRequest: () -> Unit = {},
    onEmojiSelected: (String) -> Unit,
    onEmojiDelete: () -> Unit
) {
    if (showSheet) {
        val sheetState = rememberModalBottomSheetState(
        )

        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            dragHandle = null
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            ) {
                EmojiPickerCompat(onEmojiSelected = onEmojiSelected, onEmojiDelete = onEmojiDelete)
            }
        }
    }
}

@Composable
fun EmojiPickerCompat(
    onEmojiSelected: (String) -> Unit = {},
    onEmojiDelete: () -> Unit = {}
) {
    val context = LocalContext.current
    var isEmojiCompatInitialized by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = { emojiCategories.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (!EmojiCompat.isConfigured()) {
            EmojiCompat.init(BundledEmojiCompatConfig(context))
        }

        EmojiCompat.get().registerInitCallback(object : EmojiCompat.InitCallback() {
            override fun onInitialized() {
                isEmojiCompatInitialized = true
            }

            override fun onFailed(throwable: Throwable?) {
                isEmojiCompatInitialized = false
            }
        })
    }

    if (!isEmojiCompatInitialized) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (isEmojiCompatInitialized) {
        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            ) {
                emojiCategories.forEachIndexed { index, category ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        modifier = Modifier.weight(1f),
                        text = { Text(category.icon, fontSize = 10.sp) }
                    )
                }
                Tab(
                    selected = pagerState.currentPage == emojiCategories.size,
                    onClick = {
                        onEmojiDelete()
                    },
                    icon = {
                        Icon(painter = painterResource(R.drawable.chili_ic_remove_digit), contentDescription = null, modifier = Modifier.size(16.dp), tint = Chili.color.primaryIcon )
                    },
                )
            }

            HorizontalPager(
                state = pagerState,
                beyondViewportPageCount = 1
            ) { pageIndex ->
                EmojiCategoryPage(
                    category = emojiCategories[pageIndex],
                    onEmojiSelected = onEmojiSelected
                )
            }
        }
    }
}

@Composable
fun EmojiCompatItem(
    emoji: String,
    onClick: (String) -> Unit
) {
    Text(
        text = emoji,
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick(emoji) }
            .padding(4.dp)
            .size(48.dp)
            .wrapContentSize(),
        style = Chili.typography.H16,
        textAlign = TextAlign.Center
    )
}

@Composable
fun EmojiCategoryPage(
    category: EmojiCategory,
    onEmojiSelected: (String) -> Unit
) {
    var emojiList by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(category.name) {
        generateEmojiList(category.range) { result ->
            emojiList = result
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(9),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(
            items = emojiList,
            key = { it }
        ) { emoji ->
            EmojiCompatItem(
                emoji = emoji,
                onClick = onEmojiSelected
            )
        }
    }
}


private suspend fun generateEmojiList(
    range: IntRange,
    onListGenerated: (List<String>) -> Unit
) = withContext(Dispatchers.Default) {
    val emojiCompat = EmojiCompat.get()
    val emojis = LinkedHashSet<String>()

    for (codePoint in range) {
        if (Character.isValidCodePoint(codePoint)) {
            val chars = Character.toChars(codePoint)
            val emoji = String(chars)
            if (emojiCompat.hasEmojiGlyph(emoji)) {
                emojis.add(emoji)
            }
        }
    }

    onListGenerated(emojis.distinct())
}

val emojiCategories = listOf(
    EmojiCategory("😀", "Smileys", 0x1F600..0x1F64F),
    EmojiCategory("🐻", "Animals", 0x1F400..0x1F4FF),
    EmojiCategory("🍔", "Food", 0x1F350..0x1F37F),
    EmojiCategory("⚽", "Activities", 0x1F3C0..0x1F3FF),
    EmojiCategory("🚗", "Travel", 0x1F680..0x1F6FF),
    EmojiCategory("💡", "Objects", 0x1F4A0..0x1F4FF),
    EmojiCategory("#️⃣", "Symbols", 0x2700..0x27BF),
    EmojiCategory("🏳️", "Flags", 0x1F1E6..0x1F1FF),
)

data class EmojiCategory(
    val icon: String,
    val name: String,
    val range: IntRange
)