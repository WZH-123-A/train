package com.example.train.service.Impl;

import com.example.train.bean.*;
import com.example.train.mapper.OrderMapper;
import com.example.train.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TripServiceImpl tripService;
    @Autowired
    private ViaStationServiceImpl  viaStationService;
    @Autowired
    private SeatServiceImpl seatService;
    @Autowired
    private PassengerServiceImpl passengerService;
    @Override
    public Order selectOrderByIdPassengerAndIdSeat(int IdPassenger, int IdSeat) {
        Order order=orderMapper.selectOrderByIdPassengerAndIdSeat(IdPassenger,IdSeat);
        if(order!=null) {
            Trip trip = tripService.getTripById(order.getIdTrip(), order.getDate());
            order.setTrip(trip);
            Seat seat = seatService.selectSeatById(order.getIdSeat());
            order.setSeat(seat);
            ViaStation departureStation = viaStationService.getViaStationById(order.getIdTrip(), order.getIdDeparture(),trip.getDate());
            order.setDepartureStation(departureStation);
            ViaStation destinationStation = viaStationService.getViaStationById(order.getIdTrip(), order.getIdTerminal(),trip.getDate());
            order.setDestinationStation(destinationStation);
            List<ViaStation> viaStationList = viaStationService.getViaStationsByTripIdOrderBySequence(order.getIdTrip(), trip.getDate());
            order.setViaStationList(viaStationList);
            Passenger passenger = passengerService.getPassengerById(order.getIdPassenger());
            order.setPassenger(passenger);
        }
        return order;
    }
    @Override
    public Order selectOrderByIdPassengerAndIdTripAndDateAndIdDepartureAndIdTerminal(int IdPassenger, int IdTrip,String Date,int IdDeparture,int IdTerminal) {
        Order order=orderMapper.selectOrderByIdPassengerAndIdTripAndDateAndIdDepartureAndIdTerminal(IdPassenger,IdTrip,Date,IdDeparture,IdTerminal);
        if(order!=null) {
            Trip trip = tripService.getTripById(order.getIdTrip(), order.getDate());
            order.setTrip(trip);
            Seat seat = seatService.selectSeatById(order.getIdSeat());
            order.setSeat(seat);
            ViaStation departureStation = viaStationService.getViaStationById(order.getIdTrip(), order.getIdDeparture(),trip.getDate());
            order.setDepartureStation(departureStation);
            ViaStation destinationStation = viaStationService.getViaStationById(order.getIdTrip(), order.getIdTerminal(),trip.getDate());
            order.setDestinationStation(destinationStation);
            List<ViaStation> viaStationList = viaStationService.getViaStationsByTripIdOrderBySequence(order.getIdTrip(), trip.getDate());
            order.setViaStationList(viaStationList);
            Passenger passenger = passengerService.getPassengerById(order.getIdPassenger());
            order.setPassenger(passenger);
        }
        return order;
    }
    @Override
    public List<Order> selectOrdersByIdSeat(int IdSeat) {
        List<Order> orders=orderMapper.selectOrdersByIdSeat(IdSeat);
        for(Order order:orders){
            Trip trip= tripService.getTripById(order.getIdTrip(),order.getDate());
            order.setTrip(trip);
            Seat seat=seatService.selectSeatById(order.getIdSeat());
            order.setSeat(seat);
            ViaStation departureStation=viaStationService.getViaStationById(order.getIdTrip(),order.getIdDeparture(),trip.getDate());
            order.setDepartureStation(departureStation);
            ViaStation destinationStation=viaStationService.getViaStationById(order.getIdTrip(),order.getIdTerminal(),trip.getDate());
            order.setDestinationStation(destinationStation);
            List<ViaStation> viaStationList=viaStationService.getViaStationsByTripIdOrderBySequence(order.getIdTrip(), trip.getDate());
            order.setViaStationList(viaStationList);
            Passenger passenger=passengerService.getPassengerById(order.getIdPassenger());
            order.setPassenger(passenger);
        }
        return orders;
    }
    @Override
    public List<Order> selectOrdersByPassenger(int IdPassenger) {
        List<Order> orders=orderMapper.selectOrdersByPassenger(IdPassenger);
        for(Order order:orders){
            Trip trip= tripService.getTripById(order.getIdTrip(),order.getDate());
            order.setTrip(trip);
            Seat seat=seatService.selectSeatById(order.getIdSeat());
            order.setSeat(seat);
            ViaStation departureStation=viaStationService.getViaStationById(order.getIdTrip(),order.getIdDeparture(),trip.getDate());
            order.setDepartureStation(departureStation);
            ViaStation destinationStation=viaStationService.getViaStationById(order.getIdTrip(),order.getIdTerminal(),trip.getDate());
            order.setDestinationStation(destinationStation);
            List<ViaStation> viaStationList=viaStationService.getViaStationsByTripIdOrderBySequence(order.getIdTrip(), trip.getDate());
            order.setViaStationList(viaStationList);
            Passenger passenger=passengerService.getPassengerById(order.getIdPassenger());
            order.setPassenger(passenger);
        }
        return orders;
    }
    @Override
    public List<Order> selectOrdersByTripIdAndDateAndIdDeparture(int IdTrip,String Date,int IdDeparture) {
        return orderMapper.selectOrdersByTripIdAndDateAndIdDeparture(IdTrip,Date,IdDeparture);
    }
    @Override
    public Order insertOrder(Order order) {
        Trip trip= tripService.getTripById(order.getIdTrip(),order.getDate());
        for(Carriage carriage:trip.getCarriages()){
            if(carriage.getSeatType().equals(order.getSeatType())){
                for(Seat seat:carriage.getSeats()){
                    List<Order> orders=selectOrdersByIdSeat(seat.getIdSeat());
                    if(orders.isEmpty()){
                        order.setIdSeat(seat.getIdSeat());
                        order.setStatus("未出行");
                        orderMapper.insertOrder(order);
                        return order;
                    }
                    else{
                        int flag=0;
                        for(Order order1:orders){
                            if((viaStationService.getViaStationById(
                                    order.getIdTrip(),order.getIdDeparture(),
                                    order.getDate()).getSequence()>=viaStationService.
                                    getViaStationById(order1.getIdTrip(),order1.getIdDeparture(),order1.getDate()).
                                    getSequence()&&viaStationService.
                                    getViaStationById(order.getIdTrip(),order.getIdTerminal(),order.getDate()).
                                    getSequence()<viaStationService.getViaStationById(order1.getIdTrip(),order1.
                                            getIdTerminal(),order1.getDate()).
                                    getSequence())|| (viaStationService.getViaStationById(order.getIdTrip(),order.
                                            getIdTerminal(),order.getDate()).
                                    getSequence()>viaStationService.getViaStationById(order1.getIdTrip(),order1.
                                            getIdDeparture(),order1.getDate()).
                                    getSequence()&&viaStationService.getViaStationById(order.getIdTrip(),order.
                                            getIdTerminal(),order.getDate()).
                                    getSequence()<=viaStationService.getViaStationById(order1.getIdTrip(),order1.
                                            getIdTerminal(),order1.getDate()).
                                    getSequence())){
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0){
                            order.setIdSeat(seat.getIdSeat());
                            order.setStatus("未出行");
                            orderMapper.insertOrder(order);
                            return order;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void deleteOrder(int IdPassenger,int IdSeat) {
        orderMapper.deleteOrder(IdPassenger,IdSeat);
    }
}