package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.BlankMapper;
import com.test.demo.model.Blank;
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

    /**
     * 根据教师的ID查询填空题
     * @param tid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String,Object> getBlankByTid(Integer tid,Integer pageNo,Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);
        int count = blankMapper.selectCount(entityWrapper);
        List<Blank> blankList = blankMapper.selectPage(new Page<>(pageNo,pageSize),entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",blankList);
        map.put("count",count);
        return map;
    }

    /**
     * 获取填空题列表
     * @return
     */
    public List<Blank> getBlankList(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setSqlSelect("blank_id","blank_question","blank_answer");
        return blankMapper.selectPage(new Page<Blank>(1,2),entityWrapper);
    }


}
