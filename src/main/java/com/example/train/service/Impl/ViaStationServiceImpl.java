package com.example.train.service.Impl;

import com.example.train.bean.Order;
import com.example.train.bean.Passenger;
import com.example.train.bean.User;
import com.example.train.bean.ViaStation;
import com.example.train.mapper.StationMapper;
import com.example.train.mapper.ViaStationMapper;
import com.example.train.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaStationServiceImpl implements ViaStationService {

    @Autowired
    private ViaStationMapper viaStationMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    @Lazy
    private OrderService orderService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private ViaStationStateServiceImpl viaStationStateServiceImpl;
    @Autowired
    private UserNotifyServiceImpl userNotifyServiceimpl;
    @Override
    public ViaStation getViaStationById(int idTrip, int idStation,String Date) {
        ViaStation viaStation= viaStationMapper.selectViaStationById(idTrip, idStation,Date);
        viaStation.setStation(stationMapper.selectStationById(viaStation.getIdStation()));
        return viaStation;
    }
    @Override
    public List<ViaStation> getViaStationsByTripIdOrderBySequence(int idTrip,String Date) {
        List<ViaStation> viaStations= viaStationMapper.selectViaStationsByTripIdOrderBySequence(idTrip,Date);
        for(ViaStation viaStation:viaStations){
            viaStation.setStation(stationMapper.selectStationById(viaStation.getIdStation()));
        }
        return viaStations;
    }
    @Override
    public List<ViaStation> getViaStationsByTripId(int idTrip,String Date) {
        List<ViaStation> viaStations=viaStationMapper.selectViaStationsByTripId(idTrip,Date);
        for(ViaStation viaStation:viaStations){
            viaStation.setStation(stationMapper.selectStationById(viaStation.getIdStation()));
        }
        return viaStations;
    }
    @Override
    public List<ViaStation> getViaStationsByStationId(int idStation,String Date) {
        List<ViaStation> viaStations=viaStationMapper.selectViaStationsByStationId(idStation,Date);
        for(ViaStation viaStation:viaStations){
            viaStation.setStation(stationMapper.selectStationById(viaStation.getIdStation()));
        }
        return viaStations;
    }
    @Override
    public void addViaStation(ViaStation viaStation) {
        viaStationMapper.insertViaStation(viaStation);
    }

    @Override
    public void updateViaStation(ViaStation viaStation) {
        viaStationMapper.updateViaStation(viaStation);
    }
    @Override
    public void setLater(ViaStation viaStation) {
        List<Order> orders=
                orderService.selectOrdersByTripIdAndDateAndIdDeparture
                        (viaStation.getIdTrip(),viaStation.getDate(),viaStation.getIdStation());
        List<Integer> users=new ArrayList<>();
        viaStationStateServiceImpl.registerObserver(userNotifyServiceimpl);
        if(!orders.isEmpty()){
            for(Order order:orders){
                Passenger passenger= passengerService.getPassengerById(order.getIdPassenger());
                users.add(passenger.getIdUser());
            }
            viaStationStateServiceImpl.notifyObservers
                    (users,viaStation.getIdTrip()+"次列车经过"
                            +viaStation.getStation().getNameStation()+"将晚点",viaStation.getDate());
        }
        updateViaStation(viaStation);
    }
    @Override
    public void deleteViaStation(int idTrip, int idStation,String Date) {
        viaStationMapper.deleteViaStation(idTrip, idStation,Date);
    }
}
