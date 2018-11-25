package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.CourseMapper;
import com.test.demo.model.Course;
import com.test.demo.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/24
 */
@Service
public class CourseService {


    @Resource
    private CourseMapper courseMapper;

    /**
     * 获取课程信息列表
     * @return
     */
    public Map<String,Object> getCourseList(int pageNo,int pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Student> course = courseMapper.selectPage(new Page<Course>(pageNo,pageSize),entityWrapper);

        int count =courseMapper.selectCount(entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",course);
        map.put("count",count);
        return map;
    }

    /**
     * 删除课程列表
     * @param id
     * @return
     */
    public int deleteCourse(Integer id){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("course_id",id);
        return courseMapper.delete(entityWrapper);
    }

    /**
     * 更新课程列表
     * @param course
     * @return
     */
    public  int updateCourse(Course course){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("course_id",course.getCourseId());
        return courseMapper.update(course,entityWrapper);
    }


    /**
     * 添加一门课程
     * @param course
     * @return
     */
    public int insertCourse(Course course){
        return courseMapper.insert(course);
    }

    /**
     * 根据教师的ID查询课程
     * @param tid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String,Object> getCourseByTid(Integer tid ,Integer pageNo,Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);
        List<Course> course = courseMapper.selectPage(new Page<Course>(pageNo,pageSize),entityWrapper);
        int count =courseMapper.selectCount(entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",course);
        map.put("count",count);
        return map;
    }

}
