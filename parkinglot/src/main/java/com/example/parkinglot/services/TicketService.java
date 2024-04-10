package com.example.parkinglot.services;

import com.example.parkinglot.models.*;
import com.example.parkinglot.repositories.ParkingLotRepository;
import com.example.parkinglot.repositories.TicketRepository;
import com.example.parkinglot.services.factories.FeesCalculationStrategyFactory;
import com.example.parkinglot.services.strategies.feesCalculationStrategy.FeesCalculationStrategy;
import com.example.parkinglot.services.strategies.spotassignmentstrategy.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {
    private TicketRepository ticketRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private ParkingLotRepository parkingLotRepository;

    private FeesCalculationStrategyFactory feesCalculationStrategyFactory;

    public TicketService(TicketRepository ticketRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         ParkingLotRepository parkingLotRepository,
                         FeesCalculationStrategyFactory feesCalculationStrategyFactory) {
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.parkingLotRepository = parkingLotRepository;
        this.feesCalculationStrategyFactory = feesCalculationStrategyFactory;
    }

    public Ticket generateTicket(Long parkingLotId, Vehicle vehicle, SpotType spotType, EntryGate entryGate) {
        // 1. Find a spot
        //
        ParkingLot parkingLot = parkingLotRepository.getById(parkingLotId);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(
                parkingLot,
                spotType,
                entryGate
        );

        if (parkingLot == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setEntryGate(entryGate);
        ticket.setGeneratedBy(entryGate.getOperator());
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());


        return ticket;
    }

    public Invoice generateInvoice(Ticket ticket) {
        FeesCalculationStrategy feesCalculationStrategy = feesCalculationStrategyFactory.getFeesCalculationStrategy(ticket.getParkingSpot().getSpotType());
        double fees = feesCalculationStrategy.calculateFees(ticket);
        Invoice invoice = new Invoice();
        invoice.setAmount(fees);
        invoice.setTicket(ticket);
        invoice.setExitTime(new Date());
        return invoice;
    }
}
