package com.example.train.service;

import com.example.train.bean.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger getPassengerById(int idPassenger);
    Passenger getPassengerByIdCardAndIdUser(String idCard,int idUser);
    List<Passenger> getAllPassengers();
    List<Passenger> getPassengerByUser(int idUser);
    void addPassenger(Passenger passenger);
    void updatePassenger(Passenger passenger);
    void deletePassenger(int idPassenger);
}
