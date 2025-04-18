package com.example.train.service.Impl;

import com.example.train.bean.Edge;
import com.example.train.bean.RailwayGraph;
import com.example.train.bean.Station;
import com.example.train.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetAllWayServiceImpl {
    @Autowired
    private RailwayGraphServiceImpl railwayGraphService;
    @Autowired
    private StationServiceImpl stationService;
    public void getAllWay(RailwayGraph railwayGraph,Station start, Station end, Map<List<Station>,List<Double>> paths,
                          List<Station> path,List<Double> distance){
        path.add(start);
        if(start.equals(end)){
            paths.put(new ArrayList<>(path), new ArrayList<>(distance));
        }else{
            List<Edge> nebor= railwayGraph.getAdjacencyList().get(start);
            if(nebor==null) return;
            for(Edge edge:nebor){
                if(!path.contains(edge.getTargetStation())) {
                    distance.add(edge.getDistance());
                    getAllWay(railwayGraph,edge.getTargetStation(), end, paths, path,distance);
                }
            }
        }
        if(!distance.isEmpty()) {
            distance.remove(distance.size() - 1);
        }
        path.remove(path.size()-1);
    }
    public Map<List<Station>,List<Double>> getAllWays(int start, int end){
        RailwayGraph railwayGraph=railwayGraphService.init();
        Station startStation=stationService.getStationById(start);
        Station endStation=stationService.getStationById(end);
        Map<List<Station>,List<Double>> paths=new HashMap<>();
        getAllWay(railwayGraph,startStation,endStation,paths,new ArrayList<>(),new ArrayList<>());
        return paths;
    }
}
