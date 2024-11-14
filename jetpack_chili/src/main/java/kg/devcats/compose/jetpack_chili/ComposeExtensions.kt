package kg.devcats.compose.jetpack_chili

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }