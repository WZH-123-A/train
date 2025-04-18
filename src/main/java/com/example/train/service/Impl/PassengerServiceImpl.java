package com.example.train.service.Impl;

import com.example.train.bean.Passenger;
import com.example.train.mapper.PassengerMapper;
import com.example.train.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public Passenger getPassengerById(int idPassenger) {
        return passengerMapper.selectPassengerById(idPassenger);
    }
    @Override
    public Passenger getPassengerByIdCardAndIdUser(String idCard, int idUser) {
        return passengerMapper.selectPassengerByIdCardAndIdUser(idCard,idUser);
    }
    @Override
    public List<Passenger> getAllPassengers() {
        return passengerMapper.selectAllPassengers();
    }

    @Override
    public List<Passenger> getPassengerByUser(int idUser) {
        return passengerMapper.selectPassengerByUser(idUser);
    }
    @Override
    public void addPassenger(Passenger passenger) {
        passenger.setTypeVerify("已通过");
        passengerMapper.insertPassenger(passenger);
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        passengerMapper.updatePassenger(passenger);
    }

    @Override
    public void deletePassenger(int idPassenger) {
        passengerMapper.deletePassenger(idPassenger);
    }
}
