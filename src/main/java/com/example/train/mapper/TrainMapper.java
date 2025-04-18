package com.example.train.mapper;

import com.example.train.bean.Train;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainMapper {

    @Select("SELECT * FROM train WHERE IdTrain = #{id}")
    Train selectTrainById(int id);

    @Select("SELECT * FROM train")
    List<Train> selectAllTrains();

    @Insert("INSERT INTO train (IdTrain, TypeTrain) VALUES (#{idTrain}, #{typeTrain})")
    @Options(useGeneratedKeys = true, keyProperty = "IdTrain")
    void insertTrain(Train train);

    @Update("UPDATE train SET TypeTrain = #{TypeTrain} WHERE IdTrain = #{idTrain}")
    void updateTrain(Train train);

    @Delete("DELETE FROM train WHERE IdTrain = #{id}")
    void deleteTrain(int id);
}