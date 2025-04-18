package com.example.train.mapper;

import com.example.train.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM orders WHERE IdPassenger=#{IdPassenger} AND IdSeat=#{IdSeat} ")
    Order selectOrderByIdPassengerAndIdSeat(@Param("IdPassenger") int IdPassenger, @Param("IdSeat") int IdSeat);
    @Select("SELECT * FROM orders WHERE IdPassenger=#{IdPassenger} AND IdTrip=#{IdTrip} AND Date=#{Date} AND IdDeparture=#{IdDeparture} AND IdTerminal=#{IdTerminal}")
    Order selectOrderByIdPassengerAndIdTripAndDateAndIdDepartureAndIdTerminal(@Param("IdPassenger") int IdPassenger, @Param("IdTrip") int IdTrip,@Param("Date") String Date,@Param("IdDeparture") int IdDeparture,@Param("IdTerminal") int IdTerminal);
    @Select("SELECT * FROM orders WHERE IdSeat=#{IdSeat}")
    List<Order> selectOrdersByIdSeat(int IdSeat);
    @Select("SELECT * FROM orders WHERE IdPassenger = #{IdPassenger}")
    List<Order> selectOrdersByPassenger(int IdPassenger);
    @Select("SELECT * FROM orders WHERE IdTrip = #{IdTrip} AND Date=#{Date} AND IdDeparture=#{IdDeparture}")
    List<Order> selectOrdersByTripIdAndDateAndIdDeparture(int IdTrip,String Date,int IdDeparture);
    @Insert("INSERT INTO orders (IdPassenger, IdSeat, Date, IdDeparture, IdTerminal, Cost, Status,SeatType,IdTrip) VALUES (#{idPassenger}, #{idSeat}, #{date}, #{idDeparture}, #{idTerminal}, #{cost}, #{status}, #{seatType},#{idTrip})")
    @Options(useGeneratedKeys = true, keyProperty = "Id")
    void insertOrder(Order order);


    @Delete("DELETE FROM orders WHERE IdPassenger = #{IdPassenger} AND IdSeat = #{IdSeat}")
    void deleteOrder(int IdPassenger,int IdSeat);
}
