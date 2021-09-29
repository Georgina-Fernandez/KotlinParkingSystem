package com.example.parkingsystem.mvp.view

import android.app.Activity
import com.example.parkingsystem.R
import com.example.parkingsystem.activity.ParkingReservationActivity
import com.example.parkingsystem.activity.ReservationListActivity
import com.example.parkingsystem.fragment.ParkingSizeDialogFragment
import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingContract
import com.example.parkingsystem.mvp.view.base.ActivityView
import com.example.parkingsystem.util.showToast

class ParkingView(activity: Activity) : ParkingContract.View, ActivityView(activity) {

    override fun showParkingSizeDialog(listener: ParkingDialogListener) {
        ParkingSizeDialogFragment.newInstance(listener)
            .show(fragmentManager, TAG_PARKING_DIALOG_VIEW)
    }

    override fun showParkingLots(parkingSize: Int) {
        context?.let {
            it.showToast(it.getString(R.string.text_parking_size_toast, parkingSize))
        }
    }

    override fun showParkingReservation() {
        activity?.startActivity(context?.let { ParkingReservationActivity.getIntent(it) })
    }

    override fun showClearExpiredReservationToast(expiredReservations: Int) {
        context?.let {
            it.showToast(it.getString(R.string.toast_main_activity_clear_expired_reservation, expiredReservations))
        }
    }

    override fun showReservationList() {
        activity?.startActivity(context?.let{(ReservationListActivity.getIntent(it))})
    }


    companion object {
        const val TAG_PARKING_DIALOG_VIEW = "TAG_PARKING_DIALOG_VIEW"
    }
}
