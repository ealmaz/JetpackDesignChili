package kg.devcats.compose.jetpack_chili.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun ChiliCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Checkbox(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = Chili.color.checkBoxCheckedBackground,
            uncheckedColor = Chili.color.checkBoxUncheckedBorder,
            checkmarkColor = Chili.color.checkBoxCheckedCheckmark,
            disabledCheckedColor = Chili.color.checkBoxDisabled,
            disabledUncheckedColor = Chili.color.checkBoxDisabled,
            disabledIndeterminateColor = Chili.color.checkBoxDisabled
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChiliCheckBox() {
    ShadowRoundedBox(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 40.dp)) {
        ChiliCheckBox(checked = true, onCheckedChange = {})
    }
}
