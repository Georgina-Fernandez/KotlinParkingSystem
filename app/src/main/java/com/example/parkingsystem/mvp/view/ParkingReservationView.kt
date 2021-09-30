package com.example.parkingsystem.mvp.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.ActivityParkingReservationBinding
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.mvp.view.base.ActivityView
import com.example.parkingsystem.parkingAlertDialog.ParkingCustomAlertDialog
import java.util.Calendar.getInstance

class ParkingReservationView(activity: Activity, private val binding: ActivityParkingReservationBinding) :
    ParkingReservationContract.ParkingReservationView, ActivityView(activity) {

    override fun getButtonPickerBegin(): Boolean = binding.editDateBegin.isPressed

    override fun showDatePickerDialog(dateListener: DatePickerDialog.OnDateSetListener) {
        val calendar = getInstance()
        context?.let {
            DatePickerDialog(
                it,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun showTimePickerDialog(timeListener: TimePickerDialog.OnTimeSetListener) {
        val calendar = getInstance()
        context?.let {
            TimePickerDialog(it, timeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show()
        }
    }

    override fun showDateAndTimeBegin(dateTime: String) {
        binding.editDateBegin.setText(dateTime)
    }

    override fun showDateAndTimeEnd(dateTime: String) {
        binding.editDateEnd.setText(dateTime)
    }

    override fun getParkingLot(): String = binding.editTextSpace.text.toString()

    override fun getSecurityCode(): String = binding.editTextPassword.text.toString()

    override fun enableDateEnd() {
        binding.editDateEnd.isEnabled = true
    }

    override fun showMissingDateTimeStart() {
        context?.let {
                ParkingCustomAlertDialog(it).showAlertDialog(
                    it,
                    it.getString(R.string.title_validation_warning),
                    it.getString(R.string.text_parking_reservation_day_time_start_missing)
                )
        }
    }

    override fun showExpiredDateTimeStart() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_reservation_day_time_start_expired)
            )
        }
    }

    override fun showMissingDateTimeEnd() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_reservation_day_time_end_missing)
            )
        }
    }

    override fun showInvalidDateTimeEnd() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_reservation_day_time_end_invalid)
            )
        }
    }

    override fun showMissingParkingSpace() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_reservation_space_missing)
            )
        }
    }

    override fun showOutOfBoundParkingSpace(parkingLot: Int) {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_reservation_space_out_of_bound, parkingLot)
            )
        }
    }

    override fun showMissingSecurityCode() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_reservation_code_missing)
            )
        }
    }

    override fun showReservationOverlapping() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_warning),
                it.getString(R.string.text_parking_space_reservation_reservation_overlapping)
            )
        }
    }

    override fun showOkReservation() {
        context?.let {
            ParkingCustomAlertDialog(it).showAlertDialog(
                it,
                it.getString(R.string.title_validation_ok),
                it.getString(R.string.text_parking_reservation_succeed)
            ) { activity?.finish() }
        }
    }
}
