package com.example.train.service;

import com.example.train.bean.Trip;

import java.util.List;

public interface TripService {
    Trip getTripById(int id,String date);
    List<Trip> getAllTrips();
    void addTrip(Trip trip);
    void updateTrip(Trip trip);
    void deleteTrip(int id,String date);
}
