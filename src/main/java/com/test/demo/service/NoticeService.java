package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.NoticeMapper;
import com.test.demo.model.Notice;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/27 9:02
 */
@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    /**
     * 添加一条公告信息
     * @param notice
     * @return
     */
    public int insertNotice(Notice notice){
        return noticeMapper.insert(notice);
    }



    /**
     *  获取公告列表
     * @param tid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public   Map<String, Object>  getAllNotice(Integer tid, Integer pageNo,Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);
        int count  = noticeMapper.selectCount(entityWrapper);
        List<Notice> notices = noticeMapper.selectPage(new Page<>(pageNo,pageSize),entityWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("list",notices);
        return map;
    }
}
