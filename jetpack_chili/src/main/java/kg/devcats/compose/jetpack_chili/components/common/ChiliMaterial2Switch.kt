package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import androidx.compose.material.*

@Composable
fun ChiliMaterial2Switch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Switch(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Chili.color.materialSwitchCheckedThumb,
            checkedTrackColor = Chili.color.materialSwitchCheckedTrack,
            uncheckedThumbColor = Chili.color.materialSwitchUncheckedThumb,
            uncheckedTrackColor = Chili.color.materialSwitchUncheckedTrack
        )
    )
    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliMaterialToggle() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
        ChiliMaterial2Switch(checked = true, modifier = Modifier.padding(16.dp)) {}
    }
}
