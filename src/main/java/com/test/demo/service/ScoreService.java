package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.demo.mapper.ScoreMapper;
import com.test.demo.model.Score;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils.lt;

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
     * @return
     */
    public List<Score>  getScoreByCourseId(Integer courseId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("course_id",courseId);
        return scoreMapper.selectList(entityWrapper);
    }


    /**
     * 根据课程的ID查询分段成绩得数量------绘制通知直方图
     * @param courseId
     * @return
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


}
