package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.cards.AccountCard
import kg.devcats.compose.jetpack_chili.components.cards.AccountCardState
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PreviewAccountCardExamples(
    navigateUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.screenBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "AccountCard",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 64.dp)
        ) {
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.NonIdentified(
                    title = "Пройдите \nидентификацию",
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                title = "Пройдите \nидентификацию",
                titleTextStyle = Chili.typography.H14_Primary_700.copy(color = Chili.color.accentPrimaryColor),
                actionButtonText = "Войти",
                actionButtonIcon = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_card
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.IdentificationInProcess(
                    title = "Ваша заявка \nв обработке",
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.BankSync(
                    title = "Продолжить \nперенос данных",
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.FavoritePaymentAmountAvailable(
                    title = "Карта Visa",
                    subtitle = "3 350,00 <u>c</u>",
                    titleAddition = "•••• 1234"
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.FavoritePaymentAmountAvailable(
                    title = "Карта Visa",
                    subtitle = "3 350,00 <u>c</u>",
                    titleAddition = "•••• 1234",
                    isToggleHiddenState = true
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.FavoritePaymentAmountUnavailable(
                    title = "Карта Visa",
                    subtitle = "Временно недоступен",
                    maskedCardNumber = "•••• 1234"
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.FavoritePaymentUnavailable(
                    title = "Избранный счет",
                    subtitle = "Временно недоступен "
                )
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.Loading
            )
            AccountCard(
                modifier = Modifier
                    .fillMaxSize(),
                accountCardState = AccountCardState.NoDefaultPaymentAmount(
                    title = "Выберите счёт \nпо умолчанию ",
                )
            )
        }
    }
}