package com.example.train.mapper;

import com.example.train.bean.Passenger;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PassengerMapper {
    @Select("SELECT * FROM passenger WHERE IdPassenger = #{idPassenger}")
    Passenger selectPassengerById(int idPassenger);
    @Select("SELECT * FROM passenger WHERE IdCard= #{idCard} AND IdUser=#{idUser}")
    Passenger selectPassengerByIdCardAndIdUser(String idCard, int idUser);
    @Select("SELECT * FROM passenger")
    List<Passenger> selectAllPassengers();
    @Select("SELECT * FROM passenger WHERE IdUser=#{idUser}")
    List<Passenger> selectPassengerByUser(int idUser);

    @Insert("INSERT INTO passenger (IdPassenger, NamePassenger, TypeIdCard, IdCard, TypePhone, Phone, TypeVerify, PreferType,IdUser) " +
            "VALUES (#{idPassenger}, #{namePassenger}, #{typeIdCard}, #{idCard}, #{typePhone}, #{phone}, #{typeVerify}, #{preferType},#{idUser})")
    @Options(useGeneratedKeys = true, keyProperty = "idPassenger")
    void insertPassenger(Passenger passenger);

    @Update("UPDATE passenger SET NamePassenger=#{namePassenger}, TypeIdCard=#{typeIdCard}, IdCard=#{idCard}, " +
            "TypePhone=#{typePhone}, Phone=#{phone}, TypeVerify=#{typeVerify}, PreferType=#{preferType},IdUser=#{idUser} WHERE IdPassenger=#{idPassenger}")
    void updatePassenger(Passenger passenger);

    @Delete("DELETE FROM passenger WHERE IdPassenger = #{idPassenger}")
    void deletePassenger(int idPassenger);
}
