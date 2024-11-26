package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliChevron(modifier: Modifier = Modifier, tint: Color = Chili.color.chevronColor) {
    Image(
        modifier = modifier.size(32.dp),
        painter = painterResource(id = R.drawable.chili_ic_chevron),
        contentDescription = "",
        colorFilter = ColorFilter.tint(tint)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewChiliChevron() {
    ChiliChevron()
}