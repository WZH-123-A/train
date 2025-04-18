package com.example.train.service.Impl;

import com.example.train.bean.Seat;
import com.example.train.mapper.SeatMapper;
import com.example.train.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatMapper seatMapper;

    @Override
    public Seat selectSeatById(int IdSeat) {
        return seatMapper.selectSeatById(IdSeat);
    }

    @Override
    public List<Seat> selectSeatsByCarriage(int IdCarriage) {
        return seatMapper.selectSeatsByCarriage(IdCarriage);
    }

    @Override
    public void insertSeat(Seat seat) {
        seatMapper.insertSeat(seat);
    }

    @Override
    public void updateSeat(Seat seat) {
        seatMapper.updateSeat(seat);
    }

    @Override
    public void deleteSeat(int IdSeat) {
        seatMapper.deleteSeat(IdSeat);
    }
}
