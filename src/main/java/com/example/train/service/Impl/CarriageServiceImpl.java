package com.example.train.service.Impl;

import com.example.train.bean.Carriage;
import com.example.train.mapper.CarriageMapper;
import com.example.train.service.CarriageService;
import com.example.train.service.ObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarriageServiceImpl implements CarriageService  {
    @Autowired
    private CarriageMapper carriageMapper;
    @Autowired
    private SeatServiceImpl seatService;
    @Override
    public Carriage getCarriageById(int idCarriage) {
        Carriage carriage=carriageMapper.selectCarriageById(idCarriage);
        carriage.setSeats(seatService.selectSeatsByCarriage(idCarriage));
        return carriage;
    }

    @Override
    public List<Carriage> getCarriagesByTripAndDate(int idTrip,String date) {
        List<Carriage> carriages=carriageMapper.selectCarriageByTripAndDate(idTrip,date);
        for (Carriage carriage:carriages) {
            carriage.setSeats(seatService.selectSeatsByCarriage(carriage.getIdCarriage()));
        }
        return carriages;
    }

    @Override
    public void addCarriage(Carriage carriage) {
        carriageMapper.insertCarriage(carriage);
    }

    @Override
    public void updateCarriage(Carriage carriage) {
        carriageMapper.updateCarriage(carriage);
    }

    @Override
    public void deleteCarriage(int idCarriage) {
        carriageMapper.deleteCarriage(idCarriage);
    }
}
