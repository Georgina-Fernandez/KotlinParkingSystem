package com.example.parkingsystem.mvp.contract

import com.example.parkingsystem.listener.ParkingDialogListener

interface ParkingContract {
    interface Model {
        fun getParkingSize(): Int
        fun setParkingSize(parkingLots: Int)
    }

    interface Presenter {
        fun onParkingSizeButtonPressed(listener: ParkingDialogListener)
        fun onParkingSizeOkButtonPressed(parkingLots: Int)
    }

    interface View {
        fun showParkingSizeDialog(listener: ParkingDialogListener)
        fun showParkingLots(parkingSize: Int)
    }
}
