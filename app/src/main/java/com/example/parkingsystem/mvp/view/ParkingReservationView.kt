package com.example.parkingsystem.mvp.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.ActivityParkingReservationBinding
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.mvp.view.base.ActivityView
import com.example.parkingsystem.util.showToast
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

    override fun getParkingSpace(): String = binding.editTextSpace.text.toString()

    override fun getSecurityCode(): String = binding.editTextPassword.text.toString()

    override fun showOkReservationToast() {
        context?.let {
            it.showToast(it.getString(R.string.toast_parking_reservation_succeed))
        }
    }

    override fun enableDateEnd() {
        binding.editDateEnd.isEnabled = true
    }
}
