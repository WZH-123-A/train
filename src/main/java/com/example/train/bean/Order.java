package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int IdPassenger;
    private int IdSeat;
    private int IdTrip;
    private String Date;
    private int IdDeparture;
    private int IdTerminal;
    private double Cost;
    private String Status;
    private String SeatType;
    private Seat seat;
    private Trip trip;
    private ViaStation departureStation;
    private ViaStation destinationStation;
    private Passenger passenger;
    List<ViaStation> viaStationList;
}
