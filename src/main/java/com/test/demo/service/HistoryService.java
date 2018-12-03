package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.demo.mapper.HistoryMapper;
import com.test.demo.model.History;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangxl
 * @date 2018/12/3 9:03
 */
@Service
public class HistoryService {



    @Resource
    private HistoryMapper historyMapper;

    /**
     * 添加历史记录
     * @param studentId
     * @param courseId
     * @return
     */
    public int insertHistory(Integer studentId,Integer courseId){
        History history = new History();
        history.setCourseId(courseId);
        history.setStudentId(studentId);
        history.setCreatetime(new Date());
        return historyMapper.insert(history);
    }

    /**
     * 获取历史记录列表
     * @return
     */
    public List<History> getAllHistory(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setSqlSelect("student_id","course_id","createtime");
        List<History> historyList = historyMapper.selectList(entityWrapper);
        return historyList;
    }

    /**
     * 返回歷史表中學生的ID
     * @return
     */
    public List<Integer> getAllStudentId(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.groupBy("student_id");
        List<History> list = historyMapper.selectList(entityWrapper);
        List<Integer> integerList = new ArrayList<>();
        for (History h:list
             ) {
            integerList.add(h.getStudentId());
        }
        return integerList;
    }

    /**
     * 根據學生的ID查詢課程ID列表
     * @param sid
     * @return
     */
    public List<Integer> getCourseByStudentId(Integer sid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setSqlSelect("course_id");
        entityWrapper.eq("student_id",sid);
        entityWrapper.groupBy("course_id");
        List<History> historyList = historyMapper.selectList(entityWrapper);
        List<Integer> integerList = new ArrayList<>();
        for (History h:historyList
             ) {
            integerList.add(h.getCourseId());
        }
        return integerList;
    }

}
