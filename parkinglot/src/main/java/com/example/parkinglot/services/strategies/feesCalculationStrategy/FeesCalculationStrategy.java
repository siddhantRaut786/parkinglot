package com.example.parkinglot.services.strategies.feesCalculationStrategy;

import com.example.parkinglot.models.SpotType;
import com.example.parkinglot.models.Ticket;

public interface FeesCalculationStrategy {
    double calculateFees(Ticket ticket);

    SpotType getSpotType();
}
