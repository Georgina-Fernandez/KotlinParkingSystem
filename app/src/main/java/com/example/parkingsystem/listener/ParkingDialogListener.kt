package com.example.parkingsystem.listener

import java.io.Serializable

interface ParkingDialogListener : Serializable {
    fun onFragmentDialogOkClick(parkingLots: Int)
}
