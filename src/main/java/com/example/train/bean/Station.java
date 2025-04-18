package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private int IdStation;
    private String NameStation;
    private String City;
    private int CoordinatesX;
    private int CoordinatesY;
}
