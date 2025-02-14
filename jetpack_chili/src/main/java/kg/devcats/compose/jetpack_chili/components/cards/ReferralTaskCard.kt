package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
    subtitle: String?,
    iconUrl: String,
    isFriend: Boolean,
    status: ReferralTaskStatus,
) {
    if (status is ReferralTaskStatus.Loading) {
        LoadingContent(
            modifier = modifier,
            isFriend = isFriend
        )
    } else {
        TaskContent(
            modifier = modifier,
            title = title,
            subtitle = subtitle,
            iconUrl = iconUrl,
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
    subtitle: String?,
    iconUrl: String?,
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
            subtitle?.let {
                val style =
                    if (status is ReferralTaskStatus.Unavailable) Chili.typography.H12.copy(color = gray_1)
                    else Chili.typography.H12_Secondary
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = subtitle,
                    style = style
                )
            }
            DetailInformationContent(
                modifier = Modifier.padding(top = 12.dp),
                isFriend = isFriend,
                status = status
            )
        }
        IconContent(isFriend, status is ReferralTaskStatus.Completed, iconUrl)
    }
}

@Composable
fun IconContent(isFriend: Boolean, isCompleted: Boolean, iconUrl: String?) {
    if (isFriend) {
        val resultIcon =
            if (isCompleted) R.drawable.chili_ic_checked_checkbox_round else R.drawable.chili_ic_checkbox_round
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(resultIcon),
            contentDescription = null
        )
    } else {
        AsyncImage(
            model = iconUrl,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(48.dp),
            contentDescription = null
        )
    }
}

@Composable
private fun DetailInformationContent(
    modifier: Modifier = Modifier,
    isFriend: Boolean,
    status: ReferralTaskStatus,
) {
    if (isFriend) {
        InvitingDetailInformation(modifier, status)
    } else {
        InvitedDetailInformation(modifier, status)
    }
}

@Composable
fun InvitedDetailInformation(modifier: Modifier, status: ReferralTaskStatus) {
    when (status) {
        is ReferralTaskStatus.Unavailable -> {
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

        is ReferralTaskStatus.Available -> {
            ChiliPrimaryButton(
                modifier = modifier,
                text = status.actionButtonText,
                onClick = status.onActionClick
            )
        }

        is ReferralTaskStatus.Completed -> {
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
                    style = Chili.typography.H14.copy(color = Chili.color.alertSuccessContent)
                )
            }
        }

        else -> {}
    }
}

@Composable
fun InvitingDetailInformation(modifier: Modifier, status: ReferralTaskStatus) {
    when (status) {
        is ReferralTaskStatus.Completed -> {
            Text(
                modifier = modifier,
                text = status.detailInfoText,
                style = Chili.typography.H14.copy(color = Chili.color.alertSuccessContent)
            )
        }

        is ReferralTaskStatus.Available -> {
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

        else -> {}
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