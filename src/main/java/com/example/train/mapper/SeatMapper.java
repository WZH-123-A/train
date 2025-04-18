package com.example.train.mapper;
import com.example.train.bean.Seat;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeatMapper {

    @Select("SELECT * FROM seats WHERE IdSeat = #{IdSeat}")
    Seat selectSeatById(int IdSeat);


    @Select("SELECT * FROM seats WHERE IdCarriage = #{IdCarriage}")
    List<Seat> selectSeatsByCarriage(int IdCarriage);

    @Insert("INSERT INTO seats (IdSeat, IdCarriage,SeatNumber) VALUES (#{idSeat}, #{idCarriage},#{seatNumber}))")
    @Options(useGeneratedKeys = true, keyProperty = "IdSeat")
    void insertSeat(Seat seat);

    @Update("UPDATE seats SET IdCarriage = #{IdCarriage} ,SeatNumber=#{SeatNumber} WHERE IdSeat = #{IdSeat}")
    void updateSeat(Seat seat);

    @Delete("DELETE FROM seats WHERE IdSeat = #{IdSeat}")
    void deleteSeat(int IdSeat);
}