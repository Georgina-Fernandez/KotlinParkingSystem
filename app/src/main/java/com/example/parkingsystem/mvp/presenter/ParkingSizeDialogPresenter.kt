package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingDialogContract
import com.example.parkingsystem.util.Constants
import com.example.parkingsystem.util.ParkingLotReservationVerify

class ParkingSizeDialogPresenter(private val view: ParkingDialogContract.ParkingSizeDialogFragmentView) :
    ParkingDialogContract.ParkingSizeDialogFragmentPresenter {

    override fun onButtonDialogFragmentOkPressed(listener: ParkingDialogListener) {
        val parkingSize = view.getParkingLots()

        if (parkingSize.isNotEmpty() && parkingSize.toInt() < Constants.PARKING_SIZE_MAX && parkingSize.toInt() > Constants.ZERO) {
            view.showParkingLots(parkingSize.toInt(), listener)
        } else {
            view.showErrorToastInput()
        }
    }

    override fun onButtonDialogFragmentCancelPressed() {
        view.closeDialog()
    }
}


