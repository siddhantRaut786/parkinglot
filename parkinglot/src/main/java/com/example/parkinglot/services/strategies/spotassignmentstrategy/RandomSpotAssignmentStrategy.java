package com.example.parkinglot.services.strategies.spotassignmentstrategy;

import com.example.parkinglot.models.*;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {

    @Override
    public ParkingSpot assignSpot(ParkingLot parkingLot, SpotType spotType, EntryGate entryGate) {
        for (ParkingFloor parkingFloor: parkingLot.getParkingFloors()) {
            for (ParkingSpot parkingSpot: parkingFloor.getParkingSpots()) {
                if (parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)
                        && parkingSpot.getSpotType().equals(spotType)) {
                    return parkingSpot;
                }
            }
        }

        return null;
    }
}
