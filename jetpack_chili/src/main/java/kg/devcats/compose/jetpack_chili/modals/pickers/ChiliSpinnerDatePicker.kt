package kg.devcats.compose.jetpack_chili.modals.pickers


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili
import java.util.Calendar

@Composable
fun ChiliSpinnerDatePicker(modifier: Modifier = Modifier) {

}

@Composable
fun SpinnerDatePicker(
    header: String,
    confirmButtonText: String,
    startDate: Calendar? = null,
    endDate: Calendar? = null,
    currentDate: Calendar = Calendar.getInstance(),
    onDateChanged: (Calendar) -> Unit,
    onConfirmClick: (date: Calendar) -> Unit
) {


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = header, style = Chili.typography.H16_Primary)
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {


            ChiliSpinnerView(
                modifier = Modifier.width(55.dp),
                items = (1 .. 30).map { it.toString() },
                currentIndex = 0
            ) { selectedIndex ->
                println("On item selected $selectedIndex")

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ChiliPrimaryButton(text = confirmButtonText, modifier = Modifier.fillMaxWidth()) {
//            onConfirmClick(selectedDateIndex, selectedMonthIndex, selectedYearIndex)
        }
    }
}




@Preview
@Composable
fun PreviewChiliSpinnerDatePicker() {
    SpinnerDatePicker(
        header = "Начало истории",
        confirmButtonText = "Применить",
        onDateChanged = { Calendar.getInstance() }
    ) {}
}
