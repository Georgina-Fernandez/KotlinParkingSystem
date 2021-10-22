package com.example.parkingsystem.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystem.database.ParkingReservationDatabase
import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.mvp.contract.ParkingReservationContract
import com.example.parkingsystem.mvp.model.ParkingReservationModel
import com.example.parkingsystem.util.Constants
import com.example.parkingsystem.util.ParkingLotReservationVerify
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import java.util.Calendar
import org.junit.Before
import org.junit.Test


class ParkingReservationPresenterTest {
    private lateinit var presenter: ParkingReservationContract.ParkingReservationPresenter
    private lateinit var model: ParkingReservationContract.ParkingReservationModel
    private var view: ParkingReservationContract.ParkingReservationView = mock()
    private val datePickerListener: DatePickerDialog.OnDateSetListener = mock()
    private val timePickerListener: TimePickerDialog.OnTimeSetListener = mock()
    private val reservation: ParkingLotReservation = mock()
    private var database: ParkingReservationDatabase = ParkingReservationDatabase

    @Before
    fun setup() {
        model = ParkingReservationModel(database)
        presenter = ParkingReservationPresenter(model, view)
        database.setParkingLotSize(PARKING_SIZE)
    }

    private fun createDateCalendar(plusYears: Int = 0): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, get(Calendar.YEAR) + plusYears)
            set(Calendar.MONTH, get(Calendar.MONTH))
            set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR, HOUR)
            set(Calendar.MINUTE, MINUTE_END)
        }
    }

    private fun createTimeCalendar(): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR, HOUR)
            set(Calendar.MINUTE, MINUTE_END)
        }
    }

    private fun dateSet(day: Int) {
        model.convertToCalendar(
            "$day${Constants.SLASH}${MONTH + Constants.ONE}${Constants.SLASH}$YEAR", Constants.FORMAT_DATE
        )
    }

    private fun timeSet(hourOfDay: Int, minute: Int) {
        model.convertToCalendar("$hourOfDay${Constants.TWO_POINTS}$minute", Constants.FORMAT_TIME)
    }

    private fun createReservation(dayBegin: Int, dayEnd: Int) {
        val reservation = ParkingLotReservation()
        reservation.dateStart = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + dayBegin) }
        reservation.dateEnd = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + dayEnd) }
        reservation.timeStart = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + dayBegin) }
        reservation.timeEnd = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + dayEnd) }
        reservation.securityCode = SECURITY_CODE_STRING.toInt()
        reservation.parkingLot = PARKING_LOT_STRING.toInt()
        model.makeReservation(reservation)
    }

    @Test
    fun `on button parking reservation picker pressed, view confirms the first button has been pressed and shows the date picker`() {
        whenever(view.getButtonPickerBegin()).thenReturn(true)

        presenter.onButtonParkingReservationPickerPressed(datePickerListener)

        verify(view).showDatePickerDialog(datePickerListener)
    }

    @Test
    fun `on begin date set pressed, gives format to date, saves it in the model and calls time picker`() {
        createDateCalendar()
        dateSet(DAY)

        presenter.onDateSetPressed(YEAR, MONTH, DAY, timePickerListener)
        presenter.onTimeSetPressed(HOUR, MINUTE_END)

        verify(view).showTimePickerDialog(timePickerListener)
    }

    @Test
    fun `on first button date pressed lala`(){
        whenever(view.getButtonPickerBegin()).thenReturn(true)
        model.setDateBeginPressed(view.getButtonPickerBegin())
        presenter.onDateSetPressed(YEAR, MONTH, DAY, timePickerListener)
        verify(view).showTimePickerDialog(timePickerListener)
    }

    @Test
    fun `on begin date set pressed, it enables date end picker`() {
        whenever(view.getButtonPickerBegin()).thenReturn(true)
        model.setDateBegin(createDateCalendar())
        model.setDateBeginPressed(view.getButtonPickerBegin())

        presenter.onTimeSetPressed(HOUR, MINUTE_END)

        verify(view).enableDateEnd()
    }

    @Test
    fun `on end time set pressed, gives format to time, saves it in the model and shows it on the input`() {
        val dateCalendar = createDateCalendar()
        model.setDateEnd(createDateCalendar())

        presenter.onTimeSetPressed(HOUR, MINUTE_END)

        verify(view).showDateAndTimeEnd(model.getFormattedString(dateCalendar, dateCalendar))
    }

    @Test
    fun `on save reservation button pressed, when there is missing date start, shows message`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showMissingDateTimeStart()
    }


    @Test
    fun `on save reservation button pressed, when there is expired date start, shows message`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) - Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH)) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showExpiredDateTimeStart()
    }

    @Test
    fun `on save reservation button pressed, when there is missing date end, shows message`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showMissingDateTimeEnd()
    }

    @Test
    fun `on save reservation button pressed, when there is invalid date end, shows message`() {
        whenever(reservation.getReservationVerifyResult()).thenReturn(ParkingLotReservationVerify.INVALID_END_DATE)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) - Constants.ONE) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showInvalidDateTimeEnd()
    }


    @Test
    fun `on save reservation button pressed, when there is missing parking space, shows message`() {
        whenever(view.getParkingLot()).thenReturn(EMPTY_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + TWO) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showMissingParkingSpace()
    }

    @Test
    fun `on save reservation button pressed, when there is missing security code, shows message`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(EMPTY_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + TWO) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showMissingSecurityCode()
    }

    @Test
    fun `on save reservation button pressed, when there is out of bound parking space, shows message`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_OUT_OF_BOUND)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + TWO) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showOutOfBoundParkingSpace(PARKING_SIZE)
    }


    @Test
    fun `on save reservation button pressed, when there is reservation overlapping, shows message`() {
        database.hashMapReservation.clear()
        createReservation(1, 2)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + THREE) })
        model.setTimeBegin(createTimeCalendar())
        model.setTimeEnd(createTimeCalendar())

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showReservationOverlapping()
    }

    @Test
    fun `on save reservation button pressed, if the reservation is saved successfully, shows message`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateBegin(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + Constants.ONE) })
        model.setDateEnd(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + TWO) })

        presenter.onButtonParkingReservationSavePressed()

        verify(view).showOkReservation()
    }

    companion object {
        private const val YEAR = 2021
        private const val MONTH = 10
        private const val DAY = 14
        private const val HOUR = 9
        private const val MINUTE_END = 30
        private const val PARKING_LOT_STRING = "1"
        private const val SECURITY_CODE_STRING = "545"
        private const val PARKING_SIZE = 2
        private const val PARKING_LOT_OUT_OF_BOUND = "3"
        private const val EMPTY_STRING = ""
        private const val TWO = 2
        private const val THREE = 3
    }
}
