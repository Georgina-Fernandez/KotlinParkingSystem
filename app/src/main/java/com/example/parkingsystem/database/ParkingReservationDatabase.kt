package com.example.parkingsystem.database

import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.util.Constants

object ParkingReservationDatabase {
    val hashMapReservation: MutableMap<Int, MutableList<ParkingLotReservation>> = HashMap()
    private var parkingLotSize: Int = Constants.ZERO

    fun addReservation(reservation: ParkingLotReservation) {
        var reservations = mutableListOf<ParkingLotReservation>()
        val parkingLot = reservation.parkingLot
        if (hashMapReservation.containsKey(parkingLot)) {
            hashMapReservation[parkingLot]?.let {
                reservations = it
            }
        }
        with(reservations) {
            this.add(reservation)
            hashMapReservation.put(parkingLot, this)
        }
    }

    fun getParkingLotSize(): Int = parkingLotSize

    fun setParkingLotSize(parkingSize: Int) {
        this.parkingLotSize = parkingSize
    }
}

