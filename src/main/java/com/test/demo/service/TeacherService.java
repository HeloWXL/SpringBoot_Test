package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.TeacherMapper;
import com.test.demo.model.Course;
import com.test.demo.model.Teacher;
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
public class TeacherService {


    @Resource
    private TeacherMapper teacherMapper;

//    教师登录
    public Teacher checkTeacher(Teacher teacher){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_name",teacher.getTeacherName());
        entityWrapper.eq("student_password",teacher.getTeacherPassword());
        List<Teacher> studentList  =  teacherMapper.selectList(entityWrapper);
        if(studentList.size()>0){
            return studentList.get(0);
        }else{
            return null;
        }
    }

    //教师注册
    public int insertTeahcer(String name ,String password){
        Teacher teacher = new Teacher();
        teacher.setTeacherPassword(password);
        teacher.setTeacherName(name);
        teacher.setTeacherPicture("images/default.jpg");
        return teacherMapper.insert(teacher);
    }

    //通过用户名查询用户已经能存在
    public Teacher getTeacherPassword(Teacher teacher){
        Teacher t  = teacherMapper.selectOne(teacher);
        return t;
    }

    //删除教师
    public int deleteTeacher(Integer id){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",id);
        return teacherMapper.delete(entityWrapper);
    }

    //更新教师
    public  int updateTeacher(Teacher teacher){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("teacher_id",teacher.getTeacherId());
        return teacherMapper.update(teacher,entityWrapper);
    }

    //得到教师列表
    public Map<String,Object> getTeacherList(Integer pageNo,Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Teacher> teacherslist = teacherMapper.selectPage(new Page<Course>(pageNo,pageSize),entityWrapper);
        int count = teacherMapper.selectCount(entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",teacherslist);
        map.put("count",count);
        return map;
    }


}
