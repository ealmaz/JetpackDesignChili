package kg.devcats.compose.samples.ui.chili_sample

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.cards.AccentCard
import kg.devcats.compose.jetpack_chili.components.cards.AlertBlockCard
import kg.devcats.compose.jetpack_chili.components.cards.AlertState
import kg.devcats.compose.jetpack_chili.components.cards.AnimatedBorderCard
import kg.devcats.compose.jetpack_chili.components.cards.BalanceCard
import kg.devcats.compose.jetpack_chili.components.cards.BankCard
import kg.devcats.compose.jetpack_chili.components.cards.BankCardFieldState
import kg.devcats.compose.jetpack_chili.components.cards.BonusCard
import kg.devcats.compose.jetpack_chili.components.cards.BonusCardSize
import kg.devcats.compose.jetpack_chili.components.cards.CatalogCard
import kg.devcats.compose.jetpack_chili.components.cards.ChiliCard
import kg.devcats.compose.jetpack_chili.components.cards.IconSize
import kg.devcats.compose.jetpack_chili.components.cards.InfoCard
import kg.devcats.compose.jetpack_chili.components.cards.InfoCardButtonConfig
import kg.devcats.compose.jetpack_chili.components.cards.InfoCardButtonType
import kg.devcats.compose.jetpack_chili.components.cards.PaymentCard
import kg.devcats.compose.jetpack_chili.components.cards.PieChartCard
import kg.devcats.compose.jetpack_chili.components.cards.ProductCard
import kg.devcats.compose.jetpack_chili.components.cards.StoriesCard
import kg.devcats.compose.jetpack_chili.components.cards.StoriesStatus
import kg.devcats.compose.jetpack_chili.components.cards.SubtitledSimpleCard
import kg.devcats.compose.jetpack_chili.components.cells.MultiIconedTitleCell
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PreviewCards(
    navigateUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            title = "Card",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
        ) {

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "ChiliCard",
                style = Chili.typography.H16_Primary
            )
            Column {
                ShadowRoundedBox {
                    ChiliCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        title = "Заголовок",
                        subtitle = "100 000 c",
                        verticalAlignment = Alignment.CenterVertically,
                        icon = painterResource(id = R.drawable.chili_ic_bell_budget),
                        endFrame = {
                            Image(
                                painter = painterResource(id = R.drawable.chili_ic_documents_green),
                                contentDescription = ""
                            )
                        }
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    ChiliCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        title = "Заголовок",
                        subtitle = "100 000 c",
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        icon = painterResource(id = R.drawable.chili_ic_bell_budget)
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "BalanceCard",
                style = Chili.typography.H16_Primary
            )
            Row {
                ShadowRoundedBox {
                    BalanceCard(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        balance = "100 000 c",
                        actionText = "Action",
                        isLoading = false,
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    BalanceCard(
                        modifier = Modifier
                            .clickable { },
                        title = "Заголовок",
                        balance = "100 000 c",
                        isLoading = true,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "CatalogCard",
                style = Chili.typography.H16_Primary
            )
            Row {
                ShadowRoundedBox {
                    CatalogCard(
                        modifier = Modifier
                            .clickable { }
                            .width(186.dp),
                        title = "Заголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = false,
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    CatalogCard(
                        modifier = Modifier
                            .clickable { }
                            .width(186.dp),
                        title = "Заголовок",
                        isLoading = true,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "PaymentCard",
                style = Chili.typography.H16_Primary
            )
            Row {
                ShadowRoundedBox {
                    PaymentCard(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    PaymentCard(
                        modifier = Modifier
                            .clickable { },
                        title = "Заголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isEnabled = false,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AccentCard",
                style = Chili.typography.H16_Primary
            )
            Column {
                ShadowRoundedBox {
                    AccentCard(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Сканер штрихкодов и QR",
                        subtitle = "Для удобной оплаты \nбез ввода реквизитов",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    AccentCard(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Заголовок",
                        subtitle = "100 000 c",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = true
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "BonusCard",
                style = Chili.typography.H16_Primary
            )
            Column {

                Row {
                    ShadowRoundedBox {
                        BonusCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            size = BonusCardSize.Large()
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCard(
                            modifier = Modifier,
                            title = "Заголовок",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isLoading = true,
                            size = BonusCardSize.Large()
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    ShadowRoundedBox {
                        BonusCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            size = BonusCardSize.Middle()
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCard(
                            modifier = Modifier,
                            title = "Заголовок",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isLoading = true,
                            size = BonusCardSize.Middle()
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    ShadowRoundedBox {
                        BonusCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            size = BonusCardSize.Small()
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCard(
                            modifier = Modifier,
                            title = "Заголовок",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isLoading = true,
                            size = BonusCardSize.Small()
                        )
                    }
                }
            }


            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AnimatedCard",
                style = Chili.typography.H16_Primary
            )
            Column {
                Row {
                    ShadowRoundedBox {
                        AnimatedBorderCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            commissionInfo = "Commission",
                            commissionValue = "13%",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isAnimating = true
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        AnimatedBorderCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            commissionInfo = "Commission",
                            commissionValue = "13%",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isAnimating = false
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    ShadowRoundedBox {
                        AnimatedBorderCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            commissionInfo = "Commission",
                            commissionValue = "13%",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isAnimating = true
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        AnimatedBorderCard(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            commissionInfo = "Commission",
                            commissionValue = "13%",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isAnimating = false,
                            isLoading = true
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AccentCard",
                style = Chili.typography.H16_Primary
            )
            Column {
                ShadowRoundedBox {
                    PieChartCard(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Детализация на 06.06.2020",
                        data = listOf(40f, 30f, 20f, 10f),
                        colors = listOf(
                            Color(0xFFE91E63),
                            Color(0xFF4CAF50),
                            Color(0xFFFFEB3B),
                            Color(0xFFF57F17)
                        ),
                        pieChartTitle = "Все расходы",
                        pieChartSubtitle = "140,00 с",
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    PieChartCard(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Детализация на 06.06.2020",
                        data = listOf(100f, 1000f),
                        colors = listOf(
                            Color(0xFFE91E63),
                            Color(0xFFececec)
                        ),
                        pieChartTitle = "Все расходы",
                        pieChartSubtitle = "140,00 с",
                        isSinglePieData = true
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "MultiiconedCell",
                style = Chili.typography.H16_Primary
            )

            val context = LocalContext.current
            ShadowRoundedBox {
                MultiIconedTitleCell(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    icons = listOf(
                        kg.devcats.compose.samples.R.drawable.elcart,
                        kg.devcats.compose.samples.R.drawable.elcart,
                        kg.devcats.compose.samples.R.drawable.elcart,
                        kg.devcats.compose.samples.R.drawable.elcart,
                        kg.devcats.compose.samples.R.drawable.elcart,
                        kg.devcats.compose.samples.R.drawable.elcart,
                    ),
                    title = "MultiiconedCell title",
                    additionalInfo = "Весь список",
                    isLoading = true,
                    onAdditionalInfoClick = {
                        Toast.makeText(context, "Additional info clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AlertBlockCard",
                style = Chili.typography.H16_Primary
            )

            var isOpened by remember { mutableStateOf(true) }

            if (isOpened) {
                AlertBlockCard(
                    modifier = Modifier.padding(bottom = 14.dp),
                    title = "Title",
                    alertState = AlertState.Neutral,
                    subtitle = "Subtitle",
                    buttonText = "Кнопка",
                    isClosable = true,
                    onCloseClick = { isOpened = false }
                )
            }

            AlertBlockCard(
                modifier = Modifier.padding(bottom = 14.dp),
                subtitle = "Деньги поступят на счёт по умолчанию",
                alertState = AlertState.Neutral
            )

            AlertBlockCard(
                title = "Title",
                alertState = AlertState.Error,
                subtitle = "Subtitle",
                buttonText = "Кнопка",
                onButtonClick = {
                    context.showToast("AlertBlockCard")
                }
            )

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "SubtitledSimpleCard",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox {
                SubtitledSimpleCard(
                    title = "Favorite",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png"
                )
            }

            ShadowRoundedBox(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                SubtitledSimpleCard(
                    title = "Favorite",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isLoading = true
                )
            }

            ShadowRoundedBox(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                SubtitledSimpleCard(
                    title = "Favorite",
                    emoji = "\uD83D\uDD25"
                )
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "ProductCard",
                style = Chili.typography.H16_Primary
            )

            LazyVerticalGrid (
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    ShadowRoundedBox {
                        ProductCard(
                            modifier = Modifier
                                .height(293.dp)
                                .width(168.dp),
                            imageLink = "",
                            price = "2 090,00 c",
                            installmentPrice = "8 166,6 с x 12 мес",
                            description = "Электрочайник BEREKE BR-810 серый, гарантия 2 года, можно с рассрочками",
                            discountPrice = "-20%",
                            discountBackgroundColor = "#F91155",
                            isLoading = false
                        )
                    }
                }

                item {
                    ShadowRoundedBox {
                        ProductCard(
                            modifier = Modifier
                                .height(293.dp)
                                .width(168.dp),
                            price = "2 090,00 c",
                            installmentPrice = "8 166,6 с x 12 мес",
                            description = "Электрочайник BEREKE BR-810 серый",
                            isLoading = true
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "BankCard",
                style = Chili.typography.H16_Primary
            )

            var bankCardNumberState by remember { mutableStateOf<BankCardFieldState>(BankCardFieldState.IconShow) }
            var bankCardCVVState by remember { mutableStateOf<BankCardFieldState>(BankCardFieldState.IconShow) }

            val coroutineScope = rememberCoroutineScope()

            BankCard(
                modifier = Modifier,
                date = "01/ 26",
                userName = "Ivanov Ivanovskii",
                maskedCardNumber = "1234 5• • •  • •12 3456",
                maskedCVV = "• • •",
                cardIcon = kg.devcats.compose.samples.R.drawable.ic_visa_logo,
                cardBackground = kg.devcats.compose.samples.R.drawable.card_bg,
                cardNumberState = bankCardNumberState,
                cvvState = bankCardCVVState,
                onCardNumberToggleClick = {
                    coroutineScope.launch {
                        getBankCardState(context, bankCardNumberState, "1234 5678 9012 3456").collect {
                            bankCardNumberState = it
                        }
                    }
                },
                onCVVToggleClick = {
                    coroutineScope.launch {
                        getBankCardState(context, bankCardCVVState, "123").collect {
                            bankCardCVVState = it
                        }
                    }
                }
            )

            Spacer(Modifier.height(8.dp))

            val isLoading = remember { mutableStateOf(true) }

            BankCard(
                modifier = Modifier,
                isBankingCard = true,
                isLoading = isLoading.value,
                date = "01/ 26",
                userName = "Ivanov Ivanovskii",
                maskedCardNumber = "1234 5• • •  • •12 3456",
                maskedCVV = "• • •",
                cardIcon = null,
                cardBackground = null,
                imageLink = "https://minio.o.kg/media-service/mybank/bank-cards/light/BANK_VISA_GOLD.png",
                cardNumberState = bankCardNumberState,
                cvvState = bankCardCVVState,
                onCardNumberToggleClick = {
                    coroutineScope.launch {
                        getBankCardState(context, bankCardNumberState, "1234 5678 9012 3456").collect {
                            bankCardNumberState = it
                        }
                    }
                },
                onCVVToggleClick = {
                    coroutineScope.launch {
                        getBankCardState(context, bankCardCVVState, "123").collect {
                            bankCardCVVState = it
                        }
                    }
                }
            )

            coroutineScope.launch {
                delay(3000)
                isLoading.value = false
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "InfoCard",
                style = Chili.typography.H16_Primary
            )

            InfoCard(
                modifier = Modifier.padding(bottom = 16.dp),
                title = "Title",
                subtitle = "Subtitle",
                buttonConfig = InfoCardButtonConfig(
                    text = "Button",
                    type = InfoCardButtonType.PRIMARY,
                    onClick = {
                        context.showToast("InfoCard single button")
                    }
                )
            )

            InfoCard(
                title = "Title",
                subtitle = "Subtitle",
                horizontalButtons = Pair(
                    InfoCardButtonConfig(
                        text = "Additional",
                        type = InfoCardButtonType.ADDITIONAL,
                        onClick = {
                            context.showToast("InfoCard additional button")
                        }
                    ),
                    InfoCardButtonConfig(
                        text = "Primary",
                        type = InfoCardButtonType.PRIMARY,
                        onClick = {
                            context.showToast("InfoCard primary button")
                        }
                    )
                )
            )

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "StoriesCard",
                style = Chili.typography.H16_Primary
            )

            LazyRow {
                item {
                    var storiesStatus by remember { mutableStateOf<StoriesStatus>(StoriesStatus.UnViewed) }
                    StoriesCard(
                        imageLink = "https://minio.o.kg/media-service/BannerConfigurator/light/125ac05b-0cbb-4bde-9da0-1f090d1fac2e",
                        storiesStatus = storiesStatus,
                        iconSize = IconSize.MEDIUM
                    ) {
                        storiesStatus = StoriesStatus.Viewed
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    var storiesStatus by remember { mutableStateOf<StoriesStatus>(StoriesStatus.UnViewed) }
                    StoriesCard(
                        imageLink = "https://minio.o.kg/media-service/BannerConfigurator/light/de574e23-7478-43af-b3df-b572093b818c",
                        storiesStatus = storiesStatus,
                        iconSize = IconSize.MEDIUM
                    ) {
                        storiesStatus = StoriesStatus.Viewed
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    var storiesStatus by remember { mutableStateOf<StoriesStatus>(StoriesStatus.UnViewed) }
                    StoriesCard(
                        imageLink = "https://minio.o.kg/media-service/BannerConfigurator/light/de574e23-7478-43af-b3df-b572093b818c",
                        storiesStatus = storiesStatus,
                        iconSize = IconSize.MEDIUM
                    ) {
                        storiesStatus = StoriesStatus.Viewed
                    }
                }
            }
        }
    }
}

fun getBankCardState(context: Context, bankCardState: BankCardFieldState, text: String): Flow<BankCardFieldState> = flow {
    when (bankCardState) {
        is BankCardFieldState.IconShow -> {
            emit(BankCardFieldState.Loading)
            delay(1000)
            emit(BankCardFieldState.IconCopy(text))
        }

        is BankCardFieldState.IconCopy -> {
            copyText(context = context, text = bankCardState.text)
            emit(BankCardFieldState.IconShow)
        }

        is BankCardFieldState.IconNone -> {

        }

        is BankCardFieldState.Loading -> {

        }
    }
}

private fun copyText(context: Context, text: String) {
    val clipboard: ClipboardManager? =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText(text, text)
    clipboard?.setPrimaryClip(clip)
    Toast.makeText(context, "Реквизит скопирован", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCardViews() {
    PreviewCards({})
}