package com.example.train.service;

import com.example.train.bean.Seat;

import java.util.List;

public interface SeatService {
    Seat selectSeatById(int IdSeat);
    List<Seat> selectSeatsByCarriage(int IdCarriage);
    void insertSeat(Seat seat);
    void updateSeat(Seat seat);
    void deleteSeat(int IdSeat);
}