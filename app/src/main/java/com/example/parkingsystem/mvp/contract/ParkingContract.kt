package com.example.parkingsystem.mvp.contract

import com.example.parkingsystem.listener.ParkingDialogListener

interface ParkingContract {
    interface Model {
        fun getParkingSize(): Int
        fun setParkingSize(parkingLots: Int)
        fun clearExpiredReservation()
        fun getRemovedExpiredReservations(): Int
    }

    interface Presenter {
        fun onParkingSizeButtonPressed(listener: ParkingDialogListener)
        fun onParkingSizeOkButtonPressed(parkingLots: Int)
        fun onParkingReservationButtonPressed()
        fun onClearExpiredReservationPressed()
        fun onReservationListPressed()
    }

    interface View {
        fun showParkingSizeDialog(listener: ParkingDialogListener)
        fun showParkingLots(parkingSize: Int)
        fun showParkingReservation()
        fun showClearExpiredReservationToast(expiredReservations: Int)
        fun showReservationList()
    }
}
