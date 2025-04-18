package com.example.train.service;


import com.example.train.bean.User;

import java.util.List;

public interface ObserverService {
    void update(List<Integer> users,String NotifyContent,String Date);
}
