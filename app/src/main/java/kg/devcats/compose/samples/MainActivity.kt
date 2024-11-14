package kg.devcats.compose.samples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kg.devcats.compose.jetpack_chili.theme.ChiliTheme
import kg.devcats.compose.samples.ui.navigation.SamplesNavGraph
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Locale.setDefault(Locale("ru"))
        setContent {
            ChiliTheme {
                SamplesNavGraph()
            }
        }
    }
}