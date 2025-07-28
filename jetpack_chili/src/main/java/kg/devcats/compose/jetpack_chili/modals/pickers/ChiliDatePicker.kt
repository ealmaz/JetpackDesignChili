package kg.devcats.compose.jetpack_chili.modals.pickers

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.modals.bottom_sheets.ChiliBottomSheetContainer
import kg.devcats.compose.jetpack_chili.modals.pickers.wheel.DateContentWheel
import kg.devcats.compose.jetpack_chili.modals.pickers.wheel.SelectorOptions
import kg.devcats.compose.jetpack_chili.theme.Chili
import java.text.DateFormatSymbols


@Composable
fun ChiliDatePicker(
    isShown: Boolean,
    offset: Int = 1,
    title: String,
    buttonText: String,
    yearsRange: IntRange = IntRange(1900,  Calendar.getInstance()[Calendar.YEAR] + 100),
    startDate: Date = Date(DateUtils.getCurrentTime()),
    selectorEffectEnabled: Boolean = true,
    onDateChanged: (Int, Int, Int, Long, Calendar) -> Unit = { _, _, _, _, _ -> },
    onDismiss: () -> Unit,
    onButtonClick: (Int, Int, Int, Long, Calendar) -> Unit = { _, _, _, _, _ -> },
) {

    var selectedDate by remember { mutableStateOf(startDate) }

    val months = selectedDate.monthsOfDate()

    val days = selectedDate.daysOfDate()

    val years = mutableListOf<Int>().apply {
        for (year in yearsRange) {
            add(year)
        }
    }

    LaunchedEffect(selectedDate) {
        onDateChanged(selectedDate.day, selectedDate.month, selectedDate.year, selectedDate.date, selectedDate.calendar)
    }


    ChiliBottomSheetContainer(
        modifier = Modifier
            .padding(16.dp)
            .navigationBarsPadding(),
        isShown = isShown,
        isCloseIconVisible = false,
        onDismissRequest = onDismiss
    ) {

        val height = 28.dp

        Column {
            Text(
                title,
                modifier = Modifier.padding(8.dp),
                style = Chili.typography.H18_Primary_500
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                key(days.size) {
                    DateContentWheel(modifier = Modifier.weight(1f),
                        itemSize = DpSize(150.dp, height),
                        selection = maxOf(days.indexOf(selectedDate.day), 0),
                        itemCount = days.size,
                        rowOffset = offset,
                        selectorOption = SelectorOptions().copy(
                            selectEffectEnabled = selectorEffectEnabled,
                            enabled = false
                        ),
                        onFocusItem = {
                            selectedDate = selectedDate.withDay(days[it])
                        },
                        content = {
                            Text(
                                text = days[it].toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(50.dp),
                                style = Chili.typography.H16_Primary,
                            )
                        })
                }
                DateContentWheel(modifier = Modifier.weight(2f),
                    itemSize = DpSize(150.dp, height),
                    selection = maxOf(months.indexOf(selectedDate.month), 0),
                    itemCount = months.size,
                    rowOffset = offset,
                    selectorOption = SelectorOptions().copy(
                        selectEffectEnabled = selectorEffectEnabled,
                        enabled = false
                    ),
                    onFocusItem = {
                        selectedDate = selectedDate.withMonth(months[it])
                    },
                    content = {
                        Text(
                            text = DateFormatSymbols().months[months[it]],
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(120.dp).align(Alignment.CenterVertically),
                            style = Chili.typography.H16_Primary,
                        )
                    })


                DateContentWheel(modifier = Modifier.weight(1f),
                    itemSize = DpSize(150.dp, height),
                    selection = years.indexOf(selectedDate.year),
                    itemCount = years.size,
                    rowOffset = offset,
                    selectorOption = SelectorOptions().copy(
                        selectEffectEnabled = selectorEffectEnabled,
                        enabled = false
                    ),
                    onFocusItem = {
                        selectedDate = selectedDate.withYear(years[it])
                    },
                    content = {
                        Text(
                            text = years[it].toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(120.dp),
                            style = Chili.typography.H16_Primary,
                        )
                    })
            }

            ChiliPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = buttonText
            ) {
                onButtonClick(selectedDate.day, selectedDate.month, selectedDate.year, selectedDate.date, selectedDate.calendar)
            }
        }
    }
}


@Preview
@Composable
fun DatePickerPreview() {
    ChiliDatePicker(onDateChanged = { day, month, year, date, _ ->
    }, onDismiss = {}, isShown = true, title = "Select Date", buttonText = "Select")
}