package com.example.parkingsystem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.ReservationBinding
import com.example.parkingsystem.entity.ParkingLotReservation
import com.example.parkingsystem.util.Constants

class ReservationListAdapter(private val reservations: List<ParkingLotReservation>) :
    RecyclerView.Adapter<ReservationListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reservations[position], position)
    }

    override fun getItemCount(): Int = reservations.size

    class ViewHolder(private val binding: ReservationBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: ParkingLotReservation, position: Int) {
            with(binding) {
                textReservationList.text = context.getString(R.string.text_reservation_list, position + Constants.ONE)
                textDateTimeStartReservationList.text = context.getString(
                    R.string.text_reservation_list_date_time_start,
                    reservation.getFormattedString(reservation.dateStart, reservation.timeStart)
                )
                textDateTimeEndReservationList.text = context.getString(
                    R.string.text_reservation_list_date_time_end,
                    reservation.getFormattedString(reservation.dateEnd, reservation.timeEnd)
                )
                textParkingSpaceReservationList.text =
                    context.getString(R.string.text_parking_space_reservation_list, reservation.parkingLot.toString())
                textSecurityCodeReservationList.text =
                    context.getString(R.string.text_security_code_reservation_list, reservation.securityCode.toString())
            }
        }
    }
}
