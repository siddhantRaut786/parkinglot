package com.example.parkinglot.services.strategies.spotassignmentstrategy;

import com.example.parkinglot.models.EntryGate;
import com.example.parkinglot.models.ParkingLot;
import com.example.parkinglot.models.ParkingSpot;
import com.example.parkinglot.models.SpotType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(ParkingLot parkingLot,
                           SpotType spotType,
                           EntryGate entryGate);
}
