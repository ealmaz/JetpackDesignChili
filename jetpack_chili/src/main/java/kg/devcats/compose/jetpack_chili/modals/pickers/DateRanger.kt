package kg.devcats.compose.jetpack_chili.modals.pickers

import java.util.Calendar

object DateRanger {


    fun getRages(selected: Calendar, startLimit: Calendar? = null, endLimit: Calendar? = null): DateRanges {

        val current = when {
            endLimit != null && selected > endLimit -> endLimit
            startLimit != null && selected < startLimit -> startLimit
            else -> selected
        }


        val yearRange = years.filter {  it >= startLimit.getYear() && it <= endLimit.getYear() }


        val monthRange: List<Int> = when {
            current.getYear() == endLimit.getYear() && current.getYear() == startLimit.getYear()   -> months.filter { it >= startLimit.getMonth() && it <= endLimit.getMonth() }
            current.getYear() == startLimit.getYear() -> months.filter { it >= startLimit.getMonth() }
            current.getYear() == endLimit.getYear() -> months.filter { it <= endLimit.getMonth() }
            else -> months.toList()
        }

        val currentMonthMaxDays = current.getActualMaximum(Calendar.DAY_OF_MONTH)

        val daysRange: List<Int> = when {
            current.getYear() == endLimit.getYear() && current.getYear() == startLimit.getYear()
                    && current.getMonth() == endLimit.getMonth() && current.getMonth() == startLimit.getMonth() -> days.filter { it >= startLimit.getDay() && it <= endLimit.getDay() }

            current.getYear() == endLimit.getYear() && current.getYear() == startLimit.getYear()
                    && current.getMonth() == endLimit.getMonth() && current.getMonth() != startLimit.getMonth() -> days.filter { it <= endLimit.getDay() }

            current.getYear() == endLimit.getYear() && current.getYear() == startLimit.getYear()
                    && current.getMonth() != endLimit.getMonth() && current.getMonth() == startLimit.getMonth() -> days.filter { it >= startLimit.getDay() }


            current.getYear() == startLimit.getYear() && current.getMonth() == startLimit.getMonth() -> days.filter { it >= startLimit.getDay() }

            current.getYear() == endLimit.getYear() && current.getMonth() == endLimit.getMonth()  -> days.filter { it <= endLimit.getDay() }

            else -> days
        }.filter { it <= currentMonthMaxDays }


        return DateRanges(
            days = daysRange,
            months = monthRange,
            years = yearRange,
            current = current
        )
    }






}

fun Calendar?.getDay(): Int {
    return this?.get(Calendar.DAY_OF_MONTH)?.plus(1) ?: -1
}

fun Calendar?.getYear(): Int {
    return this?.get(Calendar.YEAR) ?: -1
}

fun Calendar?.getMonth(): Int {
    return this?.get(Calendar.MONTH) ?: -1
}

data class DateRanges(
    val days: List<Int> = listOf(),
    val months: List<Int> = listOf(),
    val years: List<Int> = listOf(),
    val current: Calendar
)

val years = (1950..2050)
val days = (1..31)
val months = (1..12)