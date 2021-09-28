package com.example.parkingsystem.mvp.contract

import com.example.parkingsystem.entity.ParkingLotReservation

interface ReservationListContract {

    interface ReservationListPresenter {
        fun reservationsList()
    }

    interface ReservationListModel {
        fun getReservationsList(): List<ParkingLotReservation>
    }

    interface ReservationListView {
        fun reservationsList(reservations: List<ParkingLotReservation>)
    }
}
