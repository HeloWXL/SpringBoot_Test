package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.demo.mapper.ScoreMapper;
import com.test.demo.model.Score;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
