package com.example.train.service;

import com.example.train.bean.Connection;

import java.util.List;

public interface ConnectionService {
    Connection getConnectionByStations(int stationOne, int stationTwo);
    List<Connection> getAllConnections();
    List<Connection> getConnectionsByStationOne(int stationOne);
    List<Connection> getConnectionsByStationTwo(int stationTwo);
    void addConnection(Connection connection);
    void updateConnection(Connection connection);
    void deleteConnection(int stationOne, int stationTwo);
}