package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.demo.mapper.ExamMapper;
import com.test.demo.model.Exam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxl
 * @date 2018/12/2
 */
@Service
public class ExamService {
    @Resource
    private ExamMapper examMapper;


    /**
     * 添加试卷
     * @param exam
     * @return
     */
    public int insertExam(Exam exam){
        return examMapper.insert(exam);
    }


    /**
     * 根据教师的ID查询试卷信息
     * @return
     */
    public List<Exam> getAllExams(Integer tid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);
        return examMapper.selectList(entityWrapper);

    }

    /**
     * 根据考试的ID查询考试
     * @param examId
     * @return
     */
    public Exam getExamsByExamId(Integer examId){
        Exam exam = new Exam();
        exam.setExamId(examId);
        return examMapper.selectOne(exam);
    }

    /**
     * 查询该教师发布最晚的那么考试
     * @param teacherId
     * @return
     */
    public  Exam  getLastTimeExam(Integer teacherId){
        return examMapper.getLastTimeExam(teacherId);
    }
}
