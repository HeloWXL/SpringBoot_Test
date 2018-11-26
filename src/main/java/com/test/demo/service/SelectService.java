package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.SelectMapper;
import com.test.demo.model.Select;
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
public class SelectService {

    @Resource
    private SelectMapper selectMapper;

    /**
     * 添加选择题
     * @param s
     * @return
     */
    public int insertSelest(Select s){
        return selectMapper.insertSelect(s);
    }

    /**
     * 删除选择题
     * @param selectId
     * @return
     */
    public int deleteSelect(Integer selectId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("select_id",selectId);
        return selectMapper.delete(entityWrapper);
    }

    public Map<String,Object> getSelectByTid(Integer tid, Integer pageNo, Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);

        int count = selectMapper.selectCount(entityWrapper);
        List<Select> selectList = selectMapper.selectPage(new Page<>(pageNo,pageSize),entityWrapper);

        Map<String,Object> map = new HashMap<>();
        map.put("list",selectList);
        map.put("count",count);
        return map;
    }
}
