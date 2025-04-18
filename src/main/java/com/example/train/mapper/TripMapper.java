package com.example.train.mapper;

import com.example.train.bean.Trip;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TripMapper {

    @Select("SELECT * FROM trip WHERE IdTrip = #{id} AND Date = #{date}")
    Trip selectTripById(int id, String date);

    @Select("SELECT * FROM trip")
    List<Trip> selectAllTrips();

    @Insert("INSERT INTO trip (IdTrip, IdTrain,Date) VALUES (#{idTrip}, #{idTrain},#{date})")
    @Options(useGeneratedKeys = true, keyProperty = "IdTrip")
    void insertTrip(Trip trip);

    @Update("UPDATE trip SET IdTrain = #{idTrain} WHERE IdTrip = #{idTrip} AND Date = #{date}")
    void updateTrip(Trip trip);

    @Delete("DELETE FROM trip WHERE IdTrip = #{id} AND Date = #{date}")
    void deleteTrip(int id,String date);
}
