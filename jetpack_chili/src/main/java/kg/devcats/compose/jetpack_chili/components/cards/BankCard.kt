package kg.devcats.compose.jetpack_chili.components.cards

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.black_3_alpha_45
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    date: String,
    userName: String,
    @DrawableRes cardIcon: Int,
    @DrawableRes cardBackground: Int,
    cardNumberState: BankCardFieldState = BankCardFieldState.Loading,
    cvvState: BankCardFieldState = BankCardFieldState.Loading,
    onCardNumberToggleClick: (BankCardFieldState) -> Unit = {},
    onCVVToggleClick: (BankCardFieldState) -> Unit = {},
) {
    val cardNumberToggleIcon = getIconByState(cardNumberState)
    val cvvToggleIcon = getIconByState(cvvState)

    val cardNumber: String = getTextData(cardNumberState)
    val cvv: String = getTextData(cvvState)

    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        println("Recompose... BankCardView")
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = cardBackground),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Bottom
        ) {
            TextWithMask(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = cardPanHideDelegate(
                    cardNumber,
                    cardNumberState is BankCardFieldState.IconShow
                ).toString(),
                toggleIcon = cardNumberToggleIcon,
                shimmerWidth = 200.dp,
                isLoading = cardNumberState is BankCardFieldState.Loading
            ) {
                if (cardNumberState is BankCardFieldState.IconCopy) {
                    copyText(context = context, text = cardNumber)
                }
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
                    text = cardCvvHideDelegate(
                        cvv,
                        cvvState is BankCardFieldState.IconShow
                    ).toString(),
                    toggleIcon = cvvToggleIcon,
                    shimmerWidth = 28.dp,
                    isLoading = cvvState is BankCardFieldState.Loading
                ) {
                    if (cvvState is BankCardFieldState.IconCopy) {
                        copyText(context = context, text = cvv)
                    }
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
                    text = userName,
                    style = Chili.typography.H14.copy(color = white_1)
                )
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = cardIcon),
                    contentDescription = null
                )
            }
        }
    }
}

fun getTextData(bankCardFieldState: BankCardFieldState): String =
    when (bankCardFieldState) {
        is BankCardFieldState.IconShow -> bankCardFieldState.text
        is BankCardFieldState.IconCopy -> bankCardFieldState.text
        else -> ""
    }

private fun getIconByState(bankCardFieldState: BankCardFieldState) =
    when (bankCardFieldState) {
        is BankCardFieldState.IconShow -> R.drawable.chilli_ic_visible1
        is BankCardFieldState.IconCopy -> R.drawable.chilli_ic_copy
        else -> null
    }

sealed class BankCardFieldState {
    data class IconShow(val text: String = "") : BankCardFieldState()
    data class IconCopy(val text: String = "") : BankCardFieldState()
    data object IconNone : BankCardFieldState()
    data object Loading : BankCardFieldState()
}

private var cardPanHideDelegate: (CharSequence, Boolean) -> CharSequence =
    { pan: CharSequence, isHidden: Boolean ->
        if (isHidden) {
            try {
                pan.replaceRange(6..11, " • • •  • •")
            } catch (_: Exception) {
                pan
            }
        } else pan
    }

private var cardCvvHideDelegate = { pan: CharSequence, isHidden: Boolean ->
    if (isHidden) "• • •"
    else pan
}

private fun copyText(context: Context, text: String) {
    val clipboard: ClipboardManager? =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText(text, text)
    clipboard?.setPrimaryClip(clip)
    Toast.makeText(context, R.string.chili_copied_to_clipboard, Toast.LENGTH_SHORT).show()
}

@Composable
private fun TextWithMask(
    modifier: Modifier = Modifier,
    text: String,
    toggleIcon: Int?,
    isLoading: Boolean = false,
    shimmerWidth: Dp,
    onToggleClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = black_3_alpha_45, shape = RoundedCornerShape(8.dp))
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