package com.example.train.service;


import com.example.train.bean.User;

import java.util.ArrayList;
import java.util.List;

public interface SubjectService {
    void registerObserver(ObserverService observer);
    void removeObserver(ObserverService observer);
    void notifyObservers(List<Integer> users,String NotifyContent,String Date);
}
