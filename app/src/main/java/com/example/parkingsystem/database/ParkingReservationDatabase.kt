package com.example.parkingsystem.database

import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.util.Constants
import java.util.Calendar
import kotlin.collections.HashMap

object ParkingReservationDatabase {
    val hashMapReservation: MutableMap<Int, MutableList<ParkingLotReservation>> = HashMap()
    private var parkingLotSize: Int = Constants.ZERO
    private var expiredReservations: Int = Constants.ZERO

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

    fun clearExpiredReservation() {
        val totalReservations = getAllReservations().size
        hashMapReservation.forEach { (reservation, _) ->
            hashMapReservation[reservation]?.removeAll { parkingReservation ->parkingReservation.dateEnd.before(Calendar.getInstance())}
        }
        setRemovedExpiredReservations(totalReservations - getAllReservations().size)
    }

    private fun getAllReservations(): List<ParkingLotReservation> = hashMapReservation.flatMap { (_, values) -> values }

    fun getRemovedExpiredReservations(): Int = expiredReservations

    private fun setRemovedExpiredReservations(expiredReservations: Int) {
        this.expiredReservations = expiredReservations
    }
}
