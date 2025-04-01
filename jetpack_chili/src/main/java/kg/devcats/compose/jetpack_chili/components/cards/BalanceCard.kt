package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.common.ChiliChevron
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    title: String,
    balance: String? = null,
    titleIcon: Painter? = null,
    balanceIcon: Painter? = null,
    hideChevron: Boolean? = false,
    actionText: String? = null,
    onActionTextClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    isLoading: Boolean? = false
) {
    Surface(
        color = Chili.color.cardViewBackground,
        contentColor = Color.Unspecified,
        modifier = modifier.run {
            if (onClick != null) clickable { onClick.invoke() }
            else this
        },
    ) {
        Row(
            modifier = Modifier
                .height(72.dp)
                .padding(top = 8.dp, end = 16.dp, bottom = 12.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                if (isLoading == true) {
                    Shimmer(
                        modifier = Modifier.padding(top = 12.dp), height = 8.dp, width = 52.dp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Shimmer(
                        modifier = Modifier.padding(bottom = 8.dp), height = 8.dp, width = 104.dp
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        titleIcon?.let {
                            Image(
                                painter = it,
                                contentDescription = "",
                                modifier = Modifier
                                    .clip(Chili.shapes.RoundedCornerShape)
                                    .padding(end = 8.dp)
                            )
                        }
                        Text(
                            text = title,
                            style = Chili.typography.H12_Primary
                        )
                        if (hideChevron != true) {
                            ChiliChevron()
                        }
                    }
                    balance?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            balanceIcon?.let {
                                Image(
                                    painter = it,
                                    contentDescription = "",
                                    modifier = Modifier.clip(Chili.shapes.RoundedCornerShape)
                                )
                            }
                            Text(text = it.parseHtml(), style = Chili.typography.H16_Primary_500)
                        }
                    }
                }
            }
            actionText?.let {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    ChiliComponentButton(text = it) {
                        onActionTextClick?.invoke()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BalanceCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            BalanceCard(
                title = "Title", balance = "1100 c", actionText = "Action", isLoading = false
            )
        }
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            BalanceCard(
                title = "Title", balance = "1100 c", actionText = "Action", isLoading = true
            )
        }
    }

}

