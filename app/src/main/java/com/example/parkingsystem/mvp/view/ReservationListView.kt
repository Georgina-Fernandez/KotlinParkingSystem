package com.example.parkingsystem.mvp.view

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingsystem.adapter.ReservationListAdapter
import com.example.parkingsystem.databinding.ActivityReservationListBinding
import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.mvp.contract.ReservationListContract
import com.example.parkingsystem.mvp.view.base.ActivityView

class ReservationListView(activity: Activity, private val binding: ActivityReservationListBinding) : ActivityView(activity),
    ReservationListContract.ReservationListView {
    override fun reservationsList(reservations: List<ParkingLotReservation>) {
        with(binding.recyclerViewReservationList) {
            adapter = ReservationListAdapter(reservations)
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
