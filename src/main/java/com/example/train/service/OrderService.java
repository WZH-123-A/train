package com.example.train.service;

import com.example.train.bean.Order;

import java.util.List;

public interface OrderService {
    Order selectOrderByIdPassengerAndIdSeat(int IdPassenger, int IdSeat);
    Order selectOrderByIdPassengerAndIdTripAndDateAndIdDepartureAndIdTerminal(int IdPassenger, int IdTrip,String Date,int IdDeparture,int IdTerminal);
    List<Order> selectOrdersByIdSeat(int IdSeat);
    List<Order> selectOrdersByPassenger(int IdPassenger);
    List<Order> selectOrdersByTripIdAndDateAndIdDeparture(int IdTrip,String Date,int IdDeparture);
    Order insertOrder(Order order);
    void deleteOrder(int IdPassenger,int IdSeat);
}

