package com.example.train.service;

import com.example.train.bean.Carriage;

import java.util.List;

public interface CarriageService {
    Carriage getCarriageById(int idCarriage);
    List<Carriage> getCarriagesByTripAndDate(int idTrip, String date);
    void addCarriage(Carriage carriage);
    void updateCarriage(Carriage carriage);
    void deleteCarriage(int idCarriage);
}
