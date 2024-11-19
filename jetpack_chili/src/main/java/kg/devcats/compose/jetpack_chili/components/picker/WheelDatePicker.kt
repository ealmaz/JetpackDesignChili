package kg.devcats.compose.jetpack_chili.components.picker

import android.annotation.SuppressLint
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.picker.base.WheelPickerDefaults
import kg.devcats.compose.jetpack_chili.components.picker.base.SelectorProperties
import kg.devcats.compose.jetpack_chili.components.picker.base.DefaultWheelDatePicker
import java.time.LocalDate

@SuppressLint("NewApi")
@Composable
fun WheelDatePicker(
    modifier: Modifier = Modifier,
    startDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.MIN,
    maxDate: LocalDate = LocalDate.MAX,
    yearsRange: IntRange? = IntRange(1922, 2122),
    size: DpSize = DpSize(256.dp, 128.dp),
    rowCount: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedDate : (snappedDate: LocalDate) -> Unit = {}
) {
    DefaultWheelDatePicker(
        modifier,
        startDate,
        minDate,
        maxDate,
        yearsRange,
        size,
        rowCount,
        textStyle,
        textColor,
        selectorProperties,
        onSnappedDate = { snappedDate ->
            onSnappedDate(snappedDate.snappedLocalDate)
            snappedDate.snappedIndex
        }
    )
}