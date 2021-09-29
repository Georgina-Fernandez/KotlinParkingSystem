package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.mvp.contract.ReservationListContract

class ReservationListPresenter(
    private val model: ReservationListContract.ReservationListModel,
    private val view: ReservationListContract.ReservationListView
): ReservationListContract.ReservationListPresenter{
    init {
        reservationsList()
    }
    override fun reservationsList() {
        view.reservationsList(model.getReservationsList())
    }
}
