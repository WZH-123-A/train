package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//车厢
public class Carriage {
    private int IdCarriage;
    private int NumberSeat;
    private int IdTrip;
    private int CarriageNumber;
    private String Date;
    private String SeatType;
    private List<Seat> seats;
}
