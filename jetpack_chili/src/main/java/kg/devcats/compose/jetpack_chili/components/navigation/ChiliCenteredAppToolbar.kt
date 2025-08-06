package kg.devcats.compose.jetpack_chili.components.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ripple
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.shimmer.ShowShimmerOrContent
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCenteredAppToolbar(
    modifier: Modifier = Modifier,
    navigationIcon: Painter = painterResource(id = R.drawable.chili4_ic_back_arrow_rounded),
    isNavigationIconVisible: Boolean = true,
    onNavigationIconClick: () -> Unit = {},
    startFrame: (@Composable RowScope.() -> Unit)? = null,
    title: String,
    backgroundColor: Color = Chili.color.toolbarBackground,
    endFrame: (@Composable RowScope.() -> Unit)? = null,
    isDividerVisible: Boolean = false,
    isLoading: Boolean = false
) {
    Surface(modifier = modifier, color = backgroundColor) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
                Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isNavigationIconVisible) {
                            Image(
                                painter = navigationIcon,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(24.dp)
                                    .clickable(
                                        onClick = onNavigationIconClick,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = ripple(bounded = false),
                                    )
                            )
                        } else Spacer(modifier = Modifier.width(16.dp))

                        startFrame?.let {
                            Row(verticalAlignment = Alignment.CenterVertically) { it.invoke(this) }
                        }
                    }
                }
                
                Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(2f)) {
                    ShowShimmerOrContent(
                        modifier = Modifier
                            .padding(vertical = 24.dp),
                        shimmerWidth = 160.dp,
                        shimmerHeight = 6.dp,
                        isLoading = isLoading
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 18.dp),
                            text = title,
                            style = Chili.typography.H16_Primary_500,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.weight(1f)) {
                    endFrame?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            it.invoke(this)

                        }
                    }
                }
            }
            if (isDividerVisible) Divider(Modifier.height(1.dp))
        }

    }
}

@Preview
@Composable
fun PreviewChiliCenteredAppToolbarNav() {
    ChiliCenteredAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Заголовок",
        isNavigationIconVisible = true
    )

}

@Preview
@Composable
fun PreviewChiliCenteredAppToolbarWithLongText() {
    ChiliCenteredAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Заголовок с длинным текстом"
    )

}

@Preview
@Composable
fun PreviewChiliCenteredAppToolbarNoIcon() {
    ChiliCenteredAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Header",
        isNavigationIconVisible = false
    )

}

@Preview
@Composable
fun PreviewCenteredChiliAppToolbar() {
    ChiliCenteredAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Header",
        isNavigationIconVisible = false,
        startFrame = {
            Image(
                modifier = Modifier
                    .size(46.dp)
                    .padding(end = 8.dp),
                painter = painterResource(id = R.drawable.chili_ic_documents_green),
                contentDescription = "")
        }
    )
}

@Preview
@Composable
fun PreviewChiliCenteredAppToolbarEndFrame() {
    ChiliCenteredAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Header",
        isNavigationIconVisible = true,
        endFrame = {
            Image(
                modifier = Modifier.size(46.dp),
                painter = painterResource(id = R.drawable.chili_ic_documents_green),
                contentDescription = "")
        }
    )
}
