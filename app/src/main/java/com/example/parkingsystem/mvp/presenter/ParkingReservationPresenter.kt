package com.example.parkingsystem.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.util.Constants

class ParkingReservationPresenter(
    private val model: ParkingReservationContract.ParkingReservationModel,
    private val view: ParkingReservationContract.ParkingReservationView
) : ParkingReservationContract.ParkingReservationPresenter {

    override fun onButtonParkingReservationPickerPressed(listener: DatePickerDialog.OnDateSetListener) {
        model.setDateBeginPressed(view.getButtonPickerBegin())
        view.showDatePickerDialog(listener)
    }

    override fun onDateSetPressed(year: Int, month: Int, dayOfMonth: Int, timeListener: TimePickerDialog.OnTimeSetListener) {
        val date = "$dayOfMonth${Constants.SLASH}${month + Constants.ONE}${Constants.SLASH}$year"
        val formattedDate = model.convertToCalendar(date, Constants.FORMAT_DATE)
        if (model.getDateBeginPressed()) {
            model.setDateBegin(formattedDate)
        } else {
            model.setDateEnd(formattedDate)
        }
        view.showTimePickerDialog(timeListener)
    }

    override fun onTimeSetPressed(hourOfDay: Int, minute: Int) {
        val time = "$hourOfDay${Constants.TWO_POINTS}$minute"
        val formattedTime = model.convertToCalendar(time, Constants.FORMAT_TIME)
        if (model.getDateBeginPressed()) {
            model.setTimeBegin(formattedTime)
            view.enableDateEnd()
            view.showDateAndTimeBegin(
                model.getFormattedString(model.getDateBegin(), model.getTimeBegin())
            )
        } else {
            model.setTimeEnd(formattedTime)
            view.showDateAndTimeEnd(
                model.getFormattedString(model.getDateEnd(), model.getTimeEnd())
            )
        }
    }

    override fun onButtonParkingReservationSavePressed() {
        view.showOkReservationToast()
    }
}
