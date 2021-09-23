package com.example.parkingsystem.util

import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.entity.ParkingLotReservation

class ParkingLotReservationValidated(var database: ParkingReservationDatabase) {

    fun canBeReserved(reservation: ParkingLotReservation): Boolean {
        val parkingLot = reservation.parkingLot
        return if (database.hashMapReservation.containsKey(parkingLot)) {
            database.hashMapReservation[parkingLot]?.none { isOverlap(reservation, it) } == true
        } else {
            true
        }
    }

    private fun isOverlap(reservation: ParkingLotReservation, storedReservation: ParkingLotReservation): Boolean {
        val reservationStart = reservation.getDateAndTimeStart()
        val reservationEnd = reservation.getDateAndTimeEnd()
        val storedReservationStart = storedReservation.getDateAndTimeStart()
        val storedReservationEnd = storedReservation.getDateAndTimeEnd()
        return when {
            reservationStart.before(storedReservationStart) && reservationEnd.after(storedReservationStart) -> true
            reservationStart.before(storedReservationEnd) && reservationEnd.after(storedReservationEnd) -> true
            else -> reservationStart.after(storedReservationStart) && reservationEnd.before(storedReservationEnd)
        }
    }
}
