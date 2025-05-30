package kg.devcats.compose.jetpack_chili.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.black_3_alpha_45
import kg.devcats.compose.jetpack_chili.theme.gray_10_alpha_70
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    isBankingCard: Boolean = false,
    date: String,
    userName: String,
    maskedCardNumber: String,
    maskedCVV: String,
    @DrawableRes cardIcon: Int? = null,
    @DrawableRes cardBackground: Int? = null,
    imageLink: String? = null,
    cardNumberState: BankCardFieldState = BankCardFieldState.Loading,
    cvvState: BankCardFieldState = BankCardFieldState.Loading,
    onCardNumberToggleClick: (BankCardFieldState) -> Unit = {},
    onCVVToggleClick: (BankCardFieldState) -> Unit = {},
    isLoading: Boolean = false
) {
    val cardNumber: String = cardNumberState.getTextData(maskedCardNumber)
    val cvv: String = cvvState.getTextData(maskedCVV)

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (isBankingCard) {
            imageLink?.let {
                if (isLoading){
                    Shimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                    )
                } else {
                    AsyncImage(
                        model = it,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth(),
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.bank_card_shimmer),
                        error = painterResource(R.drawable.bank_card_shimmer)
                    )
                }
            }
        } else {
            cardBackground?.let {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = it),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Bottom
        ) {
            TextWithMask(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = cardNumber,
                toggleIcon = cardNumberState.getIconByState(),
                shimmerWidth = 200.dp,
                isLoading = cardNumberState is BankCardFieldState.Loading,
                isBackgroundLoading = isBankingCard && isLoading
            ) {
                onCardNumberToggleClick.invoke(cardNumberState)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = date,
                    style = Chili.typography.H16_Primary_500.copy(color = white_1)
                )

                TextWithMask(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    text = cvv,
                    toggleIcon = cvvState.getIconByState(),
                    shimmerWidth = 28.dp,
                    isLoading = cvvState is BankCardFieldState.Loading,
                    isBackgroundLoading = isBankingCard && isLoading
                ) {
                    onCVVToggleClick.invoke(cvvState)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 10.dp, bottom = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    text = userName,
                    style = Chili.typography.H14.copy(color = white_1)
                )
                cardIcon?.let {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

sealed class BankCardFieldState {
    data object IconShow : BankCardFieldState()
    data class IconCopy(val text: String = "") : BankCardFieldState()
    data object IconNone : BankCardFieldState()
    data object Loading : BankCardFieldState()

    fun getIconByState() =
        when (this) {
            is IconShow -> R.drawable.chilli_ic_visible
            is IconCopy -> R.drawable.chilli_ic_copy
            else -> null
        }

    fun getTextData(maskedText: String): String =
        when (this) {
            is IconShow -> maskedText
            is IconNone -> maskedText
            is IconCopy -> this.text
            else -> ""
        }
}

@Composable
private fun TextWithMask(
    modifier: Modifier = Modifier,
    text: String,
    toggleIcon: Int?,
    isLoading: Boolean = false,
    isBackgroundLoading: Boolean = false,
    shimmerWidth: Dp,
    onToggleClick: () -> Unit
) {
    val backgroundColor = if (isBackgroundLoading) gray_10_alpha_70 else black_3_alpha_45
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            Shimmer(modifier = Modifier
                .padding(end = 24.dp)
                .width(shimmerWidth)
                .height(12.dp))
            Spacer(modifier = Modifier.size(24.dp))
        } else {
            Text(
                modifier = Modifier.padding(end = 24.dp),
                text = text,
                style = Chili.typography.H16_Primary_500.copy(color = white_1)
            )

            if (toggleIcon == null) {
                Spacer(modifier = Modifier.size(24.dp))
            } else {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onToggleClick.invoke() },
                    painter = painterResource(id = toggleIcon),
                    contentDescription = null
                )
            }
        }
    }
}