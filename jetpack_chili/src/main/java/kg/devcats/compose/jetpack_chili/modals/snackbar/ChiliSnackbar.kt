package kg.devcats.compose.jetpack_chili.modals.snackbar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ChiliSnackBar(
    snackbarMessage: SnackbarMessage,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp
) {
    var remainingTime by remember {
        mutableLongStateOf(
            (snackbarMessage.progressDurationMillis
                ?: snackbarMessage.snackbarDurationMillis) / 1000
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(
        snackbarMessage.progressDurationMillis ?: snackbarMessage.snackbarDurationMillis
    ) {
        delay(snackbarMessage.progressDurationMillis ?: snackbarMessage.snackbarDurationMillis)
        snackbarMessage.onDismiss?.invoke()
        SnackbarManager.dismissSnackbar()
    }

    if (snackbarMessage.type == SnackbarType.TIMER && snackbarMessage.progressDurationMillis != null) {
        scope.launch {
            while (remainingTime > 0) {
                delay(1000L)
                remainingTime -= 1
            }
        }
    }

    Card(
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = snackbarMessage.backgroundColor ?: Chili.color.snackbarBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = if (snackbarMessage.type != SnackbarType.TIMER) 12.dp else 4.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            snackbarMessage.iconRes?.let { iconRes ->
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }

            if (snackbarMessage.type == SnackbarType.LOADER) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically),
                    strokeWidth = 2.dp
                )
            } else if (snackbarMessage.type == SnackbarType.TIMER && snackbarMessage.progressDurationMillis != null) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        progress = { remainingTime / (snackbarMessage.progressDurationMillis / 1000f) },
                        modifier = Modifier.size(32.dp),
                        color = Chili.color.snackbarIndeterminateColor,
                        trackColor = Chili.color.snackbarBackground,
                        strokeWidth = 1.dp
                    )
                    if (remainingTime > 0) {
                        Text(
                            text = "$remainingTime",
                            modifier = Modifier.align(Alignment.Center),
                            style = Chili.typography.H14_Primary
                        )
                    }
                }
            }

            Text(
                text = snackbarMessage.message,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                style = Chili.typography.H14_Primary
            )

            snackbarMessage.actionText?.let { actionText ->
                TextButton(
                    onClick = {
                        snackbarMessage.onActionClick?.invoke()
                        SnackbarManager.dismissSnackbar()
                    },
                    modifier = Modifier.align(Alignment.CenterVertically),

                    ) {
                    Text(
                        text = actionText,
                        color = Chili.color.buttonComponentText,
                        style = Chili.typography.H14
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SnackbarPreview() {
    ChiliSnackBar(
        snackbarMessage = SnackbarMessage(
            message = "Timer Snackbar",
            type = SnackbarType.TIMER,
            progressDurationMillis = 10000,
            actionText = "Dismiss",
            onActionClick = { println("Dismiss clicked!") }
        )
    )
}
