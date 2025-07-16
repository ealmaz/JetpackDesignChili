package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun AdditionalLabelCard(
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color = Chili.color.contentSecondary,
    icon: Painter? = null,
    title: String,
    subtitle: String? = null,
    additionalLabel: String? = null,
    isHighlighted: Boolean = false,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {

    if (isLoading) {
        LoadingState(modifier)
        return
    }

    Box {
        if (isHighlighted) {
            AnimatedGradientBackground(
                modifier = Modifier
                    .clip(Chili.shapes.RoundedCornerShape)
                    .matchParentSize(),
            )
        }

        Column(
            modifier = modifier
                .then(
                    if (isHighlighted) Modifier.padding(1.5.dp)
                    else Modifier
                )
                .clip(Chili.shapes.RoundedCornerShape)
                .background(cardBackgroundColor)
                .clickable(onClick = onClick)
                .then(
                    if (isHighlighted) Modifier.padding(6.5.dp)
                    else Modifier.padding(8.dp)
                )
        ) {
            Row {
                icon?.let {
                    Image(
                        painter = icon,
                        modifier = Modifier.size(32.dp),
                        contentDescription = null,
                    )
                }

                Spacer(Modifier.weight(1f))

                additionalLabel?.let {
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Chili.color.animatedCardNoteBg)
                            .padding(4.dp),
                        text = it,
                        style = Chili.typography.H12_Secondary,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title.parseHtml(),
                style = Chili.typography.H14_Primary_500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            subtitle?.let {
                Text(
                    text = subtitle,
                    style = Chili.typography.H12_Primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    Column(
        modifier = modifier
            .clip(Chili.shapes.RoundedCornerShape)
            .background(Chili.color.contentSecondary)
            .padding(8.dp)
    ) {
        Shimmer(
            width = 32.dp,
            height = 32.dp,
        )

        Spacer(Modifier.height(14.dp))

        Shimmer(
            width = 82.dp,
            height = 7.dp,
        )

        Spacer(Modifier.height(8.dp))

        Shimmer(
            width = 56.dp,
            height = 7.dp,
        )

        Spacer(Modifier.height(12.dp))

        Shimmer(
            width = 56.dp,
            height = 7.dp,
        )
    }
}

@Composable
@Preview
private fun AdditionalLabelCardPreview() {
    AdditionalLabelCard(
        Modifier.size(120.dp, 106.dp),
        icon = painterResource(R.drawable.chili_ic_documents_green),
        additionalLabel = "10%",
        subtitle = "no commission",
        isHighlighted = true,
        title = "Header and header"
    )
}

@Composable
@Preview
private fun AdditionalLabelCardPreview2() {
    AdditionalLabelCard(
        Modifier.size(120.dp, 106.dp),
        icon = painterResource(R.drawable.chili_ic_documents_green),
        additionalLabel = "10%",
        isLoading = true,
        subtitle = "no commission",
        isHighlighted = true,
        title = "Header and header"
    )
}
