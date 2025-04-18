package com.example.train.mapper;

import com.example.train.bean.ViaStation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ViaStationMapper {

    @Select("SELECT * FROM viastation WHERE IdTrip = #{idTrip} AND IdStation = #{idStation} AND Date=#{Date}")
    ViaStation selectViaStationById(int idTrip, int idStation,String Date);

    @Select("SELECT * FROM viastation WHERE IdTrip = #{idTrip} AND Date=#{Date}")
    List<ViaStation> selectViaStationsByTripId(int idTrip,String Date);

    @Select("SELECT * FROM viastation WHERE IdTrip = #{idTrip} AND Date=#{Date} ORDER BY Sequence")
    List<ViaStation> selectViaStationsByTripIdOrderBySequence(int idTrip,String Date);
    @Select("SELECT * FROM viastation WHERE IdStation = #{idStation} AND Date=#{Date}")
    List<ViaStation> selectViaStationsByStationId(int idStation,String Date);

    @Insert("INSERT INTO viastation (IdTrip, IdStation, ArrivalTime, DepartureTime, Sequence, type,Date,State) VALUES (#{idTrip}, #{idStation}, #{arrivalTime}, #{departureTime}, #{sequence}, #{type},#{date},#{state})")
    @Options(useGeneratedKeys = true, keyProperty = "IdTrip")
    void insertViaStation(ViaStation viaStation);

    @Update("UPDATE viastation SET ArrivalTime = #{arrivalTime}, DepartureTime = #{departureTime}, Sequence = #{sequence}, type = #{type},State=#{state} WHERE IdTrip = #{idTrip} AND IdStation = #{idStation} AND Date=#{date}")
    void updateViaStation(ViaStation viaStation);

    @Delete("DELETE FROM viastation WHERE IdTrip = #{idTrip} AND IdStation = #{idStation} AND Date=#{Date}")
    void deleteViaStation(int idTrip, int idStation,String Date);
}
