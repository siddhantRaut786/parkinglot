package com.example.parkinglot.controllers;

import com.example.parkinglot.dtos.*;
import com.example.parkinglot.models.ParkingFloor;
import com.example.parkinglot.models.ParkingLot;
import com.example.parkinglot.services.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {
    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public CreateParkingLotResponseDto createParkingLot(
            CreateParkingLotRequestDto request
    ) {

        if (request.getNumberOfFloors() < 2) {
            CreateParkingLotResponseDto responseDto = new CreateParkingLotResponseDto();
            responseDto.setResponseStatus(ResponseStatusDto.FAILURE);
            return responseDto;
        }

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(request.getAddress());
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        for (int i = 0; i < request.getNumberOfFloors(); ++i) {
            parkingFloors.add(new ParkingFloor());
        }
        parkingLot.setParkingFloors(parkingFloors);

        ParkingLot createdParkingLot = parkingLotService.createParkingLot(parkingLot);

        CreateParkingLotResponseDto response = new CreateParkingLotResponseDto();
        response.setParkingLot(createdParkingLot);
        response.setResponseStatus(ResponseStatusDto.SUCCESS);
        return response;
    }


    public UpdateParkingLotResponseDto updateAddress(UpdateParkingLotRequestDto request) {
        ParkingLot updatedParkingLot = parkingLotService.updateAddress(
                request.getParkingLotId(), request.getAddress()
        );

        UpdateParkingLotResponseDto responseDto = new UpdateParkingLotResponseDto();
        responseDto.setParkingLot(updatedParkingLot);
        responseDto.setResponseStatus(ResponseStatusDto.SUCCESS);
        return responseDto;
    }
}

