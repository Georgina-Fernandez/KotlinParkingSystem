package com.example.parkingsystem.mvp.view

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.ParkingSizeDialogBinding
import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingDialogContract
import com.example.parkingsystem.mvp.view.base.DialogFragmentView
import com.example.parkingsystem.parkingAlertDialog.ParkingCustomAlertDialog
import com.example.parkingsystem.util.Constants

class ParkingSizeDialogView(fragment: Fragment, private val binding: ParkingSizeDialogBinding) :
    DialogFragmentView(fragment), ParkingDialogContract.ParkingSizeDialogFragmentView {

    init {
        binding.textParkingSizeDialogFragment.text =
            context?.getString(R.string.text_parking_size_dialog, Constants.PARKING_SIZE_MAX)
    }

    override fun getParkingLots(): String = binding.inputParkingSize.text.toString()

    override fun showParkingLots(parkingLots: Int, listener: ParkingDialogListener) {
        listener.onFragmentDialogOkClick(parkingLots)
        closeDialog()
    }

    override fun clearInput() {
        binding.inputParkingSize.text.clear()
    }

    override fun closeDialog() {
        fragment?.let {
            (it as DialogFragment).dismiss()
        }
    }

    override fun showErrorToastInput() {
        clearInput()
        context?.let {
                ParkingCustomAlertDialog(it).showAlertDialog(
                    it,
                    it.getString(R.string.title_validation_warning),
                    it.getString(R.string.text_parking_dialog_error_input)
                )
        }
    }
}
