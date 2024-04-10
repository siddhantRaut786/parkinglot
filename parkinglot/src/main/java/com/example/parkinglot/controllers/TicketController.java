package com.example.parkinglot.controllers;

import com.example.parkinglot.dtos.GenerateTicketRequestDto;
import com.example.parkinglot.dtos.GenerateTicketResponseDto;
import com.example.parkinglot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(
            GenerateTicketRequestDto request
    ) {
        return null;
    }
}
