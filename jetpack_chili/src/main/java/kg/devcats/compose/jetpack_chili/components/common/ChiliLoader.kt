package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliLoader(
    modifier: Modifier = Modifier,
    color : Color = Chili.color.loader,
    strokeWidth: Dp = 3.dp
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = color,
            strokeWidth = strokeWidth,
            strokeCap = StrokeCap.Round)
    }
}

@Preview
@Composable
fun PreviewChiliLoader() {
    ChiliLoader()
}
