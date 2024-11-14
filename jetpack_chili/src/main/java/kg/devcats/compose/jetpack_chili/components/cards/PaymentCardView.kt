package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PaymentCardView(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter? = null,
    onClick: (() -> Unit)? = null,
    isEnabled: Boolean = true
) {
    ChiliCardView(
        modifier = modifier.padding(12.dp),
        title = title,
        icon = icon,
        onClick = onClick,
        isEnabled = isEnabled,
        horizontalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        titleStyle = Chili.typography.H12_Primary
    )
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    Column {
        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            Column {
                PaymentCardView(
                    title = "Заголовок",
                    icon = painterResource(id = R.drawable.chili_ic_documents_green)
                )
            }
        }

        ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
            Column {
                PaymentCardView(
                    title = "Заголовок",
                    icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    isEnabled = false
                )
            }
        }
    }
}

