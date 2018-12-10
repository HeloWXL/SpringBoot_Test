package com.test.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.demo.model.Score;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ScoreMapper extends BaseMapper<Score> {

    @Select("select MAX(score)\n" +
            "from score \n" +
            "where course_id = #{courseId}")
    int getMaxScore(int courseId);

    @Select("select MIN(score)\n" +
            "from score \n" +
            "where course_id = #{courseId}")
    int getMinScore(int courseId);

    /**
     * 学生提交试卷-更新试卷
     * @param score
     * @param courseId
     * @param studentId
     * @return
     */
    @Update("update score set score = #{score} ,is_test = 1 where course_id = #{courseId}  and student_id = #{studentId}")
    int updateScore(@Param("score") Integer score , @Param("courseId") Integer courseId, @Param("studentId") Integer studentId);


    /**
     * 添加考试信息
     * @param examId
     * @param courseId
     * @return
     */
    @Update("update score set exam_id = #{examId} where course_id = #{courseId}")
    int updateScore(@Param("examId") Integer examId,@Param("courseId") Integer courseId);
}