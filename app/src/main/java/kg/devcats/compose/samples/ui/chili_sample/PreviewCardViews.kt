package kg.devcats.compose.samples.ui.chili_sample

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import kg.devcats.compose.jetpack_chili.components.cards.BalanceCard
import kg.devcats.compose.jetpack_chili.components.cards.BonusCardSize
import kg.devcats.compose.jetpack_chili.components.cards.BonusCard
import kg.devcats.compose.jetpack_chili.components.cards.AnimatedBorderCard
import kg.devcats.compose.jetpack_chili.components.cards.CatalogCard
import kg.devcats.compose.jetpack_chili.components.cards.ChiliCard
import kg.devcats.compose.jetpack_chili.components.cards.InfoState
import kg.devcats.compose.jetpack_chili.components.cards.PaymentCard
import kg.devcats.compose.jetpack_chili.components.cards.PieChartCard
import kg.devcats.compose.jetpack_chili.components.cards.ProductCard
import kg.devcats.compose.jetpack_chili.components.cards.SubtitledSimpleCard
import kg.devcats.compose.jetpack_chili.components.cells.MultiIconedTitleCell
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

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
            title = "CardViews",
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
                text = "ChiliCardView",
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
                text = "BalanceCardView",
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
                text = "CatalogCardView",
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
                text = "PaymentCardView",
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
                text = "AccentCardView",
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
                text = "BonusCardView",
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
                text = "AnimatedCardView",
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
                text = "AccentCardView",
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
                text = "MultiiconedCellView",
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
                    title = "MultiiconedCellView title",
                    additionalInfo = "Весь список",
                    isLoading = true,
                    onAdditionalInfoClick = {
                        Toast.makeText(context, "Additional info clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AlertBlockCardView",
                style = Chili.typography.H16_Primary
            )

            var isOpened by remember { mutableStateOf(true) }

            if (isOpened) {
                AlertBlockCard(
                    modifier = Modifier.padding(bottom = 14.dp),
                    title = "Title",
                    infoState = InfoState.Neutral,
                    subtitle = "Subtitle",
                    buttonText = "Кнопка",
                    isClosable = true,
                    onCloseClick = { isOpened = false }
                )
            }

            AlertBlockCard(
                modifier = Modifier.padding(bottom = 14.dp),
                title = "Деньги поступят на счёт по умолчанию",
                infoState = InfoState.Warning
            )

            AlertBlockCard(
                title = "Title",
                infoState = InfoState.Error,
                subtitle = "Subtitle",
                buttonText = "Кнопка",
                onButtonClick = {
                    context.showToast("AlertBlockCardView")
                }
            )

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "SubtitledSimpleCardView",
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
                text = "ProductCardView",
                style = Chili.typography.H16_Primary
            )

            LazyVerticalGrid (
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    ShadowRoundedBox {
                        ProductCard(
                            modifier = Modifier.height(293.dp).width(168.dp),
                            imageLink = "https://cdn.omarket.kg/ads-minify/XBxctjZWlSKjq9pgazTZoEOPh45SXToHMckuX1Ce7Rnb3egueT.WEBP",
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
                            modifier = Modifier.height(293.dp).width(168.dp),
                            price = "2 090,00 c",
                            installmentPrice = "8 166,6 с x 12 мес",
                            description = "Электрочайник BEREKE BR-810 серый",
                            isLoading = true
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCardViews() {
    PreviewCards({})
}