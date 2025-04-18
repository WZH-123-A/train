package com.example.train.service.Impl;

import com.example.train.bean.Connection;
import com.example.train.mapper.ConnectionMapper;
import com.example.train.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    private ConnectionMapper connectionMapper;
    @Override
    public List<Connection> getAllConnections() {
        return connectionMapper.selectAllConnections();
    }
    @Override
    public Connection getConnectionByStations(int stationOne, int stationTwo) {
        return connectionMapper.selectConnectionByStations(stationOne, stationTwo);
    }

    @Override
    public List<Connection> getConnectionsByStationOne(int stationOne) {
        return connectionMapper.selectConnectionsByStationOne(stationOne);
    }

    @Override
    public List<Connection> getConnectionsByStationTwo(int stationTwo) {
        return connectionMapper.selectConnectionsByStationTwo(stationTwo);
    }

    @Override
    public void addConnection(Connection connection) {
        connectionMapper.insertConnection(connection);
    }

    @Override
    public void updateConnection(Connection connection) {
        connectionMapper.updateConnection(connection);
    }

    @Override
    public void deleteConnection(int stationOne, int stationTwo) {
        connectionMapper.deleteConnection(stationOne, stationTwo);
    }
}
