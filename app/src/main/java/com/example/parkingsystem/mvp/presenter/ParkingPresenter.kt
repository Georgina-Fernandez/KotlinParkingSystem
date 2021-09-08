package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingContract

class ParkingPresenter(
    private val model: ParkingContract.Model,
    private val view: ParkingContract.View
) : ParkingContract.Presenter {

    override fun onParkingSizeButtonPressed(listener: ParkingDialogListener) {
        view.showParkingSizeDialog(listener)
    }

    override fun onParkingSizeOkButtonPressed(parkingLots: Int) {
        model.setParkingSize(parkingLots)
        view.showParkingLots(model.getParkingSize())
    }

    override fun onParkingReservationButtonPressed() {
        view.showParkingReservation()
    }
}
