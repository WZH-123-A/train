package com.example.train.mapper;

import com.example.train.bean.UserNotify;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserNotifyMapper {
    @Select("SELECT * FROM usernotify WHERE IdNotify = #{idNotify}")
    UserNotify getUserNotifyById(int idNotify);
    @Select("SELECT * FROM usernotify WHERE IdUser = #{idUser}")
    List<UserNotify> getUserNotifyByUserId(int idUser);
    @Insert("INSERT INTO usernotify(IdUser, NotifyContent, Date) VALUES(#{idUser}, #{notifyContent}, #{date})")
    void addUserNotify(UserNotify userNotify);

    @Update("UPDATE usernotify SET IdUser = #{idUser}, NotifyContent = #{notifyContent}, Date = #{date} WHERE IdNotify = #{idNotify}")
    void updateUserNotify(UserNotify userNotify);

    @Delete("DELETE FROM usernotify WHERE IdNotify = #{idNotify}")
    void deleteUserNotify(int idNotify);
}
