package kg.devcats.compose.samples.ui.chili_sample

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliComponentButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.cards.AccentCard
import kg.devcats.compose.jetpack_chili.components.cards.AlertBlockCard
import kg.devcats.compose.jetpack_chili.components.cards.AlertState
import kg.devcats.compose.jetpack_chili.components.cards.AnimatedBorderCard
import kg.devcats.compose.jetpack_chili.components.cards.BalanceCard
import kg.devcats.compose.jetpack_chili.components.cards.BankCard
import kg.devcats.compose.jetpack_chili.components.cards.BankCardFieldState
import kg.devcats.compose.jetpack_chili.components.cards.BonusCard
import kg.devcats.compose.jetpack_chili.components.cards.BonusCardSize
import kg.devcats.compose.jetpack_chili.components.cards.AdditionalContentInfoCard
import kg.devcats.compose.jetpack_chili.components.cards.AdditionalLabelCard
import kg.devcats.compose.jetpack_chili.components.cards.CatalogCard
import kg.devcats.compose.jetpack_chili.components.cards.ChiliCard
import kg.devcats.compose.jetpack_chili.components.cards.ExpandableCard
import kg.devcats.compose.jetpack_chili.components.cards.IconSize
import kg.devcats.compose.jetpack_chili.components.cards.InfoCard
import kg.devcats.compose.jetpack_chili.components.cards.InfoCardButtonConfig
import kg.devcats.compose.jetpack_chili.components.cards.InfoCardButtonType
import kg.devcats.compose.jetpack_chili.components.cards.PaymentCard
import kg.devcats.compose.jetpack_chili.components.cards.PieChartCard
import kg.devcats.compose.jetpack_chili.components.cards.ProductCard
import kg.devcats.compose.jetpack_chili.components.cards.ProgressCard
import kg.devcats.compose.jetpack_chili.components.cards.PromoCard
import kg.devcats.compose.jetpack_chili.components.cards.PromoStatusState
import kg.devcats.compose.jetpack_chili.components.cards.ReferralTaskCard
import kg.devcats.compose.jetpack_chili.components.cards.ReferralTaskStatus
import kg.devcats.compose.jetpack_chili.components.cards.StoriesCard
import kg.devcats.compose.jetpack_chili.components.cards.SubtitledSimpleCard
import kg.devcats.compose.jetpack_chili.components.cards.TransferCard
import kg.devcats.compose.jetpack_chili.components.cells.MultiIconedTitleCell
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.LocalValueShimmering
import kg.devcats.compose.samples.SampleToolbarMenu
import kg.devcats.compose.samples.ui.extension.showToast
import kg.devcats.compose.samples.ui.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PreviewCards(
    navController: NavHostController = rememberNavController(),
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            modifier = Modifier.statusBarsPadding(),
            title = "Card",
            isDividerVisible = true,
            isNavigationIconVisible = true,
            endFrame = { SampleToolbarMenu() },
            onNavigationIconClick = {
                navigateUp.invoke()
            })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
                .navigationBarsPadding()
        ) {
            ChiliPrimaryButton(text = "Account card examples", modifier = Modifier.fillMaxWidth().padding(top = 32.dp, bottom = 16.dp)) {
                navController.navigate(Screens.AccountCardExamplesScreen.toString())
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
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
                        isLoading = LocalValueShimmering.current,
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
                        isLoading = LocalValueShimmering.current,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "CatalogCard",
                style = Chili.typography.H16_Primary
            )
            ShadowRoundedBox {
                CatalogCard(
                    modifier = Modifier
                        .width(186.dp),
                    title = "Заголовок",
                    isHighlighted = true,
                    icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    isLoading = LocalValueShimmering.current,
                    onClick = {}
                )
            }

            Spacer(Modifier.height(16.dp))

            Row {
                ShadowRoundedBox {
                    CatalogCard(
                        modifier = Modifier
                            .width(186.dp),
                        title = "Заголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = LocalValueShimmering.current,
                        onClick = {}
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    CatalogCard(
                        modifier = Modifier
                            .width(186.dp),
                        title = "Заголовок",
                        isLoading = LocalValueShimmering.current,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "TransferCard",
                style = Chili.typography.H16_Primary
            )

            Box(modifier = Modifier.background(Chili.color.screenBackground)) {
                Row(modifier = Modifier.padding(16.dp)) {
                    TransferCard(
                        modifier = Modifier.width(120.dp),
                        title = "Переводы по номеру телефона",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = LocalValueShimmering.current,
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    TransferCard(
                        modifier = Modifier
                            .width(120.dp),
                        title = "Между счетами",
                        isHighlighted = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = LocalValueShimmering.current,
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
                        isLoading = LocalValueShimmering.current,
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
                        isLoading = LocalValueShimmering.current,
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
                            size = BonusCardSize.Large(),
                            isLoading = LocalValueShimmering.current,
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCard(
                            modifier = Modifier,
                            title = "Заголовок",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            isLoading = LocalValueShimmering.current,
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
                            size = BonusCardSize.Middle(),
                            isLoading = LocalValueShimmering.current,
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
                            size = BonusCardSize.Small(),
                            isLoading = LocalValueShimmering.current,
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
                            isAnimating = true,
                            isLoading = LocalValueShimmering.current,
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
                            isLoading = LocalValueShimmering.current,
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
                            isAnimating = true,
                            isLoading = LocalValueShimmering.current,
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
                    isLoading = LocalValueShimmering.current,
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
                modifier = Modifier.padding(bottom = 14.dp),
                title = "Title",
                alertState = AlertState.Error,
                subtitle = "Subtitle",
                buttonText = "Кнопка",
                onButtonClick = {
                    context.showToast("AlertBlockCard")
                }
            )

            AlertBlockCard(
                title = "Title",
                alertState = AlertState.Success,
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
                    isLoading = LocalValueShimmering.current,
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
                    isLoading = LocalValueShimmering.current,
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
                            isLoading = LocalValueShimmering.current,
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
                },
                isLoading = LocalValueShimmering.current,
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
                },
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
                    var storiesIsViewed by remember { mutableStateOf(false) }
                    StoriesCard(
                        isLoading = LocalValueShimmering.current,
                        imageLink = "https://minio.o.kg/media-service/BannerConfigurator/light/125ac05b-0cbb-4bde-9da0-1f090d1fac2e",
                        isViewed = storiesIsViewed,
                        iconSize = IconSize.MEDIUM
                    ) {
                        storiesIsViewed = true
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    var storiesIsViewed by remember { mutableStateOf(false) }
                    StoriesCard(
                        isLoading = LocalValueShimmering.current,
                        imageLink = "https://minio.o.kg/media-service/BannerConfigurator/light/de574e23-7478-43af-b3df-b572093b818c",
                        isViewed = storiesIsViewed,
                        iconSize = IconSize.MEDIUM
                    ) {
                        storiesIsViewed = true
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    var storiesIsViewed by remember { mutableStateOf(false) }
                    StoriesCard(
                        imageLink = "https://minio.o.kg/media-service/BannerConfigurator/light/de574e23-7478-43af-b3df-b572093b818c",
                        isViewed = storiesIsViewed,
                        iconSize = IconSize.MEDIUM,
                        isLoading = true
                    ) {
                        storiesIsViewed = true
                    }
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "PromoCard",
                style = Chili.typography.H16_Primary
            )

            LazyRow {
                item {
                    ShadowRoundedBox {
                        PromoCard(
                            modifier = Modifier.width(194.dp),
                            icon = R.drawable.chili_ic_documents_green,
                            titleText = "",
                            isLoading = LocalValueShimmering.current,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    ShadowRoundedBox {
                        PromoCard(
                            modifier = Modifier.width(194.dp).clickable {

                            },
                            icon = R.drawable.chili_ic_documents_green,
                            titleText = "Рассрочка 0-0-12",
                            promoStatusState = PromoStatusState.New("Новое")
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    ShadowRoundedBox {
                        PromoCard(
                            modifier = Modifier.width(194.dp),
                            icon = R.drawable.chili_ic_documents_green,
                            titleText = "Рассрочка 0-0-12",
                            promoStatusState = PromoStatusState.Active("Активный"),
                            isLoading = LocalValueShimmering.current,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    ShadowRoundedBox {
                        PromoCard(
                            modifier = Modifier.width(194.dp),
                            icon = R.drawable.chili_ic_documents_green,
                            titleText = "Рассрочка 0-0-12",
                            promoStatusState = PromoStatusState.Expired("Просрочен"),
                            isLoading = LocalValueShimmering.current,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    ShadowRoundedBox {
                        PromoCard(
                            modifier = Modifier.width(194.dp),
                            icon = R.drawable.chili_ic_documents_green,
                            titleText = "Рассрочка 0-0-12",
                            promoStatusState = PromoStatusState.Wait("В ожидании"),
                            isLoading = LocalValueShimmering.current,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    ShadowRoundedBox {
                        PromoCard(
                            modifier = Modifier.width(194.dp),
                            icon = R.drawable.chili_ic_documents_green,
                            titleText = "Рассрочка 0-0-12",
                            promoStatusState = PromoStatusState.NoStatus,
                            isLoading = LocalValueShimmering.current,
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "ProgressCard",
                style = Chili.typography.H16_Primary
            )

            var progressPercent by remember { mutableIntStateOf(20) }
            val maxSum = 100_000
            val currentSum = (progressPercent * maxSum) / 100

            ShadowRoundedBox {
                ProgressCard(
                    title = "$currentSum из $maxSum c",
                    description = "Доступный объем счета",
                    progressPercent = progressPercent,
                    isLoading = LocalValueShimmering.current,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { progressPercent = 0 }) {
                    Text("0%")
                }
                Button(onClick = { progressPercent = 20 }) {
                    Text("20%")
                }
                Button(onClick = { progressPercent = 80 }) {
                    Text("80%")
                }
                Button(onClick = { progressPercent = 100 }) {
                    Text("100%")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ProgressCard(
                    title = "",
                    description = "Доступный объем счета",
                    progressPercent = 20,
                    isLoading = LocalValueShimmering.current,
                )
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "ReferralTaskCard",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = false,
                    status = ReferralTaskStatus.Loading,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = false,
                    status = ReferralTaskStatus.Completed(
                        detailInfoText = "Выполнено"
                    ),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт \u2028или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = false,
                    status = ReferralTaskStatus.Available(
                        actionButtonText = "Подробнее",
                        onActionClick = {},
                        detailInfoTextClick = {}
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт \u2028или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = false,
                    status = ReferralTaskStatus.Unavailable(
                        detailInfoText = "Недоступно",
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт \u2028или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = true,
                    status = ReferralTaskStatus.Loading
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт \u2028или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = true,
                    status = ReferralTaskStatus.Available(
                        actionButtonText = "Отправить другу",
                        detailInfoText = "Подробнее",
                        onActionClick = {},
                        detailInfoTextClick = {}
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                ReferralTaskCard(
                    title = "Пополнить кошелёк, счёт \u2028или карту",
                    subtitle = "Через другие банковские приложения или терминалах на сумму от 100 с",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isFriend = true,
                    status = ReferralTaskStatus.Completed(
                        detailInfoText = "Выполнено",
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            var isExpanded by remember { mutableStateOf(true) }

            ShadowRoundedBox {
                ExpandableCard(
                    title = "Title",
                    subtitle = "Subtitle",
                    isExpanded = isExpanded,
                    onExpandChange = { isExpanded = it },
                    expandedContent = {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Expanded content")
                        }
                    },
                    isLoading = LocalValueShimmering.current,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            var isExpanded2 by remember { mutableStateOf(true) }

            ShadowRoundedBox {
                ExpandableCard(
                    title = "Title",
                    subtitle = "Subtitle",
                    isExpanded = isExpanded2,
                    onExpandChange = { isExpanded2 = it },
                    expandedContent = {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Expanded content")
                        }
                    },
                    isLoading = LocalValueShimmering.current,
                )
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AdditionalContentInfoCard",
                style = Chili.typography.H16_Primary
            )

            val buttonTextStyle: TextStyle = Chili.typography.H14_Secondary
            val buttonShape: Shape = remember { RoundedCornerShape(20.dp) }
            val buttonBackgroundColor: Color = Chili.color.contentSecondary

            ShadowRoundedBox {
                AdditionalContentInfoCard(
                    title = "Заголовок",
                    subTitle = "Подзаголовок",
                    onClick = { context.showToast("AdditionalContentInfoCard ") },
                ) {
                    ChiliComponentButton(
                        modifier = Modifier
                            .clip(buttonShape)
                            .background(buttonBackgroundColor),
                        text = "Подробнее",
                        textStyle = buttonTextStyle,
                        enabledTextColor = buttonTextStyle.color) {}
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                AdditionalContentInfoCard(
                    title = "Оплачивайте покупки через QR  или в каталоге",
                    icon = painterResource(R.drawable.chilli_ic_bonus)
                ) {
                    ChiliComponentButton(
                        modifier = Modifier
                            .clip(buttonShape)
                            .background(buttonBackgroundColor),
                        text = "Подробнее",
                        textStyle = buttonTextStyle,
                        enabledTextColor = buttonTextStyle.color) {}
                    Spacer(modifier = Modifier.height(8.dp))

                    ChiliComponentButton(
                        modifier = Modifier
                            .clip(buttonShape)
                            .background(buttonBackgroundColor),
                        text = "Подробнее",
                        textStyle = buttonTextStyle,
                        enabledTextColor = buttonTextStyle.color) {}
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                AdditionalContentInfoCard(
                    title = "Оплачивайте покупки через QR  или в каталоге",
                    iconUrl = "",
                    icon = painterResource(R.drawable.chilli_ic_bonus)
                ) {
                    ChiliComponentButton(
                        modifier = Modifier
                            .clip(buttonShape)
                            .background(buttonBackgroundColor),
                        text = "Оплачивайте покупки через QR или в каталоге",
                        textStyle = buttonTextStyle,
                        enabledTextColor = buttonTextStyle.color) {}
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            ShadowRoundedBox {
                AdditionalContentInfoCard(
                    title = "Оплачивайте покупки через QR  или в каталоге",
                    iconUrl = "ytt"
                ) {
                    ChiliComponentButton(
                        modifier = Modifier
                            .clip(buttonShape)
                            .background(buttonBackgroundColor),
                        text = "Оплачивайте покупки через QR или в каталоге",
                        textStyle = buttonTextStyle,
                        enabledTextColor = buttonTextStyle.color) {}
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AdditionalLabelCard",
                style = Chili.typography.H16_Primary
            )

            Spacer(Modifier.width(16.dp))

            Row(Modifier.horizontalScroll(rememberScrollState())) {

                AdditionalLabelCard(
                    Modifier.size(120.dp, 106.dp),
                    icon = painterResource(R.drawable.chili_ic_documents_green),
                    additionalLabel = "10%",
                    isLoading = LocalValueShimmering.current,
                    subtitle = "no commission",
                    isHighlighted = true,
                    title = "Header and header"
                )

                Spacer(Modifier.width(8.dp))

                AdditionalLabelCard(
                    Modifier.size(120.dp, 106.dp),
                    icon = painterResource(R.drawable.chili_ic_documents_green),
                    additionalLabel = "10%",
                    isLoading = LocalValueShimmering.current,
                    subtitle = "no commission",
                    isHighlighted = false,
                    title = "Header and header"
                )

                Spacer(Modifier.width(8.dp))

                AdditionalLabelCard(
                    Modifier.size(120.dp, 106.dp),
                    icon = painterResource(R.drawable.chili_ic_documents_green),
                    additionalLabel = "10%",
                    isLoading = LocalValueShimmering.current,
                    subtitle = "no commission",
                    isHighlighted = true,
                    title = "Header"
                )

                Spacer(Modifier.width(8.dp))

                AdditionalLabelCard(
                    Modifier.size(120.dp, 106.dp),
                    icon = painterResource(R.drawable.chili_ic_documents_green),
                    isLoading = LocalValueShimmering.current,
                    isHighlighted = true,
                    title = "Header and header"
                )

                Spacer(Modifier.width(8.dp))


                AdditionalLabelCard(
                    Modifier.size(120.dp, 106.dp),
                    isLoading = LocalValueShimmering.current,
                    isHighlighted = false,
                    title = "Header"
                )

                Spacer(Modifier.width(8.dp))
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
    PreviewCards{}
}