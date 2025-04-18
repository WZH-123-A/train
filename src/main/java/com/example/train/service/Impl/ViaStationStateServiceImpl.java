package com.example.train.service.Impl;

import com.example.train.service.ObserverService;
import com.example.train.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaStationStateServiceImpl implements SubjectService {
    private List<ObserverService> observers=new ArrayList<>();
    @Override
    public void registerObserver(ObserverService observer)
    {
        observers.add(observer);
    }
    @Override
    public void removeObserver(ObserverService observer)
    {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers(List<Integer> users, String NotifyContent, String Date)
    {
        for(ObserverService observer:observers){
            observer.update(users,NotifyContent,Date);
        }
    }
}
