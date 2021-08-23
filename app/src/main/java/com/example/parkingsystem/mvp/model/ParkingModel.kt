package com.example.parkingsystem.mvp.model

import com.example.parkingsystem.mvp.contract.ParkingContract

class ParkingModel : ParkingContract.Model {

    private var parkingSize: Int = PARKING_SIZE_DEFAULT

    override fun getParkingSize(): Int = parkingSize

    override fun setParkingSize(parkingSize: Int) {
        this.parkingSize = parkingSize
    }

    companion object {
        private const val PARKING_SIZE_DEFAULT = 10
    }
}
