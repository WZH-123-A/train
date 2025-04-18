package com.example.train.service.Impl;

import com.example.train.bean.User;
import com.example.train.bean.UserNotify;
import com.example.train.mapper.UserNotifyMapper;
import com.example.train.service.ObserverService;
import com.example.train.service.UserNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNotifyServiceImpl implements UserNotifyService, ObserverService {
    @Autowired
    private UserNotifyMapper userNotifyMapper;
    @Override
    public UserNotify getUserNotifyById(int idNotify) {
        return userNotifyMapper.getUserNotifyById(idNotify);
    }
    @Override
    public void addUserNotify(UserNotify userNotify) {
        userNotifyMapper.addUserNotify(userNotify);
    }
    @Override
    public void updateUserNotify(UserNotify userNotify) {
        userNotifyMapper.updateUserNotify(userNotify);
    }
    @Override
    public void deleteUserNotify(int idNotify) {
        userNotifyMapper.deleteUserNotify(idNotify);
    }
    @Override
    public List<UserNotify> getUserNotifyByUserId(int idUser) {
        return userNotifyMapper.getUserNotifyByUserId(idUser);
    }
    @Override
    public void update(List<Integer> users, String NotifyContent,String Date) {
        for(int user:users){
            UserNotify userNotify=new UserNotify();
            userNotify.setIdUser(user);
            userNotify.setNotifyContent(NotifyContent);
            userNotify.setDate(Date);
            userNotifyMapper.addUserNotify(userNotify);
        }
    }
}

