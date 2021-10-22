package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingContract
import com.example.parkingsystem.mvp.model.ParkingModel
import com.example.parkingsystem.util.Constants
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParkingPresenterTest {
    private lateinit var presenter: ParkingContract.Presenter
    private lateinit var model: ParkingContract.Model
    private var view: ParkingContract.View = mock()
    private var listener: ParkingDialogListener = mock()
    private var database: ParkingReservationDatabase= ParkingReservationDatabase

    @Before
    fun setup() {
        model = ParkingModel(database)
        presenter = ParkingPresenter(model, view)
    }

    @Test
    fun `on parking size button pressed, then shows dialog`() {
        presenter.onParkingSizeButtonPressed(listener)

        verify(view).showParkingSizeDialog(listener)
    }

    @Test
    fun `on reservation list button pressed, shows the reservations list activity`() {
        presenter.onReservationListPressed()

        verify(view).openReservationList()
    }

    @Test
    fun `on parking reservation button pressed, shows the reservation activity`(){
        presenter.onParkingReservationButtonPressed()

        verify(view).showParkingReservation()
    }

    @Test
    fun `on clear expired reservation pressed, clears all expired reservations`(){
        presenter.onClearExpiredReservationPressed()

        verify(view).showClearExpiredReservationToast(model.getRemovedExpiredReservations())
    }

    @Test
    fun `on parking size ok button pressed, saves the input value for the parking lot size`(){
        presenter.onParkingSizeOkButtonPressed(Constants.PARKING_LOTS)

        verify(view).showParkingLots(model.getParkingSize())
    }
}
