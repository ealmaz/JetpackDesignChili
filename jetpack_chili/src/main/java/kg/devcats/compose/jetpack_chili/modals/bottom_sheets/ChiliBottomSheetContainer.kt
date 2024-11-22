package kg.devcats.compose.jetpack_chili.modals.bottom_sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChiliBottomSheetContainer(
    isShown: Boolean,
    hideOnSwipe: Boolean = true,
    isCloseIconVisible: Boolean = true,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    var internalBottomSheetVisibilityState by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { hideOnSwipe })
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
            Surface(
                shape = Chili.shapes.RoundedCornerShape,
                contentColor = Color.Unspecified,
                color = Chili.color.bottomSheetBackground,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    if (isCloseIconVisible) ChiliBottomSheetCloseIcon(onDismissRequest)
                    else Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)) {
                        content.invoke(this)
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.ChiliBottomSheetCloseIcon(onDismissRequest: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.chili_ic_circle_clear),
        contentDescription = "",
        modifier = Modifier
            .align(Alignment.End)
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
            ) { onDismissRequest.invoke() },
    )
}
