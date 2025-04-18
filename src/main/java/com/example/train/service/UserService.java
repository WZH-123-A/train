package com.example.train.service;

import com.example.train.bean.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    User getUserByIdCard(String idCard);
    User getUserByName(String name);
    User getUserByPhone(String phone);
    User getUserByEmail(String email);
    User getUserByAccount(String account);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
