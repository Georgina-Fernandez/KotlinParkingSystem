package com.example.parkingsystem.mvp.model

import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.util.DateUtils
import com.example.parkingsystem.util.ParkingLotReservationValidated
import com.example.parkingsystem.util.ParkingLotReservationVerify
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ParkingReservationModel(private val database: ParkingReservationDatabase) :
    ParkingReservationContract.ParkingReservationModel {

    private var isDateBeginPressed = false
    private val reservation = ParkingLotReservation()

    override fun setDateBeginPressed(isDateBeginPressed: Boolean) {
        this.isDateBeginPressed = isDateBeginPressed
    }

    override fun getDateBeginPressed(): Boolean = isDateBeginPressed

    override fun convertToCalendar(date: String, formatGiven: String): Calendar {
        return DateUtils.convertToCalendar(date, SimpleDateFormat(formatGiven, Locale.getDefault()))
    }

    override fun setDateBegin(dateBegin: Calendar) {
        reservation.dateStart = dateBegin
    }

    override fun setTimeBegin(timeBegin: Calendar) {
        reservation.timeStart = timeBegin
    }

    override fun setDateEnd(dateEnd: Calendar) {
        reservation.dateEnd = dateEnd
    }

    override fun setTimeEnd(timeEnd: Calendar) {
        reservation.timeEnd = timeEnd
    }

    override fun getDateBegin(): Calendar = reservation.dateStart

    override fun getTimeBegin(): Calendar = reservation.timeStart

    override fun getDateEnd(): Calendar = reservation.dateEnd

    override fun getTimeEnd(): Calendar = reservation.timeEnd

    override fun getParkingLot(): Int = reservation.parkingLot

    override fun getSecurityCode(): Int = reservation.securityCode

    override fun getFormattedString(date: Calendar, time: Calendar): String = reservation.getFormattedString(date, time)

    override fun getParkingLotSize() = ParkingReservationDatabase.getParkingLotSize()

    override fun getReservation(): ParkingLotReservation = reservation

    override fun completeReservationInfo(parkingLot: Int, securityCode: Int) {
        reservation.parkingLot = parkingLot
        reservation.securityCode = securityCode
    }

    override fun getReservationVerifyResult(): ParkingLotReservationVerify =
        reservation.getReservationVerifyResult()

    override fun getValidReservation(): ParkingLotReservationVerify =
        if (ParkingLotReservationValidated(database).canBeReserved(reservation)) {
            ParkingLotReservationVerify.SUCCESS
        } else {
            ParkingLotReservationVerify.OVERLAP_RESERVATION
        }

    override fun parkingLotSizeValidate(): ParkingLotReservationVerify = if (getParkingLotSize() < reservation.parkingLot) {
        ParkingLotReservationVerify.OUT_OF_BOUND_LOT
    } else {
        ParkingLotReservationVerify.SUCCESS
    }

    override fun makeReservation(reservation: ParkingLotReservation) {
        ParkingReservationDatabase.addReservation(reservation)
    }
}
