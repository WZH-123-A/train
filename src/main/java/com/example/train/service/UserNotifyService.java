package com.example.train.service;

import com.example.train.bean.UserNotify;

import java.util.List;

public interface UserNotifyService {
    UserNotify getUserNotifyById(int idNotify);
    List<UserNotify> getUserNotifyByUserId(int idUser);
    void addUserNotify(UserNotify userNotify);
    void updateUserNotify(UserNotify userNotify);
    void deleteUserNotify(int idNotify);
}
