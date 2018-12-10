package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.demo.mapper.ScoreMapper;
import com.test.demo.model.Score;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/27 14:49
 */
@Service
public class ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    /**
     * 根据课程的ID查询成绩列表
     * @param courseId
     * @return List<Score>
     */
    public List<Score>  getScoreByCourseId(Integer courseId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("course_id",courseId);
        return scoreMapper.selectList(entityWrapper);
    }

    /**
     * 根据课程的ID查询分段成绩得数量------绘制通知直方图
     * @param courseId
     * @return List<Integer>
     */
    public List<Integer> getCountByCourseId(Integer courseId){
        List<Integer> list = new ArrayList<>();

        int s1 = scoreMapper.selectCount( new EntityWrapper<Score>().eq("course_id",courseId).lt("score",50));
        list.add(s1);
        int s2 = scoreMapper.selectCount(new EntityWrapper<Score>().eq("course_id",courseId).between("score",50,60));
        list.add(s2);
        int s3 = scoreMapper.selectCount(new EntityWrapper<Score>().eq("course_id",courseId).between("score",60,70));
        list.add(s3);
        int s4 = scoreMapper.selectCount(new EntityWrapper<Score>().eq("course_id",courseId).between("score",70,80));
        list.add(s4);
        int s5 = scoreMapper.selectCount(new EntityWrapper<Score>().eq("course_id",courseId).between("score",80,90));
        list.add(s5);
        int s6 = scoreMapper.selectCount(new EntityWrapper<Score>().eq("course_id",courseId).between("score",90,100));
        list.add(s6);
        return list;
    }

    /**
     * 根据学生的ID查询 成绩
     * @param sid
     * @return List<Score>
     */
    public List<Score> getScoreBySid(Integer sid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_id",sid);
        return scoreMapper.selectList(entityWrapper);
    }

    /**
     * 获取的考试成绩列表
     * @return List<Score>
     */
    public List<Score> getAllScore(){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Score> list = scoreMapper.selectList(entityWrapper);
        return list;
    }

    /**
     * 学生添加课程
     * @param studentId
     * @param courseId
     * @return
     */
    public int insertCourse(Integer studentId,Integer courseId){
        Score s = new Score();
        s.setCourseId(courseId);
        s.setStudentId(studentId);
        return scoreMapper.insert(s);
    }

    /**
     * 根据学生的ID查找他所学习的课程ID
     * @param sid
     * @return
     */
    public List<Integer> getCourseId(Integer sid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_id",sid);
        entityWrapper.groupBy("course_id");
        List<Integer> integerList = new ArrayList<>();
        List<Score> scoreList = scoreMapper.selectList(entityWrapper);
        for (Score s: scoreList
             ) {
            integerList.add(s.getCourseId());
        }
        return integerList;
    }

    /**
     * 根据学生的ID查询试卷的ID
     * @param sid
     * @return
     */
    public List<Integer> getExamId(Integer sid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_id",sid);
        List<Integer> integerList = new ArrayList<>();
        List<Score> scoreList = scoreMapper.selectList(entityWrapper);
        for (Score s: scoreList
        ) {
            if(s.getExamId()!=0){
                integerList.add(s.getExamId());
            }
        }
        return integerList;
    }

    /**
     * 获取教师教学质量报告
     * @param courseId
     * @return
     */
    public Map<String,Object> getClassLevel(Integer courseId){
        Map<String,Object> map  = new HashMap<>();
        //本门课程考试学生的总人数
        Integer sumCount = scoreMapper.selectCount(new EntityWrapper<Score>()
                .eq("course_id",courseId).eq("is_test",1));
        map.put("sumCount",sumCount);
        //优秀学生 人数
        Integer goodCount = scoreMapper.selectCount(new EntityWrapper<Score>().ge("score",90)
                .eq("course_id",courseId).eq("is_test",1));
        map.put("goodCount",goodCount);
        //及格学生人数
        Integer passCount = scoreMapper.selectCount(new EntityWrapper<Score>().between("score",60,90)
                .eq("course_id",courseId).eq("is_test",1));
        map.put("passCount",passCount);
        //不及格学生人数
        Integer notPassCount =scoreMapper.selectCount(new EntityWrapper<Score>().lt("score",60)
                .eq("course_id",courseId).eq("is_test",1));
        map.put("notPassCount",notPassCount);

        //获取平均分
        List<Score> scoreList= scoreMapper.selectList(new EntityWrapper<Score>().setSqlSelect("score")
                .eq("course_id",courseId).eq("is_test",1));
        int avgCount = 0;
        int sumScore=0;
        for (Score s: scoreList
             ) {
            sumScore+=s.getScore();
        }
        avgCount = sumScore/sumCount;
        map.put("avgCount",avgCount);
        //数据格式化
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        //优秀率
        double goodRate = (double)goodCount/sumCount;
        map.put("goodRate",nf.format(goodRate));
        //及格率
        double passRate = (double)passCount/sumCount;
        map.put("passRate",nf.format(passRate));
        //不及格率
        double notPassRate = (double)notPassCount/sumCount;
        map.put("notPassRate",nf.format(notPassRate));
        //标准分
        double variance = 0;
        for (Score s:scoreList
             ) {
            variance+=Math.pow((double)(s.getScore()-avgCount),2);
        }
        double standards = 0;
        standards = Math.sqrt(variance/sumCount);
        map.put("standards",nf.format(standards));
        //最高分
        int maxScore = scoreMapper.getMaxScore(courseId);
        map.put("maxScore",maxScore);
        //最低分
        int minScore =scoreMapper.getMinScore(courseId);
        map.put("minScore",minScore);
        return map;
    }

    /**
     * 学生提交试卷-更新试卷
     * @param score
     * @param courseId
     * @param studentId
     * @return
     */
    public int updateScore(Integer score ,Integer courseId,Integer studentId){
        return scoreMapper.updateScore(score,courseId,studentId);
    }

    /**
     * 添加考试信息
     * @param examId
     * @param courseId
     * @return
     */
    public int updateScore(Integer examId, Integer courseId){
        return scoreMapper.updateScore(examId,courseId);
    }

}
