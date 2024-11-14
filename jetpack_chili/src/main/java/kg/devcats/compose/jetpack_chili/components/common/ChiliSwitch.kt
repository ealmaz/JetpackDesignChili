package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliSwitch(
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
            checkedThumbColor = Chili.color.switchCheckedThumb,
            checkedTrackColor = Chili.color.switchCheckedTrack,
            checkedBorderColor = Chili.color.switchCheckedTrack,
            checkedIconColor = Chili.color.switchCheckedTrack,
            uncheckedThumbColor = Chili.color.switchUncheckedThumb,
            uncheckedTrackColor = Chili.color.switchUncheckedTrack,
            uncheckedBorderColor = Chili.color.switchUncheckedTrack,
            uncheckedIconColor = Chili.color.switchUncheckedTrack,
            disabledCheckedThumbColor = Chili.color.switchDisabledCheckedThumb,
            disabledCheckedTrackColor = Chili.color.switchDisabledCheckedTrack,
            disabledCheckedBorderColor = Chili.color.switchDisabledCheckedTrack,
            disabledCheckedIconColor = Chili.color.switchDisabledCheckedTrack,
            disabledUncheckedThumbColor = Chili.color.switchDisabledUncheckedThumb,
            disabledUncheckedTrackColor = Chili.color.switchDisabledUncheckedTrack,
            disabledUncheckedBorderColor = Chili.color.switchDisabledUncheckedTrack,
            disabledUncheckedIconColor = Chili.color.switchDisabledUncheckedTrack
        )
    )
    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliToggle() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
        ChiliSwitch(checked = true, modifier = Modifier.padding(16.dp)) {}
    }
}
