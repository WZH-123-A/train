package com.example.train.service.Impl.CalculateFare;

import com.example.train.service.CalculateFareService;
import org.springframework.stereotype.Service;

@Service
public class CalculateHardSeatFareServiceImpl implements CalculateFareService {
    @Override
    public int calculateFare(double distance) {
        return (int)(distance * 0.2);
    }
}
