package kg.devcats.compose.samples.ui.chili_sample


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun TextAppearance(
    navigateUp: () -> Unit,
) {
    Column(modifier = Modifier.background(Chili.color.surfaceBackground)) {
        ChiliCenteredAppToolbar(title = "TextAppearance", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp)) {
            Text(text = "Typography H64", style = Chili.typography.H64, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H32", style = Chili.typography.H32, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H28", style = Chili.typography.H28, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H24", style = Chili.typography.H24, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H20", style = Chili.typography.H20, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H18", style = Chili.typography.H18, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H16", style = Chili.typography.H16, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H14", style = Chili.typography.H14, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H12", style = Chili.typography.H12, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Typography H10", style = Chili.typography.H10, color = Chili.color.primaryText)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Primary color", style = Chili.typography.H16_Primary)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Secondary color", style = Chili.typography.H16_Secondary)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Value color", style = Chili.typography.H16_Value)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Marked color", style = Chili.typography.H16_Marked)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Error color", style = Chili.typography.H16_Error)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Primary color weight 400", style = Chili.typography.H16_Primary)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Primary color weight 500", style = Chili.typography.H16_Primary_500)
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Typography Primary color weight 700", style = Chili.typography.H16_Primary_700)
            Divider(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTextAppearance() {
    TextAppearance({})
}
