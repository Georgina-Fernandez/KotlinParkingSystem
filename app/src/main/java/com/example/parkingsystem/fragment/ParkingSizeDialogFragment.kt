package com.example.parkingsystem.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.parkingsystem.databinding.ParkingSizeDialogBinding
import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingDialogContract
import com.example.parkingsystem.mvp.presenter.ParkingSizeDialogPresenter
import com.example.parkingsystem.mvp.view.ParkingSizeDialogView

class ParkingSizeDialogFragment : DialogFragment() {
    private lateinit var binding: ParkingSizeDialogBinding
    private lateinit var presenter: ParkingDialogContract.ParkingSizeDialogFragmentPresenter
    private lateinit var listener: ParkingDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ParkingSizeDialogBinding.inflate(layoutInflater)
        setListeners()
        listener = arguments?.getSerializable(LISTENER_KEY) as ParkingDialogListener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ParkingSizeDialogPresenter(ParkingSizeDialogView(this, binding))
    }

    private fun setListeners() {
        binding.buttonDialogFragmentOk.setOnClickListener {
            presenter.onButtonDialogFragmentOkPressed(
                listener
            )
        }
        binding.buttonDialogFragmentCancel.setOnClickListener { presenter.onButtonDialogFragmentCancelPressed() }
    }

    companion object {
        private const val LISTENER_KEY = "LISTENER_KEY"

        fun newInstance(listener: ParkingDialogListener) = ParkingSizeDialogFragment().apply {
            this.arguments = Bundle().apply {
                putSerializable(LISTENER_KEY, listener)
            }
        }
    }
}
