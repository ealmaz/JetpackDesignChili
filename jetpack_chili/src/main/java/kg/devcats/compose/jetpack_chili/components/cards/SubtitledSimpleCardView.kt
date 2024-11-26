package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.ShimmerView
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun SubtitledSimpleCardView(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    titleStyle: TextStyle = Chili.typography.H16_Primary,
    subtitleStyle: TextStyle = Chili.typography.H12_Secondary,
    icon: Painter? = null,
    iconUrl: String? = null,
    secondaryIcon: Painter? = null,
    emoji: String? = null,
    onCardClick: (() -> Unit)? = null,
    isLoading: Boolean = false
) {
    if (isLoading) {
        LoadingCard()
    } else {
        Surface(
            modifier = modifier.clickable(enabled = onCardClick != null) { onCardClick?.invoke() },
            color = Chili.color.cardViewBackground,
            contentColor = Color.Unspecified
        ) {
            ContentCard(
                title = title,
                subtitle = subtitle,
                titleStyle = titleStyle,
                subtitleStyle = subtitleStyle,
                icon = icon,
                iconUrl = iconUrl,
                secondaryIcon = secondaryIcon,
                emoji = emoji
            )
        }
    }

}

@Composable
private fun LoadingCard() {
    Surface(
        color = Chili.color.cardViewBackground,
        contentColor = Color.Unspecified
    ) {
    Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerView(modifier = Modifier.size(32.dp), height = 32.dp, width = 32.dp)
        Spacer(modifier = Modifier.width(8.dp))
        ShimmerView(modifier = Modifier.padding(top = 4.dp), height = 8.dp, width = 120.dp)
    }
        }
}

@Composable
private fun ContentCard(
    title: String,
    subtitle: String?,
    titleStyle: TextStyle,
    subtitleStyle: TextStyle,
    icon: Painter?,
    iconUrl: String?,
    secondaryIcon: Painter?,
    emoji: String?,
) {
    Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null || emoji != null || iconUrl != null) {
            IconOrEmoji(icon = icon, iconUrl = iconUrl, emoji = emoji)
        }

        Column(
            modifier = Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, style = titleStyle, maxLines = 1)
            subtitle?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it, style = subtitleStyle, maxLines = 1)
            }
        }

        if (secondaryIcon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = secondaryIcon,
                contentDescription = "Secondary Icon",
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun IconOrEmoji(
    icon: Painter?,
    emoji: String?,
    iconUrl: String?
) {
    when {
        iconUrl != null -> {
            AsyncImage(
                model = iconUrl,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(32.dp),
                contentDescription = "Icon"
            )
        }

        icon != null -> {
            Image(
                painter = icon,
                contentDescription = "Icon",
                modifier = Modifier.size(32.dp)
            )
        }

        emoji != null -> {
            Box(modifier = Modifier.size(32.dp)) {
                Text(
                    text = emoji,
                    style = Chili.typography.H24,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SubtitledSimpleCardViewPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
        ShadowRoundedBox {
            SubtitledSimpleCardView(
                title = "Main Title",
                subtitle = "This is a subtitle",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                secondaryIcon = painterResource(id = R.drawable.chili_ic_documents_green)
            )
        }

        ShadowRoundedBox {
            SubtitledSimpleCardView(
                title = "Card Title",
                emoji = "🔥"
            )
        }

        ShadowRoundedBox {
            SubtitledSimpleCardView(
                title = "Card Title",
                subtitle = "Subtitle",
                icon = painterResource(id = R.drawable.chili_ic_documents_green),
                secondaryIcon = painterResource(id = R.drawable.chili_ic_documents_green),
                isLoading = true
            )
        }
    }
}