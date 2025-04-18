package com.example.train.mapper;

import com.example.train.bean.Connection;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ConnectionMapper {
    @Select("SELECT * FROM connection")
    List<Connection> selectAllConnections();

    @Select("SELECT * FROM connection WHERE StationOne = #{stationOne} AND StationTwo = #{stationTwo}")
    Connection selectConnectionByStations(int stationOne, int stationTwo);

    @Select("SELECT * FROM connection WHERE StationOne = #{stationOne}")
    List<Connection> selectConnectionsByStationOne(int stationOne);

    @Select("SELECT * FROM connection WHERE StationTwo = #{stationTwo}")
    List<Connection> selectConnectionsByStationTwo(int stationTwo);

    @Insert("INSERT INTO connection (StationOne, StationTwo, Distance) VALUES (#{stationOne}, #{stationTwo}, #{distance})")
    void insertConnection(Connection connection);

    @Update("UPDATE connection SET Distance = #{distance} WHERE StationOne = #{stationOne} AND StationTwo = #{stationTwo}")
    void updateConnection(Connection connection);

    @Delete("DELETE FROM connection WHERE StationOne = #{stationOne} AND StationTwo = #{stationTwo}")
    void deleteConnection(int stationOne, int stationTwo);
}