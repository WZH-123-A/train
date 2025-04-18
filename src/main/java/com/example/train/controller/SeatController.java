package com.example.train.controller;

import com.example.train.bean.Seat;
import com.example.train.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @GetMapping("/{idSeat}")
    public ResponseEntity<Seat> getSeatById(@PathVariable int idSeat){
        Seat seat=seatService.selectSeatById(idSeat);
        if(seat!=null){
            return ResponseEntity.ok(seat);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/carriage/{idCarriage}")
    public ResponseEntity<List<Seat>> getSeatsByCarriage(@PathVariable int idCarriage){
        List<Seat> seats=seatService.selectSeatsByCarriage(idCarriage);
        if(!seats.isEmpty()){
            return ResponseEntity.ok(seats);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
        seatService.insertSeat(seat);
        return ResponseEntity.ok(seat);
    }
    @PutMapping
    public ResponseEntity<Seat> updateSeat(@RequestBody Seat updateSeat){
        Seat seat=seatService.selectSeatById(updateSeat.getIdSeat());
        if(seat!=null){
            seatService.updateSeat(updateSeat);
            return ResponseEntity.ok(seat);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{idSeat}")
    public ResponseEntity<Void> deleteSeat(@PathVariable int idSeat){
        Seat seat=seatService.selectSeatById(idSeat);
        if(seat!=null){
            seatService.deleteSeat(idSeat);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
