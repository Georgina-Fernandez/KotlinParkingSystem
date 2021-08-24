package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingDialogContract
import com.example.parkingsystem.util.Constants

class ParkingSizeDialogPresenter(private val view: ParkingDialogContract.ParkingSizeDialogFragmentView) :
    ParkingDialogContract.ParkingSizeDialogFragmentPresenter {

    override fun onButtonDialogFragmentOkPressed(listener: ParkingDialogListener) {
        val parkingSize = view.getParkingLots()
        val parkingSizeInt = parkingSize.toInt()

        if (parkingSize.isNotEmpty() && parkingSizeInt < Constants.PARKING_SIZE_MAX) {
            view.showParkingLots(parkingSizeInt, listener)
        } else {
            view.showErrorToastInput()
        }
    }

    override fun onButtonDialogFragmentCancelPressed() {
        view.closeDialog()
    }
}
