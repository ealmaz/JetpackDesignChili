package kg.devcats.compose.jetpack_chili.components.divider

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

object TitledDividerDefaults {

    /**
     * Creates a params for TitledDivider that represents the default container.
     */
    @Composable
    fun titledDividerParams(
        title: String,
        subtitle: String? = null,
        titleTextStyle: TextStyle = Chili.typography.H20_Primary_700,
        subtitleTextStyle: TextStyle = Chili.typography.H14_Primary,
        titleMaxLines: Int = 2,
        subtitleMaxLines: Int = 2,
        notificationIcon: Painter = painterResource(R.drawable.chili_ic_notification),
        isNotificationVisible: Boolean = false,
        isShevronIsVisible: Boolean = false,
        hideAbleContent: @Composable (() -> Unit)? = null
    ): TitleDividerParams {
        return TitleDividerParams(
            title = title,
            subtitle = subtitle,
            titleTextStyle = titleTextStyle,
            subtitleTextStyle = subtitleTextStyle,
            titleMaxLines = titleMaxLines,
            subtitleMaxLines = subtitleMaxLines,
            notificationIcon = notificationIcon,
            isNotificationVisible = isNotificationVisible,
            isShevronIsVisible = isShevronIsVisible,
            hideAbleContent = hideAbleContent
        )
    }

}

data class TitleDividerParams(
    val title: String,
    val subtitle: String?,
    val titleTextStyle: TextStyle,
    val subtitleTextStyle: TextStyle,
    val titleMaxLines: Int,
    val subtitleMaxLines: Int,
    val notificationIcon: Painter,
    val isNotificationVisible: Boolean,
    val isShevronIsVisible: Boolean,
    val hideAbleContent: @Composable (() -> Unit)? = null
)