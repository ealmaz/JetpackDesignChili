package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun RoundedBox(modifier: Modifier = Modifier, contentColor: Color = Chili.color.shadowContainerContent, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        color = contentColor,
        shape = Chili.shapes.RoundedCornerShape,
    ) {
        content.invoke()
    }
}