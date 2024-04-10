package com.example.parkinglot.services.strategies.feesCalculationStrategy;

import com.example.parkinglot.models.SpotType;
import com.example.parkinglot.models.Ticket;

public class BikeFeesCalculationStrategy implements FeesCalculationStrategy {
    @Override
    public double calculateFees(Ticket ticket) {
        //TODO implement
        return 0;
    }

    @Override
    public SpotType getSpotType() {
        return SpotType.SMALL;
    }
}
