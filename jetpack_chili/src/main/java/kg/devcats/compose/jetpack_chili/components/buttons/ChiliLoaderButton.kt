package kg.devcats.compose.jetpack_chili.components.buttons


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader

@Composable
fun ChiliLoaderButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (isLoading) ChiliLoader(modifier = Modifier.height(50.dp))
        else ChiliPrimaryButton(text = text, enabled = enabled, onClick = onClick, modifier = Modifier.fillMaxWidth())
    }



}

@Preview()
@Composable
fun PreviewChiliLoaderButton() {
    ChiliLoaderButton(text = "Loader", modifier = Modifier.fillMaxWidth()) {}

}
