package com.example.train.controller;

import com.example.train.bean.Order;
import com.example.train.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/{idPassenger}/{idSeat}")
    public ResponseEntity<Order> getOrderByUserAndSeat(@PathVariable int idPassenger, @PathVariable int idSeat){
        Order order=orderService.selectOrderByIdPassengerAndIdSeat(idPassenger,idSeat);
        if(order!=null){
            return ResponseEntity.ok(order);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/passenger/{idPassenger}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable int idPassenger){
        List<Order> orders=orderService.selectOrdersByPassenger(idPassenger);
        if(!orders.isEmpty()){
            return ResponseEntity.ok(orders);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        Order order1=orderService.selectOrderByIdPassengerAndIdTripAndDateAndIdDepartureAndIdTerminal(order.getIdPassenger(),order.getIdTrip(), order.getDate(),order.getIdDeparture(),order.getIdTerminal());
        if(order1!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("您已经预约过此票");
        }
        order1=orderService.insertOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order1);
    }
    @DeleteMapping("/{idPassenger}/{idSeat}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int idPassenger,@PathVariable int idSeat){
        Order order=orderService.selectOrderByIdPassengerAndIdSeat(idPassenger,idSeat);
        if(order!=null){
            orderService.deleteOrder(idPassenger,idSeat);
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.notFound().build();
    }
}
