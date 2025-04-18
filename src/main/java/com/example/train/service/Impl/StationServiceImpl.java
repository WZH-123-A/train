package com.example.train.service.Impl;

import com.example.train.bean.Station;
import com.example.train.mapper.StationMapper;
import com.example.train.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public Station getStationById(int id) {
        return stationMapper.selectStationById(id);
    }

    @Override
    public List<Station> getAllStations() {
        return stationMapper.selectAllStations();
    }

    @Override
    public List<Station> getStationsByCity(String city) {
        return stationMapper.selectStationsByCity(city);
    }
    @Override
    public void addStation(Station station) {
        stationMapper.insertStation(station);
    }

    @Override
    public void updateStation(Station station) {
        stationMapper.updateStation(station);
    }

    @Override
    public void deleteStation(int id) {
        stationMapper.deleteStation(id);
    }
}