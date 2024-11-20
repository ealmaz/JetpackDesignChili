package kg.devcats.compose.jetpack_chili.components.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun BonusTagView(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .paint(
                painter = painterResource(R.drawable.chilli_bonus_tag_bg),
                contentScale = ContentScale.FillBounds
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(all = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 2.dp),
                text = text,
                style = Chili.typography.H12_Primary_500.copy(color = Color.White),
            )
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.chilli_ic_bonus),
                contentDescription = null
            )
        }
    }
}