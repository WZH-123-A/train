package com.example.train.controller;

import com.example.train.bean.Price;
import com.example.train.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {
    @Autowired
    private PriceService priceService;
    @GetMapping("/{TypeTrain}/{distance}")
    public ResponseEntity<Price> getPrice(@PathVariable String TypeTrain,@PathVariable double distance){
        return ResponseEntity.ok().body(priceService.calculatePrice(TypeTrain,distance));
    }
}
