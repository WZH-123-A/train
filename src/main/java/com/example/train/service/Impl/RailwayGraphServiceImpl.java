package com.example.train.service.Impl;

import com.example.train.bean.Connection;
import com.example.train.bean.RailwayGraph;
import com.example.train.mapper.ConnectionMapper;
import com.example.train.mapper.StationMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RailwayGraphServiceImpl {
    @Autowired
    private ConnectionMapper connectionMapper;
    @Autowired
    private StationMapper stationMapper;

    private RailwayGraph railwayGraph;
//    public RailwayGraphServiceImpl(ConnectionMapper connectionMapper){
//        this.connectionMapper=connectionMapper;
//    }
//    @PostConstruct
    public RailwayGraph init(){
        railwayGraph=new RailwayGraph();
        List<Connection> connections=connectionMapper.selectAllConnections();
        for(Connection connection:connections){
            railwayGraph.addEdge(stationMapper.selectStationById(connection.getStationOne()),stationMapper.selectStationById(connection.getStationTwo()),connection.getDistance());
        }
        return railwayGraph;
    }
}
