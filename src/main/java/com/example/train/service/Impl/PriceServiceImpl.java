package com.example.train.service.Impl;

import com.example.train.bean.Price;
import com.example.train.service.Impl.CalculateFare.*;
import com.example.train.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private CalculateFirstSeatFareServiceImpl calculateFirstFareService;
    @Autowired
    private CalculateSecondFareServiceImpl calculateSecondFareService;
    @Autowired
    private CalculateSoftSeatFareServiceImpl calculateSoftSeatFareService;
    @Autowired
    private CalculateSoftSleepFareServiceImpl calculateSoftSleepFareService;
    @Autowired
    private CalculateBusinessFareServiceImpl calculateBusinessFareService;
    @Autowired
    private CalculateHardSeatFareServiceImpl calculateHardSeatFareService;
    @Autowired
    private CalculateHardSleepFareServiceImpl calculateHardSleepFareService;
    @Override
    public Price calculatePrice(String TypeTrain, double distance) {
        Price price =new Price(0,0,0,0,0,0,0);
        if(TypeTrain.equals("C-城际")||TypeTrain.equals("D-动车")||TypeTrain.equals("G-高铁")){
            price.setBusinessSeat(calculateBusinessFareService.calculateFare(distance));
            price.setFirstClassSeat(calculateFirstFareService.calculateFare(distance));
            price.setSecondClassSeat(calculateSecondFareService.calculateFare(distance));
        }
        else{
            price.setSoftSleepSeat(calculateSoftSleepFareService.calculateFare(distance));
            price.setHardSleepSeat(calculateHardSleepFareService.calculateFare(distance));
            price.setSoftSeat(calculateSoftSeatFareService.calculateFare(distance));
            price.setHardSeat(calculateHardSeatFareService.calculateFare(distance));
        }
        return price;
    }
}
