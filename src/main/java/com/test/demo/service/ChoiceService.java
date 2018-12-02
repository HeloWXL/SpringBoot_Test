package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.ChoiceMapper;
import com.test.demo.model.Choice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/26 14:55
 */
@Service
public class ChoiceService {

    @Resource
    private ChoiceMapper choiceMapper;

    /**
     * 添加选择题
     * @param choice
     * @return
     */
    public int insertSelest(Choice choice){

        return choiceMapper.insert(choice);
    }

    /**
     * 删除选择题
     * @param selectId
     * @return
     */
    public int deleteSelect(Integer selectId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("select_id",selectId);
        return choiceMapper.delete(entityWrapper);
    }

    /**
     * 根据教师的ID查询选择题
     * @param tid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String,Object> getSelectByTid(Integer tid, Integer pageNo, Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);
        int count = choiceMapper.selectCount(entityWrapper);
        List<Choice> selectList = choiceMapper.selectPage(new Page<>(pageNo,pageSize),entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",selectList);
        map.put("count",count);
        return map;
    }

    /**
     * 获取选择题列表
     * @return
     */
    public List<Choice> getAllChoices(){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Choice> selectList = choiceMapper.selectList(entityWrapper);
        return selectList;
    }


}
