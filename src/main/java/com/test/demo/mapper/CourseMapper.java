package com.test.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.demo.model.Course;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {


    @Select("select course_id from score GROUP BY course_id")
    List<Integer> getCouseFromScore();

}