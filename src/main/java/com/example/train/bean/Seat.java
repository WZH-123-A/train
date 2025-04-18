package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private int IdSeat;
    private int IdCarriage;
    private String SeatNumber;
}
