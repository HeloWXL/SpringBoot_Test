package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.CourseMapper;
import com.test.demo.model.Course;
import com.test.demo.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
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

    /**
     * 根据课程名查询课程信息
     * @param courseName
     * @return
     */
    public Course getCourseByCoursrName(String courseName){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("course_name",courseName);
         if(courseMapper.selectList(entityWrapper).size()>0){
             return (Course) courseMapper.selectList(entityWrapper).get(0);
         }else{
             return null;
         }

    }

    /**
     * 根据教师的ID查询课程
     * @param tid
     * @return
     */
    public List<Course> getCourseByTid(Integer tid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",tid);
        List<Course> course = courseMapper.selectList(entityWrapper);
        return course;
    }

    /**
     * 获取课程的总数量----传输给后台
     * @return
     */
    public Integer getCourseCount(){
        EntityWrapper entityWrapper = new EntityWrapper();
        return courseMapper.selectCount(entityWrapper);
    }


    /**
     * 根据课程的ID查询课程信息
     * @param cid
     * @return
     */
    public Course getCourseByCid(Integer cid){
        Course c = new Course();
        c.setCourseId(cid);
        return courseMapper.selectOne(c);
    }



}
