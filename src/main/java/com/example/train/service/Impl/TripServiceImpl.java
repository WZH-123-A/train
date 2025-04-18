package com.example.train.service.Impl;

import com.example.train.bean.Trip;
import com.example.train.mapper.TrainMapper;
import com.example.train.mapper.TripMapper;
import com.example.train.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private TrainServiceImpl trainService;
    @Autowired
    private CarriageServiceImpl carriageService;

    @Override
    public Trip getTripById(int id,String Date) {
        Trip trip = tripMapper.selectTripById(id, Date);
        trip.setTrain(trainService.getTrainById(trip.getIdTrain()));
        trip.setCarriages(carriageService.getCarriagesByTripAndDate(id,Date));
        return trip;
    }

    @Override
    public List<Trip> getAllTrips() {
        List<Trip> trips=tripMapper.selectAllTrips();
        for (Trip trip : trips) {
            trip.setTrain(trainService.getTrainById(trip.getIdTrain()));
            trip.setCarriages(carriageService.getCarriagesByTripAndDate(trip.getIdTrip(),trip.getDate()));
        }
        return trips;
    }

    @Override
    public void addTrip(Trip trip) {
        tripMapper.insertTrip(trip);
    }

    @Override
    public void updateTrip(Trip trip) {
        tripMapper.updateTrip(trip);
    }

    @Override
    public void deleteTrip(int id,String Date) {
        tripMapper.deleteTrip(id,Date);
    }
}
