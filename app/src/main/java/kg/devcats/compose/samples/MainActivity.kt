package kg.devcats.compose.samples

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.extensions.findComponentActivity
import kg.devcats.compose.jetpack_chili.theme.ChiliTheme
import kg.devcats.compose.samples.ui.navigation.SamplesNavGraph
import java.util.Locale

var isShimmeringState = false
val LocalValueShimmering = compositionLocalOf { false }

class MainActivity : AppCompatActivity(), SystemSettings {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Locale.setDefault(Locale("ru"))
        setContent {
            CompositionLocalProvider(LocalValueShimmering provides isShimmeringState) {
                ChiliTheme(darkTheme = isCurrentDarkTheme(), edgeToEdgeEnabled = true) {
                    SamplesNavGraph()
                }
            }
        }
    }

    override fun onThemeClick() {
        when (isCurrentDarkTheme()) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            }
        }
    }

    override fun onShimmeringClick() {
        isShimmeringState = !isShimmeringState
        recreate()
    }

    private fun isCurrentDarkTheme(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
}

@Composable
internal fun RowScope.SampleToolbarMenu() {
    val context = LocalContext.current
    IconButton(
        onClick = { (context.findComponentActivity() as? SystemSettings)?.onThemeClick() }
    ) {
        Icon(painter = painterResource(R.drawable.sample_change_theme_icon), modifier = Modifier.size(28.dp), contentDescription = null, tint = Color.Unspecified)
    }
    IconButton(
        onClick = { (context.findComponentActivity() as? SystemSettings)?.onShimmeringClick() }
    ) {
        Icon(painter = painterResource(R.drawable.sample_shimmer_icon), modifier = Modifier.size(28.dp), contentDescription = null, tint = Color.Unspecified)
    }
}

interface SystemSettings {
    fun onThemeClick()
    fun onShimmeringClick()
}