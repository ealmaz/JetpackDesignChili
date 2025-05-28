package kg.devcats.compose.jetpack_chili.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliAppToolbar(
    modifier: Modifier = Modifier,
    navigationIcon: Painter = painterResource(id = R.drawable.chili4_ic_back_arrow_rounded),
    isNavigationIconVisible: Boolean = true,
    onNavigationIconClick: () -> Unit = {},
    startFrame: (@Composable RowScope.() -> Unit)? = null,
    title: String,
    backgroundColor: Color = Chili.color.toolbarBackground,
    endFrame: (@Composable RowScope.() -> Unit)? = null,
    isDividerVisible: Boolean = false,
    titleStyle: TextStyle = Chili.typography.H16_Primary_500,
) {

    Surface(modifier = modifier, color = backgroundColor) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
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

                Text(
                    modifier = Modifier
                        .padding(vertical = 18.dp)
                        .weight(1f),
                    text = title,
                    style = titleStyle
                )

                endFrame?.let {
                    Row(verticalAlignment = Alignment.CenterVertically) { it.invoke(this) }
                }

                Spacer(modifier = Modifier.width(16.dp))
            }
            if (isDividerVisible) Divider(Modifier.height(1.dp))
        }

    }
}

@Preview
@Composable
fun PreviewChiliAppToolbarNav() {
    ChiliAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Заголовок",
        isNavigationIconVisible = true
    )

}

@Preview
@Composable
fun PreviewChiliAppToolbarNoIcon() {
    ChiliAppToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = "Header",
        isNavigationIconVisible = false
    )

}

@Preview
@Composable
fun PreviewChiliAppToolbar() {
    ChiliAppToolbar(
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
fun PreviewChiliAppToolbarEndFrame() {
    ChiliAppToolbar(
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