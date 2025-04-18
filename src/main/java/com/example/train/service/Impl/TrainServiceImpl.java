package com.example.train.service.Impl;

import com.example.train.bean.Train;
import com.example.train.mapper.TrainMapper;
import com.example.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private CarriageServiceImpl carriageService;

    @Override
    public Train getTrainById(int id) {
        return trainMapper.selectTrainById(id);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainMapper.selectAllTrains();
    }

    @Override
    public void addTrain(Train train) {
        trainMapper.insertTrain(train);
    }

    @Override
    public void updateTrain(Train train) {
        trainMapper.updateTrain(train);
    }

    @Override
    public void deleteTrain(int id) {
        trainMapper.deleteTrain(id);
    }
}
