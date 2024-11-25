package kg.devcats.compose.samples.ui.chili_sample

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kg.devcats.compose.jetpack_chili.components.banner.AutoScrollBannerView
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PreviewAutoScrollBanners(navigateUp: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Banners",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            AutoScrollBannerView(
                Modifier.fillMaxWidth(),
                listOf(
                "https://postium.ru/wp-content/uploads/2024/10/mem-bu-ispugalsya-ne-bojsya.jpg",
                "https://opis-cdn.tinkoffjournal.ru/mercury/in-out-best-dog-memes.ezmero3nh2af..jpg",
                "https://0d314c86-f76b-45cc-874e-45816116a667.selcdn.net/9e97e7f1-2406-49bb-9532-72c78627221c.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4pBufLFEQNHM1lHEm7gqayDaMxSB1hwmapQ&s",
                "https://redcat7.ru/wp-content/uploads/2015/05/wmnrVaZ.jpg",
            )) {
                Toast.makeText(context, "Banner clicked $it", Toast.LENGTH_SHORT).show()
            }
        }
    }
}