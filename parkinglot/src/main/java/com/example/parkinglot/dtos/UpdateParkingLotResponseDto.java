package com.example.parkinglot.dtos;

import com.example.parkinglot.models.ParkingLot;

public class UpdateParkingLotResponseDto extends ResponseDto {
    private ParkingLot parkingLot;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
