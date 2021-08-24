package com.example.parkingsystem.mvp.contract

import com.example.parkingsystem.listener.ParkingDialogListener

interface ParkingDialogContract {
    interface ParkingSizeDialogFragmentPresenter {
        fun onButtonDialogFragmentOkPressed(listener: ParkingDialogListener)
        fun onButtonDialogFragmentCancelPressed()
    }

    interface ParkingSizeDialogFragmentView {
        fun getParkingLots(): String
        fun showParkingLots(parkingLots: Int, listener: ParkingDialogListener)
        fun closeDialog()
        fun clearInput()
        fun showErrorToastInput()
    }
}
