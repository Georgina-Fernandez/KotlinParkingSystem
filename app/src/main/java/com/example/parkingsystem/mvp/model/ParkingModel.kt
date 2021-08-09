package com.example.parkingsystem.mvp.model

import com.example.parkingsystem.mvp.contract.ParkingContract

class ParkingModel : ParkingContract.Model {
    private val parkingSize = PARKING_SIZE_DEFAULT
    override fun getParkingSize(): Int = parkingSize

    companion object {
        private const val PARKING_SIZE_DEFAULT = 10
    }
}
