package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaStation {
    private int IdTrip;
    private int IdStation;
    private String Date;
    private String ArrivalTime;
    private String DepartureTime;
    private int Sequence;
    private String State;
    private String Type;
    private Station station;
}
