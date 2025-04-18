package com.example.train.controller;

import com.example.train.bean.Connection;
import com.example.train.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;
    @GetMapping("/all")
    public ResponseEntity<List<Connection>> getAllConnections() {
        List<Connection> connections = connectionService.getAllConnections();
        if (!connections.isEmpty()) {
            return ResponseEntity.ok(connections);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/stations/{stationOne}/{stationTwo}")
    public ResponseEntity<Connection> getConnectionByStations(@PathVariable int stationOne, @PathVariable int stationTwo) {
        Connection connection = connectionService.getConnectionByStations(stationOne, stationTwo);
        if (connection != null) {
            return ResponseEntity.ok(connection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stationOne/{stationOne}")
    public ResponseEntity<List<Connection>> getConnectionsByStationOne(@PathVariable int stationOne) {
        List<Connection> connections = connectionService.getConnectionsByStationOne(stationOne);
        if (!connections.isEmpty()) {
            return ResponseEntity.ok(connections);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stationTwo/{stationTwo}")
    public ResponseEntity<List<Connection>> getConnectionsByStationTwo(@PathVariable int stationTwo) {
        List<Connection> connections = connectionService.getConnectionsByStationTwo(stationTwo);
        if (!connections.isEmpty()) {
            return ResponseEntity.ok(connections);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Connection> addConnection(@RequestBody Connection connection) {
        connectionService.addConnection(connection);
        return ResponseEntity.status(HttpStatus.CREATED).body(connection);
    }

    @PutMapping
    public ResponseEntity<Connection> updateConnection(@RequestBody Connection updateConnection) {
        Connection connection = connectionService.getConnectionByStations(updateConnection.getStationOne(), updateConnection.getStationTwo());
        if (connection != null) {
            connectionService.updateConnection(updateConnection);
            return ResponseEntity.ok(updateConnection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/stations/{stationOne}/{stationTwo}")
    public ResponseEntity<Void> deleteConnection(@PathVariable int stationOne, @PathVariable int stationTwo) {
        Connection connection = connectionService.getConnectionByStations(stationOne, stationTwo);
        if (connection != null) {
            connectionService.deleteConnection(stationOne, stationTwo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
