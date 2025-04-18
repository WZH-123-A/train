package com.example.train.controller;

import com.example.train.bean.Station;
import com.example.train.bean.TicketInfo;
import com.example.train.bean.Trip;
import com.example.train.service.Impl.GetAllWayServiceImpl;
import com.example.train.service.Impl.GetTicketInfoServiceImpl;
import com.example.train.service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private GetAllWayServiceImpl getAllWayService;
    @Autowired
    private GetTicketInfoServiceImpl getTicketInfoService;
    @Autowired
    private StationServiceImpl stationService;
    @GetMapping("/ticket")
    public ResponseEntity<Map<List<Station>,List<Double>>> getTicketInfo() {
        Map<List<Station>,List<Double>> paths= getAllWayService.getAllWays(1, 9);
        return ResponseEntity.ok(paths);
    }
    @GetMapping("/ticketinfo/{start}/{end}/{date}")
    public ResponseEntity<List<TicketInfo>> getTicketInfo(@PathVariable String start, @PathVariable String end,@PathVariable String date) {
        Map<List<Station>,List<Double>> paths=new HashMap<>();
        List<Station> starts=stationService.getStationsByCity(start);
        List<Station> ends=stationService.getStationsByCity(end);

        for(Station s:starts){
            for(Station e:ends){
                paths.putAll(getAllWayService.getAllWays(s.getIdStation(), e.getIdStation()));
            }
        }
        return ResponseEntity.ok(getTicketInfoService.getTicketInfo(paths,date));
    }
}
