package com.test.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.demo.model.Score;
import org.apache.ibatis.annotations.Select;

public interface ScoreMapper extends BaseMapper<Score> {

    @Select("select MAX(score)\n" +
            "from score \n" +
            "where course_id = #{courseId}")
    int getMaxScore(int courseId);

    @Select("select MIN(score)\n" +
            "from score \n" +
            "where course_id = #{courseId}")
    int getMinScore(int courseId);
}