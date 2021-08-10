package com.example.parkingsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystem.databinding.ActivityMainBinding
import com.example.parkingsystem.mvp.contract.ParkingContract
import com.example.parkingsystem.mvp.model.ParkingModel
import com.example.parkingsystem.mvp.presenter.ParkingPresenter
import com.example.parkingsystem.mvp.view.ParkingView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: ParkingContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ParkingPresenter(ParkingModel(), ParkingView(this))
        setListener()
    }
    private fun setListener() {
        binding.parkingSizeButton.setOnClickListener {
            presenter.onParkingSizeButtonPressed()
        }
    }
}
