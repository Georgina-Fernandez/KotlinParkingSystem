package com.example.parkingsystem.mvp.contract

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import java.util.Calendar

interface ParkingReservationContract {
    interface ParkingReservationModel {
        fun setDateBeginPressed(isDateBeginPressed: Boolean)
        fun getDateBeginPressed(): Boolean
        fun convertToCalendar(sDate: String, formatGiven: String): Calendar
        fun setDateBegin(dateStart: Calendar)
        fun setTimeBegin(timeStart: Calendar)
        fun setDateEnd(dateEnd: Calendar)
        fun setTimeEnd(timeEnd: Calendar)
        fun getDateBegin(): Calendar
        fun getTimeBegin(): Calendar
        fun getDateEnd(): Calendar
        fun getTimeEnd(): Calendar
        fun getFormattedString(date: Calendar, time: Calendar): String
    }

    interface ParkingReservationPresenter {
        fun onButtonParkingReservationPickerPressed(listener: DatePickerDialog.OnDateSetListener)
        fun onDateSetPressed(year: Int, month: Int, dayOfMonth: Int, timeListener: TimePickerDialog.OnTimeSetListener)
        fun onTimeSetPressed(hourOfDay: Int, minute: Int)
        fun onButtonParkingReservationSavePressed()
    }

    interface ParkingReservationView {
        fun getButtonPickerBegin(): Boolean
        fun showDatePickerDialog(dateListener: DatePickerDialog.OnDateSetListener)
        fun showTimePickerDialog(timeListener: TimePickerDialog.OnTimeSetListener)
        fun showDateAndTimeBegin(dateTime: String)
        fun showDateAndTimeEnd(dateTime: String)
        fun getParkingSpace(): String
        fun getSecurityCode(): String
        fun showOkReservationToast()
        fun enableDateEnd()
    }
}
