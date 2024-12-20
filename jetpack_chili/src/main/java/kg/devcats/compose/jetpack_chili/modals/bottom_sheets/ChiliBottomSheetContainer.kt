package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiliBottomSheetContainer(
    modifier: Modifier = Modifier,
    isShown: Boolean,
    hideOnSwipe: Boolean = true,
    isCloseIconVisible: Boolean = true,
    isTopIconVisible: Boolean = false,
    isTopInnerIconVisible: Boolean = false,
    topIconColor: Color = Chili.color.bottomSheetTopIconColor,
    bottomSheetShape: Shape = Chili.shapes.RoundedCornerShape,
    backgroundColor: Color = Chili.color.bottomSheetBackground,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    var internalBottomSheetVisibilityState by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true, confirmValueChange = { hideOnSwipe })
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = isShown) {
        if (isShown) {
            internalBottomSheetVisibilityState = true
        } else {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                internalBottomSheetVisibilityState = false
            }
        }
    }

    if (internalBottomSheetVisibilityState) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            contentColor = Color.Unspecified,
            containerColor = Color.Transparent,
            shape = Chili.shapes.CornerNone,
            dragHandle = null,
        ) {
            if (isTopIconVisible) {
                Column(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(3.dp)
                            .width(40.dp)
                            .background(
                                color = topIconColor,
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                    Spacer(Modifier.height(6.dp))
                    BottomSheetContent(
                        modifier = modifier,
                        isCloseIconVisible = isCloseIconVisible,
                        shape = bottomSheetShape,
                        backgroundColor = backgroundColor,
                        onDismissRequest = onDismissRequest,
                        content = content
                    )
                }
            } else {
                BottomSheetContent(
                    modifier = modifier,
                    isCloseIconVisible = isCloseIconVisible,
                    isTopInnerIconVisible = isTopInnerIconVisible,
                    topIconColor = topIconColor,
                    shape = bottomSheetShape,
                    backgroundColor = backgroundColor,
                    onDismissRequest = onDismissRequest,
                    content = content
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    isCloseIconVisible: Boolean = true,
    isTopInnerIconVisible: Boolean = false,
    topIconColor: Color = Chili.color.bottomSheetTopIconColor,
    shape: Shape = Chili.shapes.RoundedCornerShape,
    backgroundColor: Color = Chili.color.bottomSheetBackground,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        shape = shape,
        contentColor = Color.Unspecified,
        color = backgroundColor,
        modifier = modifier,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (isTopInnerIconVisible) Spacer(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(5.dp)
                        .width(40.dp)
                        .background(
                            color = topIconColor,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .align(Alignment.TopCenter)
                )
                if (isCloseIconVisible) ChiliBottomSheetCloseIcon(onDismissRequest)
            }
            if (!isCloseIconVisible) Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)) {
                content.invoke(this)
            }
        }
    }
}

@Composable
private fun BoxScope.ChiliBottomSheetCloseIcon(onDismissRequest: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.chili_ic_circle_clear),
        contentDescription = "",
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
            ) { onDismissRequest.invoke() },
    )
}