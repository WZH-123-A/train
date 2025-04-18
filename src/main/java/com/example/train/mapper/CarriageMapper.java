package com.example.train.mapper;

import com.example.train.bean.Carriage;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CarriageMapper {

    @Select("SELECT * FROM carriage WHERE IdCarriage = #{idCarriage}")
    Carriage selectCarriageById(int idCarriage);

    @Select("SELECT * FROM carriage WHERE IdTrip = #{idTrip} AND Date=#{date}")
    List<Carriage> selectCarriageByTripAndDate(int idTrip,String date);

    @Insert("INSERT INTO carriage (IdCarriage, NumberSeat, IdTrip,SeatType,Date,CarriageNumber) VALUES (#{idCarriage}, #{numberSeat}, #{idTrip},#{seatType},#{date})},#{carriageNumber})")
    void insertCarriage(Carriage carriage);

    @Update("UPDATE carriage SET NumberSeat = #{numberSeat},  IdTrip = #{idTrip},SeatType=#{seatType},Date=#{date},CarriageNumber=#{carriageNumber} WHERE IdCarriage = #{idCarriage}")
    void updateCarriage(Carriage carriage);

    @Delete("DELETE FROM carriage WHERE IdCarriage = #{idCarriage}")
    void deleteCarriage(int idCarriage);
}