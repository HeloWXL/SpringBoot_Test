package com.test.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.demo.model.Exam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ExamMapper extends BaseMapper<Exam> {

    /**
     * 查询该教师发布最晚的那么考试
     * @param teacherId
     * @return
     */
    @Select("SELECT exam_id,MAX(create_time) FROM exam where teacher_id= #{teacherId}")
    public Exam getLastTimeExam(@Param("teacherId") Integer teacherId);
}