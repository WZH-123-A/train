package com.example.train.service;

import com.example.train.bean.Train;

import java.util.List;

public interface TrainService {
    Train getTrainById(int id);
    List<Train> getAllTrains();
    void addTrain(Train train);
    void updateTrain(Train train);
    void deleteTrain(int id);
}