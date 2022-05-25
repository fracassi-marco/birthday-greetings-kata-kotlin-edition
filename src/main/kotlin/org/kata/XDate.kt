package org.kata

import java.text.SimpleDateFormat
import java.util.*

data class XDate(private var date: Date = Date()) {

    constructor(yyyyMMdd: String) : this(SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd))

    val day: Int get() = getPartOfDate(GregorianCalendar.DAY_OF_MONTH)
    val month: Int get() = 1 + getPartOfDate(GregorianCalendar.MONTH)

    fun isSameDay(anotherDate: XDate): Boolean {
        return anotherDate.day == day && anotherDate.month == month
    }

    private fun getPartOfDate(part: Int): Int {
        val calendar = GregorianCalendar()
        calendar.time = date
        return calendar[part]
    }
}