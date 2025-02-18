package kg.devcats.compose.jetpack_chili.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.shimmer.Shimmer
import kg.devcats.compose.jetpack_chili.components.shimmer.shimmerBrush
import kg.devcats.compose.jetpack_chili.parseHtml
import kg.devcats.compose.jetpack_chili.setIsPressedEffect
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.gray_1
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    accountCardState: AccountCardState = AccountCardState.Loading,
    actionButtonClick: () -> Unit = {},
    onContainerClick: () -> Unit = {}
) {
    AccountCardLayout(
        modifier = modifier,
        onContainerClick = onContainerClick
    ) { isPressed ->
        AccountCardContent(
            accountCardState = accountCardState,
            isPressed = isPressed,
            actionButtonClick = actionButtonClick
        )
    }
}

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    title: String,
    titleTextStyle: TextStyle = Chili.typography.H14_Primary,
    subtitle: String? = null,
    subtitleTextStyle: TextStyle = Chili.typography.H14_Primary_700,
    iconId: Int? = null,
    titleAddition: String? = null,
    titleAdditionTextStyle: TextStyle = Chili.typography.H14.copy(color = gray_1),
    isChevronVisible: Boolean = false,
    isToggleIconVisible: Boolean = false,
    isToggleHiddenState: Boolean = false,
    actionButtonIcon: Int? = null,
    actionButtonText: String? = null,
    actionButtonChevronVisible: Boolean = false,
    actionButtonClick: () -> Unit = {},
    onContainerClick: () -> Unit = {},
) {
    var toggleHiddenState by remember { mutableStateOf(isToggleHiddenState) }
    AccountCardLayout(
        modifier = modifier,
        onContainerClick = onContainerClick
    ) { isPressed ->
        if (subtitle != null) {
            AccountContent(
                title = title,
                titleTextStyle = titleTextStyle,
                subtitle = if (toggleHiddenState) "••••••••" else subtitle,
                subtitleTextStyle = subtitleTextStyle,
                iconId = iconId,
                titleAddition = titleAddition,
                titleAdditionTextStyle = titleAdditionTextStyle,
                isChevronVisible = isChevronVisible,
                toggleIconId = if (isToggleIconVisible && toggleHiddenState) R.drawable.chili_ic_toggle_off else if (isToggleIconVisible) R.drawable.chili_ic_toggle_on else null,
                isPressed = isPressed,
                onToggleClick = { toggleHiddenState = !toggleHiddenState }
            )
        } else {
            SingleLineAccountContent(
                modifier = Modifier,
                title = title,
                titleTextStyle = titleTextStyle,
                iconId = iconId,
                isPressed = isPressed
            )
        }
        ActionButtonContent(
            modifier = Modifier,
            icon = actionButtonIcon,
            text = actionButtonText,
            isChevronVisible = actionButtonChevronVisible,
            onClick = actionButtonClick,
        )
    }
}

@Composable
private fun AccountCardLayout(
    modifier: Modifier,
    onContainerClick: () -> Unit = {},
    content: @Composable RowScope.(Boolean) -> Unit
) {
    val isPressed = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .setIsPressedEffect(isPressed, onContainerClick, true),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content(isPressed.value)
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Chili.color.dividerColor)
        )
    }
}

@Composable
private fun RowScope.AccountCardShimmer() {
    val starShape = GenericShape { size, _ ->
        val path = Path().apply {
            fillType = PathFillType.EvenOdd

            val midX = size.width / 2
            val midY = size.height / 2
            val radius = size.minDimension / 2
            val angle = PI / 5
            val rotationOffset = -PI / 2

            for (i in 0 until 10) {
                val r = if (i % 2 == 0) radius else radius / 2
                val theta = angle * i - rotationOffset
                val x = midX + r * cos(theta).toFloat()
                val y = midY - r * sin(theta).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        addPath(path)
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp)
                    .clip(starShape)
                    .background(shimmerBrush())
            )

            Shimmer(
                height = 8.dp,
                width = 68.dp,
                roundRadius = 10.dp
            )
        }

        Box(
            modifier = Modifier.height(20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Shimmer(
                height = 8.dp,
                width = 68.dp,
                roundRadius = 10.dp
            )
        }
    }

    ActionButtonContent()
}

