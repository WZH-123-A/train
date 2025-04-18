package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    private int IdPassenger;
    private int IdUser;
    private String NamePassenger;
    private String TypeIdCard;
    private String IdCard;
    private String TypePhone;
    private String Phone;
    private String TypeVerify;
    private String PreferType;
}
