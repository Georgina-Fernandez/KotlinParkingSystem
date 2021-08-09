package com.example.parkingsystem.mvp.contract

interface ParkingContract {
    interface Model {
        fun getParkingSize(): Int
    }

    interface Presenter {
        fun onParkingSizeButtonPressed()
    }

    interface View {
        fun showParkingSizeMessage(parkingSize: Int)
    }
}
