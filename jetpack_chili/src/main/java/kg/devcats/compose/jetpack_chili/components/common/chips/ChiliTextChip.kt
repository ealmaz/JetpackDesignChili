package kg.devcats.compose.jetpack_chili.components.common.chips

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili
import kg.devcats.compose.jetpack_chili.theme.magenta_1
import kg.devcats.compose.jetpack_chili.theme.white_1

@Composable
fun ChiliTextChip(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Chili.typography.H14_Primary_500,
    isSelected: Boolean,
    selectedChipBackgroundColor: Color = magenta_1,
    unselectedChipBackgroundColor: Color = Chili.color.textChipUnselectedBackgroundColor,
    selectedStateTextColor: Color = white_1,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) selectedChipBackgroundColor else unselectedChipBackgroundColor
    val textColor = if (isSelected) selectedStateTextColor else textStyle.color

    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = Chili.shapes.RoundedCornerShape,
        color = backgroundColor,
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}