package com.example.train.controller;

import com.example.train.bean.Carriage;
import com.example.train.service.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carriages")
public class CarriageController {

    @Autowired
    private CarriageService carriageService;

    @GetMapping("/{idCarriage}")
    public ResponseEntity<Carriage> getCarriageById(@PathVariable int idCarriage) {
        Carriage carriage = carriageService.getCarriageById(idCarriage);
        if (carriage != null) {
            return ResponseEntity.ok(carriage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/trip/{idTrip}/{date}")
    public ResponseEntity<List<Carriage>> getCarriagesByTrainId(@PathVariable int idTrip,@PathVariable String date) {
        List<Carriage> carriages = carriageService.getCarriagesByTripAndDate(idTrip,date);
        if (!carriages.isEmpty()) {
            return ResponseEntity.ok(carriages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Carriage> addCarriage(@RequestBody Carriage carriage) {
        carriageService.addCarriage(carriage);
        return ResponseEntity.status(HttpStatus.CREATED).body(carriage);
    }

    @PutMapping
    public ResponseEntity<Carriage> updateCarriage( @RequestBody Carriage updateCarriage) {
        Carriage carriage = carriageService.getCarriageById(updateCarriage.getIdCarriage());
        if (carriage != null) {
            carriageService.updateCarriage(updateCarriage);
            return ResponseEntity.ok(updateCarriage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCarriage}")
    public ResponseEntity<Void> deleteCarriage(@PathVariable int idCarriage) {
        Carriage carriage = carriageService.getCarriageById(idCarriage);
        if (carriage != null) {
            carriageService.deleteCarriage(idCarriage);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
