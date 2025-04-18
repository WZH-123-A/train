package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private int FirstClassSeat;
    private int SecondClassSeat;
    private int BusinessSeat;
    private int HardSeat;
    private int SoftSeat;
    private int HardSleepSeat;
    private int SoftSleepSeat;
}
