package kg.devcats.compose.jetpack_chili.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.red_4
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun PromoCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    titleText: String,
    promoStatusState: PromoStatusState = PromoStatusState.NoStatus,
    isLoading: Boolean = false,
) {
    val (statusText, statusGradientColors) = when (promoStatusState) {
        is PromoStatusState.New -> promoStatusState.statusText to listOf(
            Color(0xFFFF6491),
            Color(0xFFF91155)
        )

        is PromoStatusState.Active -> promoStatusState.statusText to listOf(
            Color(0xFF79B815),
            Color(0xFFA3D555)
        )

        is PromoStatusState.Wait -> promoStatusState.statusText to listOf(
            Color(0xFFFEAA35),
            Color(0xFFFF9400)
        )

        is PromoStatusState.Expired -> {
            promoStatusState.statusText to listOf(
                Color(0xFFFF3B30),
                Color(0xFFBB2E26)
            )
        }
        else -> null to null
    }
    val expiredBorderColor = if (promoStatusState is PromoStatusState.Expired) red_4 else Color.Transparent
    Box(
        modifier = modifier
            .background(
                color = Chili.color.cardViewBackground,
                shape = Chili.shapes.RoundedCornerShape
            )
            .border(width = 1.5.dp, color = expiredBorderColor, shape = Chili.shapes.RoundedCornerShape)
            .defaultMinSize(minHeight = 88.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, end = 4.dp, top = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                if (isLoading) {
                    Shimmer(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        height = 32.dp,
                        width = 32.dp
                    )

                    Shimmer(
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 6.dp, start = 8.dp, end = 8.dp),
                        height = 26.dp,
                        width = 75.dp
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(32.dp),
                        painter = painterResource(id = icon),
                        contentDescription = null
                    )

                    if (statusText != null && statusGradientColors != null) {
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.linearGradient(colors = statusGradientColors),
                                    shape = Chili.shapes.RoundedCornerShape
                                )
                                .padding(top = 4.dp, bottom = 6.dp, start = 8.dp, end = 8.dp)
                        ) {
                            Text(
                                text = statusText,
                                style = Chili.typography.H12_Primary_500.copy(color = white_1),
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 38.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (isLoading) {
                    Shimmer(height = 8.dp, width = 120.dp)
                } else {
                    Text(
                        text = titleText,
                        style = Chili.typography.H14_Primary,
                    )
                }
            }
        }
    }
}

sealed class PromoStatusState {
    data object NoStatus : PromoStatusState()
    data class New(val statusText: String) : PromoStatusState()
    data class Active(val statusText: String) : PromoStatusState()
    data class Wait(val statusText: String) : PromoStatusState()
    data class Expired(val statusText: String) : PromoStatusState()
}