package kg.devcats.compose.samples.ui.chili_sample

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.common.AgreementCell
import kg.devcats.compose.jetpack_chili.components.common.AnimatedProgressLine
import kg.devcats.compose.jetpack_chili.components.common.BonusTag
import kg.devcats.compose.jetpack_chili.components.common.ChiliCheckBox
import kg.devcats.compose.jetpack_chili.components.common.ChiliLoader
import kg.devcats.compose.jetpack_chili.components.common.ChiliSwitch
import kg.devcats.compose.jetpack_chili.components.common.ShadowRoundedBox
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.blue_1
import kg.devcats.compose.jetpack_chili.theme.green_1
import kg.devcats.compose.jetpack_chili.theme.red_1

@Composable
fun PreviewCommon(
    navigateUp: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.surfaceBackground)) {
        ChiliCenteredAppToolbar(title = "Common", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 64.dp)) {

            ShadowRoundedBox(modifier = Modifier.fillMaxWidth()) {
                Column {
                    var isChecked by remember { mutableStateOf(false) }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                        ChiliCheckBox(checked = isChecked) { isChecked = it }
                        Text(text = "ChiliCheckBox", style = Chili.typography.H16_Primary)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                        ChiliCheckBox(checked = isChecked, enabled = false) { isChecked = it }
                        Text(text = "Disabled ChiliCheckBox", style = Chili.typography.H16_Primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            ShadowRoundedBox(modifier = Modifier.fillMaxWidth()) {
                Column {
                    var isChecked by remember { mutableStateOf(false) }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                        ChiliSwitch(checked = isChecked) { isChecked = it }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "ChiliCheckBox", style = Chili.typography.H16_Primary)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                        ChiliSwitch(checked = isChecked, enabled = false) { isChecked = it }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Disabled ChiliCheckBox", style = Chili.typography.H16_Primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            ShadowRoundedBox(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                        ChiliLoader()
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Chili loader", style = Chili.typography.H16_Primary)
                    }
                }
            }
        }

        Text(modifier = Modifier.padding(start = 16.dp), text = "BonusTag", style = Chili.typography.H16_Primary)

        val context = LocalContext.current
        Row {
            BonusTag(modifier = Modifier
                .padding(16.dp), text = "Бонусы: 21 343,00") {
                Toast.makeText(context, "Bonus clicked", Toast.LENGTH_SHORT).show()
            }

            BonusTag(modifier = Modifier
                .padding(16.dp), enabled = false, text = "+10,00") {
                Toast.makeText(context, "Bonus clicked", Toast.LENGTH_SHORT).show()
            }
        }

        Column {
            AgreementCell(
                modifier = Modifier.padding(start = 6.dp),
                title = stringResource(id = kg.devcats.compose.samples.R.string.clickable_link_example)
            ){
                Toast.makeText(context, "Link clicked: $it", Toast.LENGTH_SHORT).show()
            }

            AgreementCell(
                modifier = Modifier.padding(start = 16.dp),
                isEditable = false,
                title = stringResource(id = kg.devcats.compose.samples.R.string.clickable_link_example)
            ){
                Toast.makeText(context, "Link clicked: $it", Toast.LENGTH_SHORT).show()
            }
        }

        Text(modifier = Modifier.padding(16.dp), text = "AnimatedProgressLine", style = Chili.typography.H16_Primary)

        var progress by remember { mutableIntStateOf(20) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedProgressLine(
                progressPercent = progress,
                progressGradientColors = listOf(red_1, green_1, blue_1),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { progress = 0 }) {
                    Text("0%")
                }
                Button(onClick = { progress = 20 }) {
                    Text("20%")
                }
                Button(onClick = { progress = 80 }) {
                    Text("80%")
                }
                Button(onClick = { progress = 100 }) {
                    Text("100%")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCommonViews() {
    PreviewCommon({})
}