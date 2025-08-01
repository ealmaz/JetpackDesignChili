package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliDoubledButtons
import kg.devcats.compose.jetpack_chili.components.cells.AnimatedCell
import kg.devcats.compose.jetpack_chili.components.cells.DetailedInfoCell
import kg.devcats.compose.jetpack_chili.components.cells.ChiliAdditionalInfoCell
import kg.devcats.compose.jetpack_chili.components.cells.ChiliCell
import kg.devcats.compose.jetpack_chili.components.cells.ChiliCheckBoxCell
import kg.devcats.compose.jetpack_chili.components.cells.DoubleCell
import kg.devcats.compose.jetpack_chili.components.cells.DoubleCellItemParams
import kg.devcats.compose.jetpack_chili.components.cells.ProductCell
import kg.devcats.compose.jetpack_chili.components.common.AdvancedShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.common.BonusTag
import kg.devcats.compose.jetpack_chili.components.common.ChiliCheckBox
import kg.devcats.compose.jetpack_chili.components.common.ChiliMaterial2Switch
import kg.devcats.compose.jetpack_chili.components.common.ChiliSwitch
import kg.devcats.compose.jetpack_chili.components.common.CircledBadgeText
import kg.devcats.compose.jetpack_chili.components.common.RoundedBox
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.setRoundedShapeByPosition
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.LocalValueShimmering
import kg.devcats.compose.samples.SampleToolbarMenu
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewCells(
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Chili.color.surfaceBackground)
    ) {
        ChiliCenteredAppToolbar(
            modifier = Modifier.statusBarsPadding(),
            title = "Cell",
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

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell",
                style = Chili.typography.H16_Primary
            )

            AdvancedShadowRoundedBox {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        isLoading = LocalValueShimmering.current
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок ",
                        isLoading = LocalValueShimmering.current,
                        endFrame = {
                            CircledBadgeText(
                                badgeText = "2368"
                            )
                        }
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        isLoading = LocalValueShimmering.current,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Rounded corner modifier",
                style = Chili.typography.H16_Primary
            )

            Box(modifier = Modifier.background(Chili.color.screenBackground)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ChiliCell(
                        modifier = Modifier
                            .setRoundedShapeByPosition(true, true)
                            .clickable { },
                        title = "Заголовок",
                        isDividerVisible = false,
                        isLoading = LocalValueShimmering.current,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ChiliCell(
                        modifier = Modifier
                            .setRoundedShapeByPosition(isFirst = true)
                            .clickable { },
                        title = "Заголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier
                            .setRoundedShapeByPosition()
                            .clickable { },
                        title = "Заголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier
                            .setRoundedShapeByPosition()
                            .clickable { },
                        title = "Заголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier
                            .setRoundedShapeByPosition(isLast = true)
                            .clickable { },
                        title = "Заголовок",
                        isLoading = LocalValueShimmering.current,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell subtitle",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        iconUrl = "https://devminio.o.kg/media-service/AlgaMoreMenuConfigurator/light/a50317e3-98e3-4f11-97e4-6e5f0ecc6ef0"
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell with icon",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = LocalValueShimmering.current,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isLoading = LocalValueShimmering.current,
                    )
                }
            }


            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell with Additilnal text",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox {
                Column {
                    ChiliAdditionalInfoCell(
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        isChevronVisible = true,
                        isDividerVisible = true,
                        additionalInfo = "Additional text",
                        additionalInfoStyle = Chili.typography.H14_Value,
                        isLoading = LocalValueShimmering.current,
                        containerBackgroundColor = Chili.color.alertErrorBg
                    )

                    ChiliAdditionalInfoCell(
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        additionalInfo = "Additional 1212 <u>c</u>",
                        additionalInfoStyle = Chili.typography.H14_Value,
                        isDividerVisible = true
                    )

                    ChiliAdditionalInfoCell(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Заголовок",
                        titleStyle = Chili.typography.H16_Value,
                        titleMaxLines = 3,
                        additionalInfo = "Подзаголовок Под Подзаголовок Под Подзаголовок",
                        additionalInfoStyle = Chili.typography.H16_Marked.copy(textAlign = TextAlign.End),
                        additionalInfoTextWeight = 1f,
                        isLoading = LocalValueShimmering.current,
                        isDividerVisible = true
                    )

                    ChiliAdditionalInfoCell(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Заголовок",
                        titleStyle = Chili.typography.H16_Value,
                        titleMaxLines = 3,
                        isLoading = LocalValueShimmering.current,
                        additionalInfo = "Подзаголовок Под Подзаголовок Под Подзаголовок Подзаголовок ",
                        additionalInfoStyle = Chili.typography.H16_Marked.copy(textAlign = TextAlign.End),
                        additionalInfoTextWeight = 1f,
                        containerPaddingValues = PaddingValues(start = 8.dp),
                        additionalInfoTextPaddingValues = PaddingValues(
                            start = 4.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell with BonusTag",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox(modifier = Modifier) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "1%")
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "1%")
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "10%")
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        isLoading = LocalValueShimmering.current,
                        subtitle = null,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "100%")
                        },
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "AnimatedCell",
                style = Chili.typography.H16_Primary
            )

            AnimatedCell(
                modifier = Modifier.fillMaxWidth(),
                title = "Переведите пользователю на O!Bank(O!Деньги)",
                subtitle = "Подзаголовок",
                titleMaxLines = 2,
                titleOverflow = TextOverflow.Visible,
                isChevronVisible = true,
                subtitleStyle = Chili.typography.H14_Success,
                iconUrl = "",
                isLoading = LocalValueShimmering.current
            )

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell with Switch",
                style = Chili.typography.H16_Primary
            )

            var switchChecked by remember { mutableStateOf(false) }
            var switchChecked2 by remember { mutableStateOf(false) }
            var switchChecked3 by remember { mutableStateOf(false) }
            var switchChecked4 by remember { mutableStateOf(false) }
            var switchCheckedMaterial by remember { mutableStateOf(false) }

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked) { switchChecked = it }
                        },
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(
                                enabled = false,
                                checked = switchChecked2
                            ) { switchChecked2 = it }
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked2) { switchChecked2 = it }
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked3) { switchChecked3 = it }
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked4) { switchChecked4 = it }
                        },
                    )
                }
            }

            RoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Material2 Switch",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliMaterial2Switch(checked = switchCheckedMaterial) {
                                switchCheckedMaterial = it
                            }
                        },
                        isChevronVisible = false
                    )
                }
            }

            var checkBoxChecked by remember { mutableStateOf(false) }
            var checkBoxChecked2 by remember { mutableStateOf(false) }
            var checkBoxChecked3 by remember { mutableStateOf(false) }
            var checkBoxChecked4 by remember { mutableStateOf(false) }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "Cell with Check box",
                style = Chili.typography.H16_Primary
            )

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked) { checkBoxChecked = it }
                        },
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(
                                enabled = false,
                                checked = checkBoxChecked2
                            ) { checkBoxChecked2 = it }
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked2) { checkBoxChecked2 = it }
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked3) { checkBoxChecked3 = it }
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable { },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isLoading = LocalValueShimmering.current,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked4) { checkBoxChecked4 = it }
                        },
                    )
                }
            }

            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "ProductCell",
                style = Chili.typography.H16_Primary
            )

            Column {
                ProductCell(
                    modifier = Modifier
                        .clickable { }
                        .padding(vertical = 8.dp),
                    title = "Заголовок",
                    additionalText = "121212 <u>c</u>",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                    isLoading = LocalValueShimmering.current,
                )
                ProductCell(
                    modifier = Modifier
                        .clickable {}
                        .padding(vertical = 8.dp),
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    additionalText = "121212",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                    isMain = true,
                    isLoading = LocalValueShimmering.current,
                    onClick = {
                        context.showToast("Clicked")
                    }
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    additionalText = "121212 <u>c</u>",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                    isBlocked = true,
                    isLoading = LocalValueShimmering.current,
                    overlayIcon = painterResource(kg.devcats.compose.samples.R.drawable.chili_ic_lock),
                    subtitleTextAppearance = Chili.typography.H12_Error,
                    onClick = {
                        context.showToast("Clicked")
                    }
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    isLoading = LocalValueShimmering.current,
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                    overlayIcon = painterResource(kg.devcats.compose.samples.R.drawable.ic_overlay_status_declined),
                    onClick = {
                        context.showToast("Clicked")
                    }
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок, занимающий 2 строки",
                    subtitle = "Подзаголовок",
                    additionalText = "121212 <u>c</u>",
                    isLoading = LocalValueShimmering.current,
                    subtitleTextAppearance = Chili.typography.H12_Error,
                    additionalTextAppearance = Chili.typography.H15_Error,
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                )
                ProductCell(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .alpha(0.4f),
                    title = "Заголовок",
                    isLoading = LocalValueShimmering.current,
                    additionalText = "Сервис \nнедоступен",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок",
                    additionalText = "121212",
                    isLoading = LocalValueShimmering.current,
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default)
                )
                ProductCell(
                    isLoading = LocalValueShimmering.current,
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок, занимающий 3 строки, Заголовок, занимающий 3 строки, Заголовок, занимающий 3 строки, Заголовок, занимающий 3 строки",
                    subtitle = "Подзаголовок, Подзаголовок, Подзаголовок, Подзаголовок, Подзаголовок, ",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier
                .background(Chili.color.screenSecondary)
                .padding(16.dp)) {
                DetailedInfoCell(
                    icon = painterResource(R.drawable.chili_ic_documents_green),
                    title = "USDT (TRC20)",
                    subTitle = "Достигнут лимит",
                    value = "- 500,00 <u>с</u>",
                    subValue = "44 500,00 ",
                    isLoading = LocalValueShimmering.current,
                    additionalText = "Ожидает зачисления: 10 500,00 <u>с</u>",
                    caption = "09:42",
                )

                Spacer(modifier = Modifier.height(8.dp))

                DetailedInfoCell(
                    icon = painterResource(R.drawable.chili_ic_documents_green),
                    title = "USDT (TRC20)",
                    subTitle = "Достигнут лимит",
                    value = "- 500,00 с",
                    subValue = "44 500,00 с ",
                    isLoading = LocalValueShimmering.current,
                    additionalText = "Ожидает зачисления: 10 500,00 c",
                    caption = "09:42",
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier
                .background(Chili.color.screenSecondary)
                .padding(16.dp)) {
                DoubleCell(
                    DoubleCellItemParams.getDefaultParams(
                        title = "Заговолок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(R.drawable.chili_ic_documents_green)
                    ),
                    DoubleCellItemParams.getDefaultParams(
                        title = "Заговолок",
                        subtitle = "Подзаголовок",
                        additionalText = "За ужин",
                        icon = painterResource(R.drawable.chili_ic_documents_green)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                DoubleCell(
                    DoubleCellItemParams.getDefaultParams(
                        title = "Кошелек О!Деньги",
                        icon = painterResource(R.drawable.chili_ic_documents_green)
                    ),
                    DoubleCellItemParams.getDefaultParams(
                        title = "BTC в сети Bitcoin",
                        subtitle = "0,053718",
                        icon = painterResource(R.drawable.chili_ic_documents_green)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .clip(Chili.shapes.RoundedCornerShape)
                        .background(Chili.color.cellViewBackground)
                ) {
                    DetailedInfoCell(
                        icon = painterResource(R.drawable.chili_ic_documents_green),
                        title = "Double buttons",
                        value = "6 200 c",
                        isLoading = LocalValueShimmering.current,
                        subTitle = "Below this cell"
                    )
                    ChiliDoubledButtons(startButtonText = "Продлить", endButtonText = "Погасить")
                }
            }
            Column(
                modifier = Modifier
                    .background(Chili.color.screenSecondary)
                    .padding(16.dp)
                    .clip(Chili.shapes.RoundedCornerShape)
            ) {
                var isCheckboxCellChecked by remember { mutableStateOf(true) }
                ChiliCheckBoxCell(
                    title = "Сhecked",
                    subtitle = "Подзаголовок",
                    checked = isCheckboxCellChecked,
                    iconUrl = "url",
                    isDividerVisible = true,
                    isLoading = LocalValueShimmering.current,
                    additionalInfo = "Text text",
                    onCheckedChange = {
                        isCheckboxCellChecked = it
                    }
                )
                ChiliCheckBoxCell(
                    title = "Unchecked",
                    subtitle = "Подзаголовок",
                    checked = false,
                    isLoading = LocalValueShimmering.current,
                    iconUrl = "url",
                    isDividerVisible = true,
                    additionalInfo = "500,00 <u>c</u>",
                    onCheckedChange = {}
                )
                ChiliCheckBoxCell(
                    title = "Disabled",
                    subtitle = "Выключенный",
                    isDividerVisible = true,
                    checked = false,
                    isLoading = LocalValueShimmering.current,
                    enabled = false,
                    iconUrl = "url",
                    additionalInfo = "35,00 <u>c</u>",
                ) { }
                ChiliCheckBoxCell(
                    title = "Заголовок",
                    isLoading = LocalValueShimmering.current,
                    subtitle = "Подзаголовок",
                    isDividerVisible = true,
                    checked = isCheckboxCellChecked,
                    iconUrl = "url",
                ) {
                    isCheckboxCellChecked = it
                }
                ChiliCheckBoxCell(
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    isLoading = LocalValueShimmering.current,
                    checked = true,
                    iconUrl = "url",
                    additionalInfo = "5,00 <u>c</u>",
                ) { }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCellViews() {
    PreviewCells({})
}