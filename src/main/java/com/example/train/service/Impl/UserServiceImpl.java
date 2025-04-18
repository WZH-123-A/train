package com.example.train.service.Impl;

import com.example.train.bean.Order;
import com.example.train.bean.Passenger;
import com.example.train.bean.User;
import com.example.train.bean.UserNotify;
import com.example.train.mapper.UserMapper;
import com.example.train.service.OrderService;
import com.example.train.service.PassengerService;
import com.example.train.service.UserNotifyService;
import com.example.train.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserNotifyService userNotifyService;
    @Override
    public User getUserById(int id) {
        User user= userMapper.selectUserById(id);
        List<Passenger> passengers=passengerService.getPassengerByUser(id);
        List<Order> orders=new ArrayList<>();
        List<UserNotify> userNotifies=userNotifyService.getUserNotifyByUserId(id);
        for(Passenger passenger : passengers) {
            List<Order> orders1 = orderService.selectOrdersByPassenger(passenger.getIdPassenger());
            orders.addAll(orders1);
        }
        user.setPassengers(passengers);
        user.setUserNotifies(userNotifies);
        user.setOrders(orders);
        return user;
    }

    @Override
    public User getUserByIdCard(String idCard) {
        User user=userMapper.selectUserByIdCard(idCard);
        if(user!=null) {
            List<Passenger> passengers = passengerService.getPassengerByUser(user.getIdUser());
            List<Order> orders=new ArrayList<>();
            List<UserNotify> userNotifies=userNotifyService.getUserNotifyByUserId(user.getIdUser());
            for(Passenger passenger : passengers) {
                List<Order> orders1 = orderService.selectOrdersByPassenger(passenger.getIdPassenger());
                orders.addAll(orders1);
            }
            user.setOrders(orders);
            user.setPassengers(passengers);
            user.setUserNotifies(userNotifies);
        }
        return user;
    }
    @Override
    public User getUserByPhone(String phone) {
        User user=userMapper.selectUserByPhone(phone);
        if(user!=null) {
            List<Passenger> passengers = passengerService.getPassengerByUser(user.getIdUser());
            List<Order> orders=new ArrayList<>();
            List<UserNotify> userNotifies=userNotifyService.getUserNotifyByUserId(user.getIdUser());
            for(Passenger passenger : passengers) {
                List<Order> orders1 = orderService.selectOrdersByPassenger(passenger.getIdPassenger());
                orders.addAll(orders1);
            }
            user.setOrders(orders);
            user.setPassengers(passengers);
            user.setUserNotifies(userNotifies);
        }
        return user;
    }
    @Override
    public User getUserByName(String name) {
        User user=userMapper.selectUserByName(name);
        if(user!=null) {
            List<Passenger> passengers = passengerService.getPassengerByUser(user.getIdUser());
            List<Order> orders=new ArrayList<>();
            List<UserNotify> userNotifies=userNotifyService.getUserNotifyByUserId(user.getIdUser());
            for(Passenger passenger : passengers) {
                List<Order> orders1 = orderService.selectOrdersByPassenger(passenger.getIdPassenger());
                orders.addAll(orders1);
            }
            user.setOrders(orders);
            user.setPassengers(passengers);
            user.setUserNotifies(userNotifies);
        }
        return user;
    }
    @Override
    public User getUserByEmail(String email) {
        User user=userMapper.selectUserByEmail(email);
        if(user!=null) {
            List<Passenger> passengers = passengerService.getPassengerByUser(user.getIdUser());
            List<Order> orders=new ArrayList<>();
            List<UserNotify> userNotifies=userNotifyService.getUserNotifyByUserId(user.getIdUser());
            for(Passenger passenger : passengers) {
                List<Order> orders1 = orderService.selectOrdersByPassenger(passenger.getIdPassenger());
                orders.addAll(orders1);
            }
            user.setOrders(orders);
            user.setPassengers(passengers);
            user.setUserNotifies(userNotifies);
        }
        return user;
    }
    @Override
    public User getUserByAccount(String account) {
        if (account.matches("^1[3-9]\\d{9}$")) {
            return getUserByPhone(account);
        }
        else if (account.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return getUserByEmail(account);
        }
        else {
            return getUserByName(account);
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users=userMapper.selectAllUsers();
        for (User user : users) {
            List<Passenger> passengers=passengerService.getPassengerByUser(user.getIdUser());
            List<Order> orders=new ArrayList<>();
            List<UserNotify> userNotifies=userNotifyService.getUserNotifyByUserId(user.getIdUser());
            for(Passenger passenger : passengers) {
                List<Order> orders1 = orderService.selectOrdersByPassenger(passenger.getIdPassenger());
                orders.addAll(orders1);
            }
            user.setOrders(orders);
            user.setPassengers(passengers);
            user.setUserNotifies(userNotifies);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        Passenger passenger= new Passenger();
        passenger.setNamePassenger(user.getName());
        passenger.setTypeIdCard(user.getTypeIdCard());
        passenger.setIdCard(user.getIdCard());
        passenger.setTypePhone(user.getTypePhone());
        passenger.setPhone(user.getPhone());
        passenger.setTypeVerify("已通过");
        passenger.setPreferType(user.getPreferType());
        userMapper.insertUser(user);
        passenger.setIdUser(user.getIdUser());
        passengerService.addPassenger(passenger);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
