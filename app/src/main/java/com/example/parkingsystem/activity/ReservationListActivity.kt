package com.example.parkingsystem.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.databinding.ActivityReservationListBinding
import com.example.parkingsystem.mvp.contract.ReservationListContract
import com.example.parkingsystem.mvp.model.ReservationListModel
import com.example.parkingsystem.mvp.presenter.ReservationListPresenter
import com.example.parkingsystem.mvp.view.ReservationListView

class ReservationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationListBinding
    private lateinit var presenter: ReservationListContract.ReservationListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ReservationListPresenter(ReservationListModel(ParkingReservationDatabase), ReservationListView(this, binding))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, ReservationListActivity::class.java)
    }
}
