package com.example.parkingsystem.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object DateUtils {

    fun convertToCalendar(sDate: String, format: SimpleDateFormat): Calendar {
        var date = Date()
        val calendar = Calendar.getInstance()
        try {
            date = format.parse(sDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        calendar.time = date
        return calendar
    }

    fun convertToString(calendar: Calendar, format: SimpleDateFormat): String = format.format(calendar.time)
}
