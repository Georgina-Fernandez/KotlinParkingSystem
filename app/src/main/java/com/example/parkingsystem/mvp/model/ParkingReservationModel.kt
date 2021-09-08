package com.example.parkingsystem.mvp.model

import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.util.Constants
import com.example.parkingsystem.util.DateUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ParkingReservationModel : ParkingReservationContract.ParkingReservationModel {

    private var isDateBeginPressed = false
    private lateinit var dateBegin: Calendar
    private lateinit var timeBegin: Calendar
    private lateinit var dateEnd: Calendar
    private lateinit var timeEnd: Calendar

    override fun setDateBeginPressed(isDateBeginPressed: Boolean) {
        this.isDateBeginPressed = isDateBeginPressed
    }

    override fun getDateBeginPressed(): Boolean = isDateBeginPressed

    override fun convertToCalendar(date: String, formatGiven: String): Calendar {
        return DateUtils.convertToCalendar(date, SimpleDateFormat(formatGiven, Locale.getDefault()))
    }

    override fun getDateBegin(): Calendar = dateBegin

    override fun getTimeBegin(): Calendar = timeBegin

    override fun getDateEnd(): Calendar = dateEnd

    override fun getTimeEnd(): Calendar = timeEnd

    override fun setDateBegin(dateBegin: Calendar) {
        this.dateBegin = dateBegin
    }

    override fun setTimeBegin(timeBegin: Calendar) {
        this.timeBegin = timeBegin
    }

    override fun setDateEnd(dateEnd: Calendar) {
        this.dateEnd = dateEnd
    }

    override fun setTimeEnd(timeEnd: Calendar) {
        this.timeEnd = timeEnd
    }

    override fun getFormattedString(date: Calendar, time: Calendar): String {
        val dateTime = Calendar.getInstance()
        dateTime.set(
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH),
            time.get(Calendar.HOUR),
            time.get(Calendar.MINUTE)
        )
        return DateUtils.convertToString(dateTime, SimpleDateFormat(Constants.FORMAT_DATE_TIME, Locale.getDefault()))
    }
}
