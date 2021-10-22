package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.mvp.contract.ReservationListContract
import com.example.parkingsystem.mvp.model.ReservationListModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class ReservationListPresenterTest {
    private lateinit var presenter: ReservationListContract.ReservationListPresenter
    private lateinit var model: ReservationListContract.ReservationListModel
    private var view: ReservationListContract.ReservationListView = mock()
    private var database: ParkingReservationDatabase = ParkingReservationDatabase

    @Before
    fun setup() {
        model = ReservationListModel(database)
        presenter = ReservationListPresenter(model, view)
    }

    @Test
    fun `reservations list shows all valid reservations`() {
        presenter.getReservationsList()

        verify(view).showReservationsList(model.getReservationsList())
    }
}

