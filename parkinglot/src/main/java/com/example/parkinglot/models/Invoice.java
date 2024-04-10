package com.example.parkinglot.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class Invoice extends BaseModel {
    private Ticket ticket;
    private double amount;
    private Date exitTime;
    private Operator operator;
    private InvoicePaidStatus invoicePaidStatus;

}
