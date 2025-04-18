package com.example.train.service;

import com.example.train.bean.Price;

public interface PriceService {
    Price calculatePrice(String TypeTrain,double distance);
}
