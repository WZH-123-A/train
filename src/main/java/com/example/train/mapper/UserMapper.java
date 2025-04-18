package com.example.train.mapper;

import com.example.train.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE IdUser = #{id}")
    User selectUserById(int id);

    @Select("SELECT * FROM user WHERE IdCard = #{idCard}")
    User selectUserByIdCard(String idCard);
    @Select("SELECT * FROM user WHERE NameUser= #{nameUser}")
    User selectUserByName(String nameUser);
    @Select("SELECT * FROM user WHERE Phone= #{phone}")
    User selectUserByPhone(String phone);
    @Select("SELECT * FROM user WHERE Email= #{email}")
    User selectUserByEmail(String email);
    @Select("SELECT * FROM user")
    List<User> selectAllUsers();

    @Insert("INSERT INTO user (IdUser, NameUser, PassWord, Phone,IdCard,TypePhone,TypeIdCard,Email,Name,PreferType) VALUES (#{idUser}, #{nameUser}, #{passWord}, #{phone},#{idCard},#{typePhone},#{typeIdCard},#{email},#{name},#{preferType})")
    @Options(useGeneratedKeys = true, keyProperty = "IdUser")
    void insertUser(User user);

    @Update("UPDATE user SET NameUser = #{nameUser}, PassWord = #{passWord}, Phone = #{phone},IdCard=#{idCard},TypePhone=#{typePhone},TypeIdCard=#{typeIdCard},PreferType=#{preferType},Name=#{name},Email=#{email} WHERE IdUser = #{idUser}")
    void updateUser(User user);

    @Delete("DELETE FROM user WHERE IdUser = #{id}")
    void deleteUser(int id);
}
