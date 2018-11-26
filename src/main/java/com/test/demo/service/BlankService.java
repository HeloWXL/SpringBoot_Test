package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.BlankMapper;
import com.test.demo.model.Blank;
import com.test.demo.model.Select;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/26 14:51
 */
@Service
public class BlankService {

    @Resource
    private BlankMapper blankMapper;

    /**
     * 添加试题（填空题）
     * @param blank
     * @return
     */
    public int insertBlank(Blank blank){
        return blankMapper.insert(blank);
    }

    /**
     * 根据试题ID删除
     * @param blankId
     * @return
     */
    public int deleteBlank(Integer blankId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("blank_id",blankId);
        return blankMapper.delete(entityWrapper);
    }

    public Map<String,Object> getBlankByTid(Integer tid,Integer pageNo,Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);

        int count = blankMapper.selectCount(entityWrapper);
        List<Select> blankList = blankMapper.selectPage(new Page<>(pageNo,pageSize),entityWrapper);

        Map<String,Object> map = new HashMap<>();
        map.put("list",blankList);
        map.put("count",count);
        return map;
    }


}
