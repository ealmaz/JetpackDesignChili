package kg.devcats.compose.samples.ui.chili_sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliAppToolbar
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.R

@Composable
fun Toolbars(
    navigateUp: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Chili.color.screenBackground)) {
        ChiliCenteredAppToolbar(title = "Toolbars", isDividerVisible = true, isNavigationIconVisible = true, onNavigationIconClick = {
            navigateUp.invoke()
        })
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, bottom = 64.dp)) {

            ChiliAppToolbar(title = "Simple toolbar", isDividerVisible = true, isNavigationIconVisible = false)

            Spacer(modifier = Modifier.height(8.dp))

            ChiliAppToolbar(title = "Simple toolbar")

            Spacer(modifier = Modifier.height(8.dp))

            ChiliAppToolbar(title = "Simple toolbar", navigationIcon = painterResource(id = R.drawable.chili_ic_close))

            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(title = "Centered toolbar", navigationIcon = painterResource(id = R.drawable.chili_ic_close))


            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(title = "End icon toolbar", navigationIcon = painterResource(id = R.drawable.chili_ic_close), endFrame = {
                Image(painter = painterResource(id = R.drawable.chili_ic_documents_green), contentDescription = "")
            })

            Spacer(modifier = Modifier.height(8.dp))

            ChiliCenteredAppToolbar(title = "Start icon toolbar", navigationIcon = painterResource(id = R.drawable.chili_ic_close), startFrame = {
                Image(painter = painterResource(id = R.drawable.chili_ic_documents_green), contentDescription = "")
            })

            Spacer(modifier = Modifier.height(8.dp))

            ChiliAppToolbar(title = "Transparent toolbar", isDividerVisible = true, isNavigationIconVisible = false, backgroundColor = Color.Transparent)

            Spacer(modifier = Modifier.height(8.dp))

            var expanded by remember { mutableStateOf(false) }

            ChiliCenteredAppToolbar(
                title = "End icon toolbar",
                isNavigationIconVisible = false,
                endFrame = {
                    Image(painter = painterResource(id = kg.devcats.compose.jetpack_chili.R.drawable.chili_ic_more),
                        contentDescription = "",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false)
                        ) {
                            expanded = true

                        })
                    popupMenu(expanded) { expanded = false }
                })

        }
    }
}

@Composable fun popupMenu(expanded: Boolean, onDismissRequest: () -> Unit) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(Chili.color.surfaceBackground)
    ) {
        DropdownMenuItem(
            text = {  Text("Sample 1") },
            onClick = { onDismissRequest() }
        )
        DropdownMenuItem(
            text = { Text("Sample 2") },
            onClick = {onDismissRequest() /* Handle settings! */ }
        )
        Divider()
        DropdownMenuItem(
            text = { Text("Sample 3") },
            onClick = { onDismissRequest()/* Handle send feedback! */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewToolbars() {
    Toolbars({})
}