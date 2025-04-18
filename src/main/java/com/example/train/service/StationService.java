package com.example.train.service;

import com.example.train.bean.Station;

import java.util.List;

public interface StationService {
    Station getStationById(int id);
    List<Station> getAllStations();
    List<Station> getStationsByCity(String City);
    void addStation(Station station);
    void updateStation(Station station);
    void deleteStation(int id);
}
