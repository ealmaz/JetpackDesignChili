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
import kg.devcats.compose.jetpack_chili.components.picker.base.DefaultWheelDateTimePicker
import kg.devcats.compose.jetpack_chili.components.picker.base.SelectorProperties
import kg.devcats.compose.jetpack_chili.components.picker.base.TimeFormat
import kg.devcats.compose.jetpack_chili.components.picker.base.WheelPickerDefaults
import java.time.LocalDateTime

@SuppressLint("NewApi")
@Composable
fun WheelDateTimePicker(
    modifier: Modifier = Modifier,
    startDateTime: LocalDateTime = LocalDateTime.now(),
    minDateTime: LocalDateTime = LocalDateTime.MIN,
    maxDateTime: LocalDateTime = LocalDateTime.MAX,
    yearsRange: IntRange? = IntRange(1922, 2122),
    timeFormat: TimeFormat = TimeFormat.HOUR_24,
    size: DpSize = DpSize(256.dp, 128.dp),
    rowCount: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedDateTime : (snappedDateTime: LocalDateTime) -> Unit = {}
) {
    DefaultWheelDateTimePicker(
        modifier,
        startDateTime,
        minDateTime,
        maxDateTime,
        yearsRange,
        timeFormat,
        size,
        rowCount,
        textStyle,
        textColor,
        selectorProperties,
        onSnappedDateTime = { snappedDateTime ->
            onSnappedDateTime(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        }
    )
}