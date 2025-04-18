package com.example.train.mapper;

import com.example.train.bean.Station;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StationMapper {
    @Select("SELECT * FROM station WHERE IdStation = #{id}")
    Station selectStationById(int id);

    @Select("SELECT * FROM station")
    List<Station> selectAllStations();
    @Select("SELECT * FROM station WHERE City = #{city}")
    List<Station> selectStationsByCity(String city);
    @Insert("INSERT INTO station (IdStation, NameStation, City, CoordinatesX, CoordinatesY) " +
            "VALUES (#{idStation}, #{nameStation}, #{city}, #{coordinatesX}, #{coordinatesY})")
    @Options(useGeneratedKeys = true, keyProperty = "IdStation")
    void insertStation(Station station);

    @Update("UPDATE station SET NameStation = #{nameStation}, City = #{city}, CoordinatesX = #{coordinatesX}, CoordinatesY = #{coordinatesY} " +
            "WHERE IdStation = #{idStation}")
    void updateStation(Station station);

    @Delete("DELETE FROM station WHERE IdStation = #{id}")
    void deleteStation(int id);
}
