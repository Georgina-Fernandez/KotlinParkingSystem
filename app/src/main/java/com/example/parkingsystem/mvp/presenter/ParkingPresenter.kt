package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.mvp.contract.ParkingContract

class ParkingPresenter(
    private val model: ParkingContract.Model,
    private val view: ParkingContract.View
) : ParkingContract.Presenter {
    override fun onParkingSizeButtonPressed() {
        view.showParkingSizeMessage(model.getParkingSize())
    }
}
