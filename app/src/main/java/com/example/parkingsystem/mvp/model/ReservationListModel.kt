package com.example.parkingsystem.mvp.model

import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.mvp.contract.ReservationListContract

class ReservationListModel(private val database: ParkingReservationDatabase): ReservationListContract.ReservationListModel {
    override fun getReservationsList(): List<ParkingLotReservation> = database.getAllReservations()
}
