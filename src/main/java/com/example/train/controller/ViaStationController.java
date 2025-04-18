package com.example.train.controller;

import com.example.train.bean.ViaStation;
import com.example.train.service.ViaStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaStation")
public class ViaStationController {
    @Autowired
    private ViaStationService viaStationService;
    @GetMapping("/{idTrip}/{idStation}/{date}")
    public ResponseEntity<ViaStation> getViaStationByIdTripAndIdStation(@PathVariable int idTrip, @PathVariable int idStation, @PathVariable String date){
        ViaStation viaStation=viaStationService.getViaStationById(idTrip,idStation,date);
        if(viaStation!=null){
            return ResponseEntity.ok(viaStation);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/trip/{idTrip}/{date}")
    public ResponseEntity<List<ViaStation>> getViaStationsByIdTrip(@PathVariable int idTrip, @PathVariable String date){
        List<ViaStation> viaStations=viaStationService.getViaStationsByTripId(idTrip,date);
        if(!viaStations.isEmpty()){
            return ResponseEntity.ok(viaStations);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<ViaStation> createViaStation(@RequestBody ViaStation viaStation){
        viaStationService.addViaStation(viaStation);
        return ResponseEntity.ok(viaStation);
    }
    @PutMapping
    public ResponseEntity<ViaStation> updateViaStation(@RequestBody ViaStation updateViaStation){
        ViaStation viaStation=viaStationService.getViaStationById(updateViaStation.getIdTrip(),updateViaStation.getIdStation(),updateViaStation.getDate());
        if(viaStation!=null){
            viaStationService.updateViaStation(updateViaStation);
            return ResponseEntity.ok(viaStation);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{idTrip}/{idStation}/{date}")
    public ResponseEntity<Void> deleteViaStation(@PathVariable int idTrip,@PathVariable int idStation, @PathVariable String date){
        ViaStation viaStation=viaStationService.getViaStationById(idTrip,idStation,date);
        if(viaStation!=null){
            viaStationService.deleteViaStation(idTrip,idStation,date);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
