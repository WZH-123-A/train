package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfo {
    private double price;
    private Trip trip;
    private ViaStation departureTime;
    private ViaStation destinationTime;
    private Station departureStation;
    private Station destinationStation;
    private List<ViaStation> viaStations;
    private int businessSeatNumber;
    private int firstClassSeatNumber;
    private int secondClassSeatNumber;
    private int softSleepSeatNumber;
    private int hardSleepSeatNumber;
    private int hardSeatNumber;
    private int softSeatNumber;
    private double distance;
}
