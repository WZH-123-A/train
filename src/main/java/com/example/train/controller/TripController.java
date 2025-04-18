package com.example.train.controller;

import com.example.train.bean.Trip;
import com.example.train.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private TripService tripService;
    @GetMapping("/{idTrip}/{date}")
    public ResponseEntity<Trip> getTripById(@PathVariable int idTrip, @PathVariable String date){
        Trip trip=tripService.getTripById(idTrip,date);
        if(trip!=null){
            return ResponseEntity.ok(trip);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Trip>> getAllTrips(){
        List<Trip> trips=tripService.getAllTrips();
        if(!trips.isEmpty()){
            return ResponseEntity.ok(trips);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip){
        tripService.addTrip(trip);
        return ResponseEntity.ok(trip);
    }
    @PutMapping
    public ResponseEntity<Trip> updateTrip(@RequestBody Trip updateTrip){
        Trip trip=tripService.getTripById(updateTrip.getIdTrip(),updateTrip.getDate());
        if(trip!=null){
            tripService.updateTrip(updateTrip);
            return ResponseEntity.ok(updateTrip);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{idTrip}/{date}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int idTrip, @PathVariable String date){
        Trip trip=tripService.getTripById(idTrip,date);
        if(trip!=null){
            tripService.deleteTrip(idTrip,date);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
