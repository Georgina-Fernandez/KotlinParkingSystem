package com.example.parkingsystem.util

enum class ParkingLotReservationVerify {
    MISSING_DATE_BEGIN,
    EXPIRED_DATE_BEGIN,
    MISSING_DATE_END,
    INVALID_END_DATE,
    MISSING_PARKING_LOT,
    OUT_OF_BOUND_LOT,
    MISSING_CODE,
    OVERLAP_RESERVATION,
    SUCCESS,
    VALID_FIELDS
}
