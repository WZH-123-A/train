package com.example.train.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class RailwayGraph {
    private Map<Station, List<Edge>> adjacencyList= new HashMap<>();
    public void addEdge(Station stationA, Station stationB, Double distance){
        adjacencyList.computeIfAbsent(stationA,k->new ArrayList<>()).add(new Edge(stationB,distance));
        adjacencyList.computeIfAbsent(stationB,k->new ArrayList<>()).add(new Edge(stationA,distance));
    }
    public Map<Station, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}
