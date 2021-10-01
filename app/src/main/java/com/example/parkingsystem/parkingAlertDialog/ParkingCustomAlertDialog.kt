package com.example.parkingsystem.parkingAlertDialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.parkingsystem.R

class ParkingCustomAlertDialog(context: Context?) : AlertDialog(context) {
    fun showAlertDialog(context: Context, title: CharSequence, message: CharSequence, finish: (() -> Unit)? = null) {
        with(Builder(context)) {
            setTitle(title)
            setMessage(message)
            setNegativeButton(R.string.button_parking_size_dialog_ok) { _: DialogInterface, _: Int -> finish?.invoke() }
            create()
            show()
        }
    }
}
