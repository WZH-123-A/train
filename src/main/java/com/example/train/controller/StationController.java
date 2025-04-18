package com.example.train.controller;

import com.example.train.bean.Station;
import com.example.train.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("/{idStation}")
    public ResponseEntity<Station> getStationById(@PathVariable int idStation) {
        Station station = stationService.getStationById(idStation);
        if (station != null) {
            return ResponseEntity.ok(station);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAllStations() {
        System.out.println("100");
        List<Station> stations = stationService.getAllStations();
        if (stations != null) {
            return ResponseEntity.ok(stations);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        stationService.addStation(station);
        return ResponseEntity.ok(station);
    }

    @PutMapping
    public ResponseEntity<Station> updateStation(@RequestBody Station updateStation) {
        Station station = stationService.getStationById(updateStation.getIdStation());
        if (station != null) {
            stationService.updateStation(updateStation);
            return ResponseEntity.ok(station);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idStation}")
    public ResponseEntity<Void> deleteStation(@PathVariable int idStation){
        Station station=stationService.getStationById(idStation);
        if(station!=null){
            stationService.deleteStation(idStation);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
