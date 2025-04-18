package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int IdUser;
    private String NameUser;
    private String Name;
    private String PassWord;
    private String TypePhone;
    private String Phone;
    private String TypeIdCard;
    private String IdCard;
    private String PreferType;
    private String Email;
    private List<Order> orders;
    private List<Passenger> passengers;
    private List<UserNotify> userNotifies;
}
