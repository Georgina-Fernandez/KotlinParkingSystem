package com.example.parkingsystem.mvp.model

import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.mvp.contract.ParkingContract

class ParkingModel(private val database: ParkingReservationDatabase) : ParkingContract.Model {

    override fun getParkingSize(): Int = this.database.getParkingLotSize()

    override fun setParkingSize(parkingLotSize: Int) {
        this.database.setParkingLotSize(parkingLotSize)
    }
}
