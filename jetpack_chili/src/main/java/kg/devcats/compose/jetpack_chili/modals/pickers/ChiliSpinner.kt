package kg.devcats.compose.jetpack_chili.modals.pickers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.pixelsToDp
import kg.devcats.compose.jetpack_chili.theme.Chili

const val EMPTY_ITEM = " "

@Composable
fun ChiliSpinner(
    modifier: Modifier = Modifier,
    items: List<String>,
    currentIndex: Int = 0,
    preVisibleItemCount: Int = 1,
//    isEndlessScroll: Boolean = false,
    onItemSelected: (Int) -> Unit,
) {
    var itemHeight by remember { mutableIntStateOf(0) }

    Box(modifier = modifier.height(pixelsToDp(pixels = itemHeight * (preVisibleItemCount * 2 + 1)))) {

//        if (isEndlessScroll) UnlimitedScrollSpinner(
//            items = items,
//            currentIndex = currentIndex,
//            preVisibleItemCount = preVisibleItemCount,
//            onItemSelected = onItemSelected,
//            itemHeight = itemHeight,
//            onItemHeightChanged = { itemHeight = it }
//        )
//        else
        LimitedScrollSpinner(
            items = items,
            onItemSelected = onItemSelected,
            currentIndex = currentIndex,
            itemHeight = itemHeight,
            onItemHeightChanged = { itemHeight = it }
        )

        Divider(modifier = Modifier
            .padding(top = pixelsToDp(pixels = itemHeight * preVisibleItemCount))
            .fillMaxWidth()
            .height(1.dp))
        Divider(modifier = Modifier
            .padding(top = pixelsToDp(pixels = itemHeight * (preVisibleItemCount + 1)))
            .fillMaxWidth()
            .height(1.dp))
    }
}

@Composable
private fun LimitedScrollSpinner(
    items: List<String>,
    currentIndex: Int,
    preVisibleItemCount: Int = 1,
    onItemSelected: (Int) -> Unit,
    itemHeight: Int,
    onItemHeightChanged: (newHeight: Int) -> Unit
) {
    val currentItems = remember(items) { items.toMutableList().apply {
        repeat(preVisibleItemCount) {
            add(0, EMPTY_ITEM)
            add(EMPTY_ITEM)
        }
    } }

    val listState = rememberLazyListState(0)

    LaunchedEffect(key1 = currentIndex) {
        listState.animateScrollToItem((currentIndex).takeIf { it >= 0 } ?: 0)
    }

    var currentValue = 0

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        if (listState.firstVisibleItemScrollOffset != 0) {
            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
        } else onItemSelected(currentValue)
    }



    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState,
        content = {
            itemsIndexed(currentItems) { index, item ->

                val isSelected = index == listState.firstVisibleItemIndex + preVisibleItemCount

                if (isSelected) currentValue = index

                SpinnerViewItem(isSelected = isSelected, itemText = item, modifier = Modifier.onSizeChanged { if (it.height != itemHeight) onItemHeightChanged.invoke(it.height) })
            }
        }
    )
}
//
//@Composable
//private fun UnlimitedScrollSpinner(
//    items: List<String>,
//    currentIndex: Int,
//    preVisibleItemCount: Int,
//    onItemSelected: (Int) -> Unit,
//    itemHeight: Int,
//    onItemHeightChanged: (newHeight: Int) -> Unit
//) {
//    val actualIndex = remember(key1 = currentIndex) {
//        val middleIndex = Int.MAX_VALUE / 2
//        middleIndex - (middleIndex % items.size) - preVisibleItemCount + currentIndex
//    }
//    val listState = rememberLazyListState(actualIndex)
//    var currentValue by remember { mutableStateOf(0) }
//
//
//    LaunchedEffect(key1 = !listState.isScrollInProgress) {
//        if (listState.firstVisibleItemScrollOffset != 0) {
//            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
//            onItemSelected(currentValue)
//        }
//    }
//
//    LazyColumn(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        state = listState,
//        content = {
//            items(Int.MAX_VALUE) {
//
//                val index = it % items.size
//
//                val isSelected = it == listState.firstVisibleItemIndex + preVisibleItemCount
//
//                if (isSelected) currentValue = index
//
//                SpinnerViewItem(isSelected = isSelected, itemText = items[index], modifier = Modifier.onSizeChanged {
//                    if (it.height != itemHeight) onItemHeightChanged.invoke(it.height)
//                })
//            }
//        }
//    )
//}


@Composable
private fun SpinnerViewItem(modifier: Modifier = Modifier, itemText: String, isSelected: Boolean) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = itemText,
            style = Chili.typography.H16_Primary,
            color = if (isSelected) Chili.color.primaryText else Chili.color.dividerColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

