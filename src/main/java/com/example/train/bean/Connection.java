package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Connection {
    private int StationOne;
    private int StationTwo;
    private Double Distance;
}
