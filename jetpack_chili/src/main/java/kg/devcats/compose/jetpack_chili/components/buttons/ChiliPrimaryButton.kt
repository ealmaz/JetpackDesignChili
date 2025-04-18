package kg.devcats.compose.jetpack_chili.components.buttons


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.rippleClickable
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    icon: Any? = null,
    isLoading: Boolean = false,
    buttonSize: ButtonSize = ButtonSize.REGULAR,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .rippleClickable(
                enabled = enabled,
                rippleColor = Color.Red,
                onClick = onClick,
                bounded = true
            ),
        onClick = onClick,
        enabled = enabled,
        shape = Chili.shapes.RoundedCornerShape,
        contentPadding = PaddingValues(
            horizontal = buttonSize.horizontalPadding,
        ),
        colors = primaryButtonColors()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                ChiliLoader(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .size(buttonSize.iconSize)
                        .align(Alignment.CenterVertically)
                    ,
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                icon?.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = it),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .size(buttonSize.iconSize)
                            .clip(Chili.shapes.RoundedCornerShape),
                    )
                    Spacer(modifier = Modifier.width(buttonSize.iconPadding))
                }
            }
            Text(
                modifier = Modifier.padding(vertical = buttonSize.verticalPadding),
                text = if (isLoading) "" else text,
                maxLines = 1,
                style = Chili.typography.H14_Primary_500,
                color = Chili.color.buttonPrimaryText,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliPrimaryButton() {
    ChiliPrimaryButton(
        isLoading = false,
        icon = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.flaticon.com%2Ffree-icon%2Furl_7347153&psig=AOvVaw2ZmN5HcKsWq9f0gxqhaVVW&ust=1743842218223000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCJDynNL8vYwDFQAAAAAdAAAAABAE",
        text = "Primary button", modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){

    }
}
