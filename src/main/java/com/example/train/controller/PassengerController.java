package com.example.train.controller;

import com.example.train.bean.Passenger;
import com.example.train.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        Passenger passenger = passengerService.getPassengerById(id);
        if (passenger != null) {
            return ResponseEntity.ok(passenger);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        if (!passengers.isEmpty()) {
            return ResponseEntity.ok(passengers);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public  ResponseEntity<?> addPassenger(@RequestBody Passenger passenger) {
        Passenger passenger1= passengerService.getPassengerByIdCardAndIdUser(passenger.getIdCard(), passenger.getIdUser());
        if (passenger1!=null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("您已经添加过此联系人");
        }
        passengerService.addPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
    }

    @PutMapping
    public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger) {
        Passenger passenger1 = passengerService.getPassengerById(passenger.getIdPassenger());
        if (passenger1 != null) {
            passengerService.updatePassenger(passenger);
            return ResponseEntity.ok(passenger);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable int id) {
        Passenger passenger=passengerService.getPassengerById(id);
        if (passenger != null) {
            passengerService.deletePassenger(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}