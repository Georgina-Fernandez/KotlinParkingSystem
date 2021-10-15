package com.example.parkingsystem.mvp.presenter

import com.example.parkingsystem.listener.ParkingDialogListener
import com.example.parkingsystem.mvp.contract.ParkingDialogContract
import com.example.parkingsystem.util.Constants
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import android.R.attr.button
import java.lang.NumberFormatException
import android.R.attr.button


class ParkingSizeDialogTest {
    private lateinit var presenter: ParkingDialogContract.ParkingSizeDialogFragmentPresenter
    private var view: ParkingDialogContract.ParkingSizeDialogFragmentView = mock()
    private var listener: ParkingDialogListener = mock()

    @Before
    fun setup() {
        presenter = ParkingSizeDialogPresenter(view)
    }

    @Test
    fun `on button dialog fragment ok pressed when parking size is not empty, is less than the parking lot maximun size and greater than zero and it is saved`() {
        whenever(view.getParkingLots()).thenReturn((Constants.PARKING_LOTS).toString())

        presenter.onButtonDialogFragmentOkPressed(listener)

        assertEquals(Constants.PARKING_LOTS, view.getParkingLots().toInt())
        verify(view).showParkingLots(Constants.PARKING_LOTS, listener)
    }

    @Test
    fun `on button dialog fragment ok pressed when parking size is not empty, is greater than zero but greater than the parking lot maximun size and shows and error toast`() {
        whenever(view.getParkingLots()).thenReturn((PARKING_LOT_SIZE_GREATER_THAN_MAX).toString())

        presenter.onButtonDialogFragmentOkPressed(listener)

        assertEquals(PARKING_LOT_SIZE_GREATER_THAN_MAX, view.getParkingLots().toInt())
        verify(view).showErrorToastInput()
    }

    @Test
    fun `on button dialog fragment ok pressed when parking size is not empty, is greater than the parking lot maximun size but less than zero and shows and error toast`() {
        whenever(view.getParkingLots()).thenReturn((Constants.ZERO).toString())

        presenter.onButtonDialogFragmentOkPressed(listener)

        assertEquals(Constants.ZERO, view.getParkingLots().toInt())
        verify(view).showErrorToastInput()
    }

    @Test
    fun `on button dialog fragment ok pressed when parking size is empty, and shows an error toast`() {
        whenever(view.getParkingLots()).thenReturn(PARKING_LOT_SIZE_EMPTY)

        presenter.onButtonDialogFragmentOkPressed(listener)

        verify(view).showErrorToastInput()
    }

    @Test
    fun `on button dialog fragment cancel pressed closes dialog`() {
        presenter.onButtonDialogFragmentCancelPressed()

        verify(view).closeDialog()
    }

    companion object {
        private const val PARKING_LOT_SIZE_GREATER_THAN_MAX = Constants.PARKING_SIZE_MAX + Constants.ONE
        private const val PARKING_LOT_SIZE_EMPTY = ""
    }
}
