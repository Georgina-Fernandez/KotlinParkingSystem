package com.example.parkingsystem.mvp.contract

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.util.Constants
import com.example.parkingsystem.util.ParkingLotReservationVerify
import java.util.Calendar

interface ParkingReservationContract {
    interface ParkingReservationModel {
        fun setDateBeginPressed(isDateBeginPressed: Boolean)
        fun getDateBeginPressed(): Boolean
        fun convertToCalendar(date: String, formatGiven: String): Calendar
        fun setDateBegin(dateBegin: Calendar)
        fun setTimeBegin(timeBegin: Calendar)
        fun setDateEnd(dateEnd: Calendar)
        fun setTimeEnd(timeEnd: Calendar)
        fun getDateBegin(): Calendar
        fun getTimeBegin(): Calendar
        fun getDateEnd(): Calendar
        fun getTimeEnd(): Calendar
        fun getFormattedString(formattedDate: Calendar, time: Calendar): String
        fun getReservation(): ParkingLotReservation
        fun completeReservationInfo(parkingLot: Int= Constants.MINUS_ONE, securityCode: Int)
        fun getReservationVerifyResult(): ParkingLotReservationVerify
        fun getValidReservation(): ParkingLotReservationVerify
        fun makeReservation(reservation: ParkingLotReservation)
        fun getParkingLotSize(): Int
        fun parkingLotSizeValidate(): ParkingLotReservationVerify
        fun getParkingLot(): Int
        fun getSecurityCode(): Int
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
        fun getParkingLot(): String
        fun getSecurityCode(): String
        fun enableDateEnd()
        fun showMissingDateTimeStart()
        fun showMissingDateTimeEnd()
        fun showMissingParkingSpace()
        fun showOutOfBoundParkingSpace(parkingLot: Int)
        fun showMissingSecurityCode()
        fun showReservationOverlapping()
        fun showOkReservation()
        fun showInvalidDateTimeEnd()
    }
}
