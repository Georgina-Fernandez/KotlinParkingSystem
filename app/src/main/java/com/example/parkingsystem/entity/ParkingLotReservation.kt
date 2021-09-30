package com.example.parkingsystem.entity

import com.example.parkingsystem.util.Constants
import com.example.parkingsystem.util.DateUtils
import com.example.parkingsystem.util.ParkingLotReservationVerify
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ParkingLotReservation {
    lateinit var dateStart: Calendar
    lateinit var timeStart: Calendar
    lateinit var dateEnd: Calendar
    lateinit var timeEnd: Calendar
    var parkingLot: Int = Constants.MINUS_ONE
    var securityCode: Int = Constants.MINUS_ONE

    fun getReservationVerifyResult(): ParkingLotReservationVerify =
        when {
            !::dateStart.isInitialized -> ParkingLotReservationVerify.MISSING_DATE_BEGIN
            getDateAndTimeStart().before(Calendar.getInstance()) -> ParkingLotReservationVerify.EXPIRED_DATE_BEGIN
            !::dateEnd.isInitialized -> ParkingLotReservationVerify.MISSING_DATE_END
            dateEnd.before(dateStart) -> ParkingLotReservationVerify.INVALID_END_DATE
            parkingLot == Constants.MINUS_ONE -> ParkingLotReservationVerify.MISSING_PARKING_LOT
            securityCode == Constants.MINUS_ONE -> ParkingLotReservationVerify.MISSING_CODE
            else -> ParkingLotReservationVerify.VALID_FIELDS
        }

    fun getFormattedString(date: Calendar, time: Calendar): String {
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

    fun getDateAndTimeStart(): Calendar = getCalendarDateAndTime(
        dateStart.get(Calendar.YEAR),
        dateStart.get(Calendar.MONTH),
        dateStart.get(Calendar.DAY_OF_MONTH),
        timeStart.get(Calendar.HOUR_OF_DAY),
        timeStart.get(Calendar.MINUTE)
    )

    fun getDateAndTimeEnd(): Calendar = getCalendarDateAndTime(
        dateEnd.get(Calendar.YEAR),
        dateEnd.get(Calendar.MONTH),
        dateEnd.get(Calendar.DAY_OF_MONTH),
        timeEnd.get(Calendar.HOUR_OF_DAY),
        timeEnd.get(Calendar.MINUTE)
    )

    private fun getCalendarDateAndTime(
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int
    ): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, minute)
        }
    }
}
