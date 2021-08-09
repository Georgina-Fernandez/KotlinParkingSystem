package com.example.parkingsystem.mvp.view

import android.app.Activity
import android.widget.Toast
import com.example.parkingsystem.R
import com.example.parkingsystem.mvp.contract.ParkingContract
import com.example.parkingsystem.mvp.view.base.ActivityView

class ParkingView(activity: Activity) : ParkingContract.View, ActivityView(activity) {
    override fun showParkingSizeMessage(parkingSize: Int) {
        Toast.makeText(
            context,
            context?.getString(R.string.text_parking_size_toast, parkingSize),
            Toast.LENGTH_LONG
        ).show()
    }
}
