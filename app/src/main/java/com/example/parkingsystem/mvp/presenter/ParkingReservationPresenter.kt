package com.example.parkingsystem.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.util.Constants
import com.example.parkingsystem.util.ParkingLotReservationVerify

class ParkingReservationPresenter(
    private val model: ParkingReservationContract.ParkingReservationModel,
    private val view: ParkingReservationContract.ParkingReservationView
) : ParkingReservationContract.ParkingReservationPresenter {

    override fun onButtonParkingReservationPickerPressed(listener: DatePickerDialog.OnDateSetListener) {
        model.setDateBeginPressed(view.getButtonPickerBegin())
        view.showDatePickerDialog(listener)
    }

    override fun onDateSetPressed(year: Int, month: Int, dayOfMonth: Int, timeListener: TimePickerDialog.OnTimeSetListener) {
        val formattedDate =
            model.convertToCalendar("$dayOfMonth${Constants.SLASH}${month + Constants.ONE}${Constants.SLASH}$year", Constants.FORMAT_DATE)
        if (model.getDateBeginPressed()) {
            model.setDateBegin(formattedDate)
        } else {
            model.setDateEnd(formattedDate)
        }
        view.showTimePickerDialog(timeListener)
    }

    override fun onTimeSetPressed(hourOfDay: Int, minute: Int) {
        val formattedTime = model.convertToCalendar("$hourOfDay${Constants.TWO_POINTS}$minute", Constants.FORMAT_TIME)
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
        model.completeReservationInfo(
            (if (view.getParkingLot().isNotEmpty()) view.getParkingLot().toInt() else Constants.MINUS_ONE),
            (if (view.getSecurityCode().isNotEmpty()) view.getSecurityCode().toInt() else Constants.MINUS_ONE),
        )
        when (model.getReservationVerifyResult()) {
            ParkingLotReservationVerify.MISSING_PARKING_LOT -> view.showMissingParkingSpace()
            ParkingLotReservationVerify.MISSING_DATE_BEGIN -> view.showMissingDateTimeStart()
            ParkingLotReservationVerify.MISSING_DATE_END -> view.showMissingDateTimeEnd()
            ParkingLotReservationVerify.INVALID_END_DATE -> view.showInvalidDateTimeEnd()
            ParkingLotReservationVerify.MISSING_CODE -> view.showMissingSecurityCode()
            ParkingLotReservationVerify.VALID_FIELDS -> {
                when {
                    model.getValidReservation() == ParkingLotReservationVerify.OVERLAP_RESERVATION -> view.showReservationOverlapping()
                    model.parkingLotSizeValidate() == ParkingLotReservationVerify.OUT_OF_BOUND_LOT -> view.showOutOfBoundParkingSpace(model.getParkingLotSize())
                    else -> {
                        model.makeReservation(model.getReservation())
                        view.showOkReservation()
                    }
                }
            }
        }
    }
}
