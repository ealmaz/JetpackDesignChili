package kg.devcats.compose.jetpack_chili.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val materialDarkColorScheme = darkColorScheme(
    tertiary = magenta_1,
    surface = black_1,
    outlineVariant = black_4,
)
private val materialLightColorScheme = lightColorScheme(
    surface = Color.Unspecified,
    primary = magenta_1,
    onPrimary = white_1,
    surfaceVariant = gray_3,
    outlineVariant = gray_6,
)

private val chiliDefaultLightColorScheme = ChiliLightColorScheme()
private val chiliDefaultDarkColorScheme = ChiliDarkColorScheme()

private val chiliDefaultLightTypography = ChiliTypography(chiliDefaultLightColorScheme)
private val chiliDefaultDarkTypography = ChiliTypography(chiliDefaultDarkColorScheme)

@Composable
fun ChiliTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val materialColorScheme = when {
        darkTheme -> materialDarkColorScheme
        else -> materialLightColorScheme
    }

    val chiliColorScheme = when {
        darkTheme -> chiliDefaultDarkColorScheme
        else -> chiliDefaultLightColorScheme
    }

    val view = LocalView.current

    LaunchedEffect(darkTheme) {
        if (darkTheme && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (view.context as? Activity)?.run {
                window.statusBarColor = black_1.toArgb()
                window.navigationBarColor = black_1.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
            }
        }
    }

    CompositionLocalProvider(
        LocalChiliTypography provides if (darkTheme) chiliDefaultDarkTypography else chiliDefaultLightTypography,
        LocalChiliColorScheme provides chiliColorScheme,
        LocalChiliThemeAttributes provides ChiliThemeAttributes,
        LocalChiliShapes provides Chili.shapes
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}

@Composable
fun ChiliTransparentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val transparentMaterialColorScheme = materialDarkColorScheme.copy(
        background = Color.Transparent,
        surface = Color.Transparent,
        onBackground = Color.Transparent,
        onSurface = Color.Transparent
    )

    val transparentChiliColorScheme = chiliDefaultDarkColorScheme.copy(
        screenBackground = Color.Transparent,
        surfaceBackground = Color.Transparent,
        toolbarBackground = Color.Transparent,
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalChiliTypography provides if (darkTheme) chiliDefaultDarkTypography else chiliDefaultLightTypography,
        LocalChiliColorScheme provides transparentChiliColorScheme,
        LocalChiliThemeAttributes provides ChiliThemeAttributes,
        LocalChiliShapes provides Chili.shapes
    ) {
        MaterialTheme(
            colorScheme = transparentMaterialColorScheme,
            content = content
        )
    }
}




object Chili {
    val typography: ChiliTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalChiliTypography.current

    val color: ChiliColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalChiliColorScheme.current

    val shapes: ChiliShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalChiliShapes.current

    val attrs: ChiliThemeAttributes
        @Composable
        @ReadOnlyComposable
        get() = LocalChiliThemeAttributes.current
}