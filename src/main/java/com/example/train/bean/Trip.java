package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private int IdTrip;
    private int IdTrain;
    private String Date;
    private Train train;
    private List<Carriage> carriages;
}
