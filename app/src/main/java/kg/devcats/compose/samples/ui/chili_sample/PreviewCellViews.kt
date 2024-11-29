package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.cells.ChiliCell
import kg.devcats.compose.jetpack_chili.components.cells.ProductCell
import kg.devcats.compose.jetpack_chili.components.common.BonusTag
import kg.devcats.compose.jetpack_chili.components.common.ChiliCheckBox
import kg.devcats.compose.jetpack_chili.components.common.ChiliSwitch
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.samples.ui.extension.showToast

@Composable
fun PreviewCellScreen(
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.surfaceBackground)) {
        ChiliCenteredAppToolbar(title = "CellViews", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)) {

            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView", style = Chili.typography.H16_Primary)

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        isDividerVisible = true,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        isDividerVisible = true,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                    )
                }
            }

            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView subtitle", style = Chili.typography.H16_Primary)

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                    )
                }
            }

            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView with icon", style = Chili.typography.H16_Primary)

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                    )
                }
            }


            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView with Additilnal text", style = Chili.typography.H16_Primary)

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            Text(text = "Additonal text", style = Chili.typography.H14_Value)
                        },
                    )
                }
            }

            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView with BonusTag", style = Chili.typography.H16_Primary)

            ShadowRoundedBox(modifier = Modifier) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "1%")
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "10%")
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = null,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            BonusTag(modifier = Modifier, text = "100%")
                        },
                    )
                }
            }


            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView with Switch", style = Chili.typography.H16_Primary)

            var switchChecked by remember { mutableStateOf(false) }
            var switchChecked2 by remember { mutableStateOf(false) }
            var switchChecked3 by remember { mutableStateOf(false) }
            var switchChecked4 by remember { mutableStateOf(false) }

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked) {switchChecked = it}
                        },
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked2) {switchChecked2 = it}
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked3) {switchChecked3 = it}
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliSwitch(checked = switchChecked4) {switchChecked4 = it}
                        },
                    )
                }
            }

            var checkBoxChecked by remember { mutableStateOf(false) }
            var checkBoxChecked2 by remember { mutableStateOf(false) }
            var checkBoxChecked3 by remember { mutableStateOf(false) }
            var checkBoxChecked4 by remember { mutableStateOf(false) }

            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "CellView with Check box", style = Chili.typography.H16_Primary)

            ShadowRoundedBox() {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked) {checkBoxChecked = it}
                        },
                    )
                }
            }

            ShadowRoundedBox(modifier = Modifier.padding(top = 16.dp)) {
                Column {
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked2) {checkBoxChecked2 = it}
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        isDividerVisible = true,
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked3) {checkBoxChecked3 = it}
                        },
                    )
                    ChiliCell(
                        modifier = Modifier.clickable {  },
                        title = "Заголовок",
                        subtitle = "Подзаголовок",
                        icon = painterResource(id = R.drawable.chili_ic_documents_green),
                        endFrame = {
                            ChiliCheckBox(checked = checkBoxChecked4) {checkBoxChecked4 = it}
                        },
                    )
                }
            }

            Text(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp), text = "ProductCellView", style = Chili.typography.H16_Primary)

            Column {
                ProductCell(
                    modifier = Modifier.clickable {  }.padding(vertical = 8.dp),
                    title = "Заголовок",
                    additionalText = "121212 <u>c</u>",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                    isLoading = true
                )
                ProductCell(
                    modifier = Modifier.clickable {}.padding(vertical = 8.dp),
                    title = "Заголовок",
                    subtitle = "Подзаголовок",
                    additionalText = "121212",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                    isMain = true,
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
                    subtitleTextAppearance = Chili.typography.H12_Error,
                    additionalTextAppearance = Chili.typography.H15_Error,
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp).alpha(0.4f),
                    title = "Заголовок",
                    additionalText = "Сервис \nнедоступен",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок",
                    additionalText = "121212",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default)
                )
                ProductCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    title = "Заголовок, занимающий 3 строки, Заголовок, занимающий 3 строки, Заголовок, занимающий 3 строки, Заголовок, занимающий 3 строки",
                    subtitle = "Подзаголовок, Подзаголовок, Подзаголовок, Подзаголовок, Подзаголовок, ",
                    icon = painterResource(kg.devcats.compose.samples.R.drawable.ic_card_default),
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCellViews() {
    PreviewCellScreen({})
}