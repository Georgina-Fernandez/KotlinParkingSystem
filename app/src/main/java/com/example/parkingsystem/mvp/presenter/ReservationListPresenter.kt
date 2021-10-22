package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.mvp.contract.ReservationListContract

class ReservationListPresenter(
    private val model: ReservationListContract.ReservationListModel,
    private val view: ReservationListContract.ReservationListView
): ReservationListContract.ReservationListPresenter{

    override fun getReservationsList() {
        view.showReservationsList(model.getReservationsList())
    }
}
