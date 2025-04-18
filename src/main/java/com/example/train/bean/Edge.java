package com.example.train.bean;


import lombok.Data;

@Data
public class Edge {
    Station targetStation;
    Double distance;
    public Edge(Station targetStation,Double distance){
        this.targetStation = targetStation;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "targetStation='" + targetStation + '\'' +
                ", distance=" + distance +
                '}';
    }
}
