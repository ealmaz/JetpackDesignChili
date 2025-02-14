package kg.devcats.compose.jetpack_chili.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.blue_1
import kg.devcats.compose.jetpack_chili.theme.gray_1

@Composable
fun ReferralTaskCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    @DrawableRes icon: Int?,
    isFriend: Boolean,
    status: ReferralTaskStatus,
) {
    if (status is ReferralTaskStatus.Loading) {
        LoadingContent(isFriend = isFriend)
    } else {
        TaskContent(
            modifier = modifier,
            title = title,
            subtitle = subtitle,
            icon = icon,
            isFriend = isFriend,
            status = status
        )
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier, isFriend: Boolean) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Shimmer(
                modifier = Modifier
                    .padding(top = 9.dp),
                height = 6.dp,
                width = 156.dp
            )
            Shimmer(
                modifier = Modifier
                    .padding(top = 8.dp),
                height = 6.dp,
                width = 72.dp
            )
            Shimmer(
                modifier = Modifier
                    .padding(top = 21.dp),
                height = 20.dp,
                width = 100.dp
            )
        }
        val size = if (isFriend) 32.dp else 48.dp
        Shimmer(
            height = size,
            width = size
        )
    }
}

@Composable
private fun TaskContent(
    modifier: Modifier,
    title: String,
    subtitle: String,
    @DrawableRes icon: Int?,
    isFriend: Boolean,
    status: ReferralTaskStatus,
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(
                modifier = Modifier,
                text = title,
                style = Chili.typography.H16_Primary_500
            )
            val style =
                if (status is ReferralTaskStatus.Unavailable) Chili.typography.H12.copy(color = gray_1)
                else Chili.typography.H12_Secondary
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = subtitle,
                style = style
            )

            DetailInformationContent(
                modifier = Modifier.padding(top = 12.dp),
                isFriend = isFriend,
                status = status
            )
        }
        val size = if (isFriend) 32.dp else 48.dp
        val resultIcon = getResultIcon(icon, isFriend, status is ReferralTaskStatus.Completed)
        resultIcon?.let {
            Image(
                modifier = Modifier.size(size),
                painter = painterResource(it),
                contentDescription = null
            )
        }
    }
}

private fun getResultIcon(icon: Int?, isFriend: Boolean, isCompleted: Boolean): Int? {
    return when {
        isFriend && isCompleted -> R.drawable.chili_ic_checked_checkbox_round
        isFriend && !isCompleted -> R.drawable.chili_ic_checkbox_round
        else -> icon
    }
}

@Composable
private fun ColumnScope.DetailInformationContent(
    modifier: Modifier = Modifier,
    isFriend: Boolean,
    status: ReferralTaskStatus,
) {
    when {
        isFriend && status is ReferralTaskStatus.Completed -> {
            Text(
                modifier = modifier,
                text = status.detailInfoText,
                style = Chili.typography.H14.copy(color = Chili.color.alertSuccessContent)
            )
        }

        isFriend && status is ReferralTaskStatus.Available -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChiliPrimaryButton(
                    text = status.actionButtonText,
                    onClick = status.onActionClick
                )
                Text(
                    modifier = Modifier
                        .padding(start = 27.dp)
                        .clickable { status.detailInfoTextClick() },
                    text = status.detailInfoText,
                    style = Chili.typography.H14_Link_500.copy(color = blue_1)
                )
            }
        }

        status is ReferralTaskStatus.Unavailable -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.chilli_ic_lock),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = status.detailInfoText,
                    style = Chili.typography.H14.copy(color = gray_1)
                )
            }
        }

        status is ReferralTaskStatus.Available -> {
            ChiliPrimaryButton(
                modifier = modifier,
                text = status.actionButtonText,
                onClick = status.onActionClick
            )
        }

        status is ReferralTaskStatus.Completed -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.chili_ic_checked_checkbox_round),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = status.detailInfoText,
                    style = Chili.typography.H14.copy(color = Chili.color.referralTaskSuccessText)
                )
            }
        }
    }
}

sealed class ReferralTaskStatus {
    data object Loading : ReferralTaskStatus()
    data class Available(
        val actionButtonText: String = "",
        val detailInfoText: String = "",
        val onActionClick: () -> Unit = {},
        val detailInfoTextClick: () -> Unit = {}
    ) : ReferralTaskStatus()

    data class Completed(val detailInfoText: String) : ReferralTaskStatus()
    data class Unavailable(val detailInfoText: String) : ReferralTaskStatus()
}