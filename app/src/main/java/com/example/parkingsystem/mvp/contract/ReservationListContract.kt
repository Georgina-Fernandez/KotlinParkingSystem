package com.example.parkingsystem.mvp.contract

import com.example.parkingsystem.entity.ParkingLotReservation

interface ReservationListContract {

    interface ReservationListPresenter {
        fun getReservationsList()
    }

    interface ReservationListModel {
        fun getReservationsList(): List<ParkingLotReservation>
    }

    interface ReservationListView {
        fun showReservationsList(reservations: List<ParkingLotReservation>)
    }
}