@Composable
private fun RowScope.AccountCardContent(
    accountCardState: AccountCardState,
    isPressed: Boolean = false,
    actionButtonClick: () -> Unit = {}
) {
    when (accountCardState) {
        is AccountCardState.FavoritePaymentAmountAvailable -> {
            var isToggleHiddenState by remember { mutableStateOf(accountCardState.isToggleHiddenState) }
            AccountContent(
                title = accountCardState.title,
                subtitle = if (isToggleHiddenState) "••••••••" else accountCardState.subtitle,
                titleAddition = accountCardState.titleAddition,
                iconId = R.drawable.chili_ic_star,
                isChevronVisible = true,
                toggleIconId = if (isToggleHiddenState) R.drawable.chili_ic_toggle_off else R.drawable.chili_ic_toggle_on,
                isPressed = isPressed,
                onToggleClick = { isToggleHiddenState = !isToggleHiddenState }
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.NonIdentified -> {
            SingleLineAccountContent(
                modifier = Modifier,
                title = accountCardState.title,
                titleTextStyle = Chili.typography.H14_Primary_700.copy(color = Chili.color.accentPrimaryColor),
                iconId = R.drawable.non_identified,
                isPressed = isPressed
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.IdentificationInProcess -> {
            SingleLineAccountContent(
                modifier = Modifier,
                title = accountCardState.title,
                titleTextStyle = Chili.typography.H14_Primary_700,
                iconId = R.drawable.ic_dentification_in_process,
                isPressed = isPressed
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.BankSync -> {
            SingleLineAccountContent(
                modifier = Modifier,
                title = accountCardState.title,
                titleTextStyle = Chili.typography.H14_Primary_700,
                iconId = R.drawable.ic_dentification_in_process,
                isPressed = isPressed
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.FavoritePaymentAmountUnavailable -> {
            AccountContent(
                title = accountCardState.title,
                subtitle = accountCardState.subtitle,
                subtitleTextStyle = Chili.typography.H14_Primary_500.copy(color = Chili.color.warningTextColor),
                titleAddition = accountCardState.maskedCardNumber,
                iconId = R.drawable.chili_ic_star,
                isPressed = isPressed,
                isChevronVisible = true,
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.FavoritePaymentUnavailable -> {
            AccountContent(
                title = accountCardState.title,
                subtitle = accountCardState.subtitle,
                iconId = R.drawable.chili_ic_empty_star,
                isChevronVisible = true,
                isPressed = isPressed,
                subtitleTextStyle = Chili.typography.H14_Primary_500.copy(color = Chili.color.warningTextColor)
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.NoDefaultPaymentAmount -> {
            SingleLineAccountContent(
                modifier = Modifier,
                title = accountCardState.title,
                iconId = R.drawable.chili_ic_empty_star,
                isPressed = isPressed
            )
            ActionButtonContent(
                modifier = Modifier,
                onClick = actionButtonClick,
                icon = R.drawable.chili_ic_obank,
                isChevronVisible = true
            )
        }

        is AccountCardState.Loading -> {
            AccountCardShimmer()
        }
    }
}

@Composable
private fun AccountContent(
    modifier: Modifier = Modifier,
    title: String,
    titleTextStyle: TextStyle = Chili.typography.H14_Primary,
    subtitle: String,
    subtitleTextStyle: TextStyle = Chili.typography.H14_Primary_700,
    @DrawableRes iconId: Int? = null,
    titleAddition: String? = null,
    titleAdditionTextStyle: TextStyle = Chili.typography.H14.copy(color = gray_1),
    isChevronVisible: Boolean = false,
    @DrawableRes toggleIconId: Int? = null,
    isPressed: Boolean = false,
    onToggleClick: () -> Unit = {}
) {
    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 200),
        label = "alphaAnimation"
    )
    Column(
        modifier = modifier
            .padding(end = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            iconId?.let {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(20.dp),
                    painter = painterResource(id = it),
                    contentDescription = null,
                )
            }
            Text(
                modifier = Modifier.padding(vertical = 1.5.dp).alpha(alpha),
                text = title,
                style = titleTextStyle
            )
            titleAddition?.let {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = it,
                    style = titleAdditionTextStyle,
                )
            }
            if (isChevronVisible) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.chili_ic_right_arrow_rounded),
                    contentDescription = null,
                    tint = Chili.color.accountChevronColor
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            toggleIconId?.let {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(20.dp)
                        .clickable {
                            onToggleClick.invoke()
                        },
                    tint = Chili.color.toggleIconColor,
                    painter = painterResource(id = it),
                    contentDescription = null,
                )
            }
            Text(
                modifier = Modifier.padding(vertical = 1.5.dp).alpha(alpha),
                text = subtitle.parseHtml(),
                style = subtitleTextStyle,
            )
        }
    }
}

@Composable
private fun SingleLineAccountContent(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconId: Int? = null,
    titleTextStyle: TextStyle = Chili.typography.H14_Primary_700,
    isPressed: Boolean = false
) {
    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 200),
        label = "alphaAnimation"
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        iconId?.let {
            Image(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp),
                painter = painterResource(it),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.alpha(alpha),
            text = title,
            style = titleTextStyle,
        )
    }
}

@Composable
private fun ActionButtonContent(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    text: String? = null,
    isChevronVisible: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(shape = Chili.shapes.RoundedCornerShape)
            .background(
                color = Chili.color.cardViewBackground,
            )
            .width(120.dp)
            .height(40.dp)
            .clickable {
                onClick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        icon?.let {
            Image(
                modifier = Modifier.padding(start = 10.dp),
                painter = painterResource(it),
                contentDescription = null,
            )
        }

        text?.let {
            Text(
                text = it,
                style = Chili.typography.H14_Primary_500,
            )
        }
        if (isChevronVisible) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.chili_ic_right_arrow_rounded),
                contentDescription = null,
                tint = Chili.color.accountChevronColor
            )
        }
    }
}

sealed class AccountCardState {
    data class FavoritePaymentAmountAvailable(
        val title: String,
        val subtitle: String,
        val titleAddition: String,
        val isToggleHiddenState: Boolean = false
    ) : AccountCardState()

    data class FavoritePaymentAmountUnavailable(
        val title: String,
        val subtitle: String,
        val maskedCardNumber: String,
    ) : AccountCardState()

    data class FavoritePaymentUnavailable(val title: String, val subtitle: String) : AccountCardState()

    data class BankSync(val title: String) : AccountCardState()
    data class NonIdentified(val title: String) : AccountCardState()
    data class IdentificationInProcess(val title: String) : AccountCardState()
    data class NoDefaultPaymentAmount(val title: String) : AccountCardState()
    data object Loading : AccountCardState()
}