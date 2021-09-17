package com.example.parkingsystem.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.databinding.ActivityParkingReservationBinding
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.mvp.model.ParkingReservationModel
import com.example.parkingsystem.mvp.presenter.ParkingReservationPresenter
import com.example.parkingsystem.mvp.view.ParkingReservationView

class ParkingReservationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityParkingReservationBinding
    private lateinit var presenter: ParkingReservationContract.ParkingReservationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ParkingReservationPresenter(
            ParkingReservationModel(ParkingReservationDatabase),
            ParkingReservationView(this, binding)
        )
        setListeners()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        presenter.onDateSetPressed(year, month, dayOfMonth, this)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        presenter.onTimeSetPressed(hourOfDay, minute)
    }

    private fun setListeners() {
        with(binding) {
            editDateBegin.setOnClickListener { presenter.onButtonParkingReservationPickerPressed(this@ParkingReservationActivity) }
            editDateEnd.setOnClickListener { presenter.onButtonParkingReservationPickerPressed(this@ParkingReservationActivity) }
            buttonParkingReservationSave.setOnClickListener { presenter.onButtonParkingReservationSavePressed() }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, ParkingReservationActivity::class.java)
    }
}
