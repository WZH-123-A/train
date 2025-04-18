package com.example.train.service;

import com.example.train.bean.ViaStation;

import java.util.List;

public interface ViaStationService {
    ViaStation getViaStationById(int idTrip, int idStation,String Date);
    List<ViaStation> getViaStationsByTripIdOrderBySequence(int idTrip,String Date);
    List<ViaStation> getViaStationsByTripId(int idTrip,String Date);
    List<ViaStation> getViaStationsByStationId(int idStation,String Date);
    void addViaStation(ViaStation viaStation);
    void updateViaStation(ViaStation viaStation);
    void setLater(ViaStation viaStation);
    void deleteViaStation(int idTrip, int idStation,String Date);
}

