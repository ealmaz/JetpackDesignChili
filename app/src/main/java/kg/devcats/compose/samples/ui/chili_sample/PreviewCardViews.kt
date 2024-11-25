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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import kg.devcats.compose.jetpack_chili.components.cards.AccentCardView
import kg.devcats.compose.jetpack_chili.components.cards.AlertBlockCardView
import kg.devcats.compose.jetpack_chili.components.cards.BalanceCardView
import kg.devcats.compose.jetpack_chili.components.cards.BonusCardSize
import kg.devcats.compose.jetpack_chili.components.cards.BonusCardView
import kg.devcats.compose.jetpack_chili.components.cards.BorderAnimatedView
import kg.devcats.compose.jetpack_chili.components.cards.CatalogCardView
import kg.devcats.compose.jetpack_chili.components.cards.ChiliCardView
import kg.devcats.compose.jetpack_chili.components.cards.InfoState
import kg.devcats.compose.jetpack_chili.components.cards.PaymentCardView
import kg.devcats.compose.jetpack_chili.components.cards.PieChartCardView
import kg.devcats.compose.jetpack_chili.components.cards.SubtitledSimpleCardView
import kg.devcats.compose.jetpack_chili.components.cells.MultiIconedTitleCellView
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun CardViews(
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
                    ChiliCardView(
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
                    ChiliCardView(
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
                    BalanceCardView(
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
                    BalanceCardView(
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
                    CatalogCardView(
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
                    CatalogCardView(
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
                    PaymentCardView(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    PaymentCardView(
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
                    AccentCardView(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Сканер штрихкодов и QR",
                        subtitle = "Для удобной оплаты \nбез ввода реквизитов",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }

                ShadowRoundedBox(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    AccentCardView(
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
                        BonusCardView(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            size = BonusCardSize.Large()
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCardView(
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
                        BonusCardView(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            size = BonusCardSize.Middle()
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCardView(
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
                        BonusCardView(
                            modifier = Modifier,
                            title = "Сканер штрихкодов и QR",
                            icon = painterResource(id = R.drawable.chili_ic_documents_green),
                            size = BonusCardSize.Small()
                        )
                    }

                    ShadowRoundedBox(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        BonusCardView(
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
                        BorderAnimatedView(
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
                        BorderAnimatedView(
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
                        BorderAnimatedView(
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
                        BorderAnimatedView(
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
                    PieChartCardView(
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
                    PieChartCardView(
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
                MultiIconedTitleCellView(
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
                AlertBlockCardView(
                    modifier = Modifier.padding(bottom = 14.dp),
                    title = "Title",
                    infoState = InfoState.Neutral,
                    subtitle = "Subtitle",
                    buttonText = "Кнопка",
                    isClosable = true,
                    onCloseClick = { isOpened = false }
                )
            }

            AlertBlockCardView(
                modifier = Modifier.padding(bottom = 14.dp),
                title = "Деньги поступят на счёт по умолчанию",
                infoState = InfoState.Warning
            )

            AlertBlockCardView(
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
                SubtitledSimpleCardView(
                    title = "Favorite",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png"
                )
            }

            ShadowRoundedBox(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                SubtitledSimpleCardView(
                    title = "Favorite",
                    iconUrl = "https://minio.o.kg/catalog/logos/obank.png",
                    isLoading = true
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCardViews() {
    CardViews({})
}