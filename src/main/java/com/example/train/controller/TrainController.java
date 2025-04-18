package com.example.train.controller;

import com.example.train.bean.Train;
import com.example.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;
    @GetMapping("/{idTrain}")
    public ResponseEntity<Train> getTrainById(@PathVariable int idTrain){
        Train train=trainService.getTrainById(idTrain);
        if(train!=null){
            return ResponseEntity.ok(train);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Train>> getAllTrains(){
        List<Train> trains=trainService.getAllTrains();
        if(trains!=null){
            return ResponseEntity.ok(trains);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Train> addTrain(@RequestBody Train train){
        trainService.addTrain(train);
        return ResponseEntity.ok(train);
    }
    @PutMapping
    public ResponseEntity<Train> updateTrain(@RequestBody Train updateTrain){
        Train train=trainService.getTrainById(updateTrain.getIdTrain());
        if(train!=null){
            trainService.updateTrain(updateTrain);
            return ResponseEntity.ok(updateTrain);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{idTrain}")
    public ResponseEntity<Void> deleteTrain(@PathVariable int idTrain){
        Train train=trainService.getTrainById(idTrain);
        if(train!=null){
            trainService.deleteTrain(idTrain);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
