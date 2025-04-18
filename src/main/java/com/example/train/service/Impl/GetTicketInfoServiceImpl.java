package com.example.train.service.Impl;

import com.example.train.bean.*;
import com.example.train.mapper.StationMapper;
import com.example.train.mapper.TripMapper;
import com.example.train.mapper.ViaStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetTicketInfoServiceImpl {
    @Autowired
    private ViaStationServiceImpl viaStationService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private TripServiceImpl tripService;
    public List<Trip> getTrips(List<Station> path,String date){
        Map<Trip,Boolean> map=new HashMap<>();
        List<ViaStation> viaStationList=viaStationService.getViaStationsByStationId(path.get(0).getIdStation(),date);
        for(ViaStation viaStation:viaStationList){
            map.put(tripService.getTripById(viaStation.getIdTrip(),date),false);
        }
        for(int i=1;i<path.size();i++){
            viaStationList=viaStationService.getViaStationsByStationId(path.get(i).getIdStation(),date);
            for(ViaStation viaStation:viaStationList){
                Trip trip=tripService.getTripById(viaStation.getIdTrip(),date);
                if(map.containsKey(trip)) {
                    if(viaStationService.getViaStationById(trip.getIdTrip(),path.get(i).getIdStation(),date).
                            getSequence()==viaStationService.getViaStationById(trip.getIdTrip(),path.get(i-1).
                            getIdStation(),date).getSequence()+1) {
                        map.compute(trip, (k, v) -> true);
                    }
                }
            }
            List<Trip> trips=map.keySet().stream().toList();
            for(Trip trip: trips){
                if(!map.get(trip)){
                    map.remove(trip);
                }
                else{
                    map.compute(trip,(k, v) -> false);
                }
            }
        }
        return map.keySet().stream().toList();
    }

    public List<TicketInfo> getTicketInfo(Map<List<Station>,List<Double>> paths,String date) {
        List<TicketInfo> ticketInfos=new ArrayList<>();
        List<Trip> tripList=new ArrayList<>();
        int businessSeatNumber=0;
        int firstClassSeatNumber=0;
        int secondClassSeatNumber=0;
        int softSleepSeatNumber=0;
        int hardSleepSeatNumber=0;
        int hardSeatNumber=0;
        int softSeatNumber=0;
        double distance=0;
        for(Map.Entry<List<Station>,List<Double>> entry:paths.entrySet()){
            tripList=getTrips(entry.getKey(),date);
            for(Trip trip:tripList){
                businessSeatNumber=0;
                firstClassSeatNumber=0;
                secondClassSeatNumber=0;
                softSleepSeatNumber=0;
                hardSleepSeatNumber=0;
                hardSeatNumber=0;
                softSeatNumber=0;
                distance=0;
                TicketInfo ticketInfo=new TicketInfo();
                ticketInfo.setPrice(10);
                ticketInfo.setTrip(trip);
                ticketInfo.setDepartureTime(viaStationService.getViaStationById(trip.getIdTrip(),
                        entry.getKey().get(0).getIdStation(),date));
                ticketInfo.setDestinationTime(viaStationService.getViaStationById(trip.getIdTrip(),
                        entry.getKey().get(entry.getKey().size()-1).getIdStation(),date));
                ticketInfo.setDepartureStation(entry.getKey().get(0));
                ticketInfo.setDestinationStation(entry.getKey().get(entry.getKey().size()-1));
                List<ViaStation> viaStationList=viaStationService.getViaStationsByTripIdOrderBySequence(trip.getIdTrip(),date);
                ticketInfo.setViaStations(viaStationList);
                for(Carriage carriage:trip.getCarriages()){
                    for(Seat seat:carriage.getSeats()){
                        if(orderService.selectOrdersByIdSeat(seat.getIdSeat()).isEmpty()){
                            switch (carriage.getSeatType()) {
                                case "商务座" -> businessSeatNumber++;
                                case "一等座" -> firstClassSeatNumber++;
                                case "二等座" -> secondClassSeatNumber++;
                                case "软卧" -> softSleepSeatNumber++;
                                case "硬卧" -> hardSleepSeatNumber++;
                                case "硬座" -> hardSeatNumber++;
                                case "软座" -> softSeatNumber++;
                            }
                        }
                        else{
                            System.out.println(3);
                            System.out.println(orderService.selectOrdersByIdSeat(seat.getIdSeat()));
                            int flag=0;
                            for(Order order:orderService.selectOrdersByIdSeat(seat.getIdSeat())){
                                System.out.println(4);
                                if((viaStationService.getViaStationById(trip.getIdTrip(),entry.getKey().get(0).
                                        getIdStation(),date).getSequence()>=viaStationService.
                                        getViaStationById(trip.getIdTrip(),order.getIdDeparture(),date).
                                        getSequence()&&viaStationService.getViaStationById(trip.getIdTrip(),entry.getKey().
                                        get(0).getIdStation(),date).getSequence()<viaStationService.getViaStationById(trip.
                                        getIdTrip(),order.getIdTerminal(),date).getSequence())|| (viaStationService.
                                        getViaStationById(trip.getIdTrip(),entry.getKey().get(entry.getKey().size()-1).
                                                getIdStation(),date).getSequence()>viaStationService.
                                        getViaStationById(trip.getIdTrip(),order.getIdDeparture(),date).
                                        getSequence()&&viaStationService.getViaStationById(trip.getIdTrip(),entry.getKey().
                                        get(entry.getKey().size()-1).getIdStation(),date).getSequence()<=viaStationService.
                                        getViaStationById(trip.getIdTrip(),order.getIdTerminal(),date).getSequence())){
                                    flag=1;
                                    System.out.println(5);
                                    break;
                                }
                            }
                            System.out.println(6);
                            if(flag==0){
                                switch (carriage.getSeatType()) {
                                    case "商务座" -> businessSeatNumber++;
                                    case "一等座" -> firstClassSeatNumber++;
                                    case "二等座" -> secondClassSeatNumber++;
                                    case "软卧" -> softSleepSeatNumber++;
                                    case "硬卧" -> hardSleepSeatNumber++;
                                    case "硬座" -> hardSeatNumber++;
                                    case "软座" -> softSeatNumber++;
                                }
                            }
                        }
                    }
                }
                ticketInfo.setBusinessSeatNumber(businessSeatNumber);
                ticketInfo.setFirstClassSeatNumber(firstClassSeatNumber);
                ticketInfo.setSecondClassSeatNumber(secondClassSeatNumber);
                ticketInfo.setSoftSleepSeatNumber(softSleepSeatNumber);
                ticketInfo.setHardSleepSeatNumber(hardSleepSeatNumber);
                ticketInfo.setHardSeatNumber(hardSeatNumber);
                ticketInfo.setSoftSeatNumber(softSeatNumber);
                ticketInfos.add(ticketInfo);
                for(Double dis:entry.getValue()){
                    distance+=dis;
                }
                ticketInfo.setDistance(distance);
            }
        }
        return ticketInfos;
    }
}
