package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.demo.mapper.StudentMapper;
import com.test.demo.model.Course;
import com.test.demo.model.Student;
import io.swagger.models.auth.In;
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
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    /**
     * 登录检查
     * @param student
     * @return
     */
    public Student checkStudent(Student student){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_name",student.getStudentName());
        entityWrapper.eq("student_password",student.getStudentPassword());
        List<Student> studentList  = studentMapper.selectList(entityWrapper);
        if(studentList.size()>0){
            return studentList.get(0);
        }else{
            return null;
        }
    }

    /**
     * 通过用户名查询用户已经能存在
     * @param name
     * @return
     */
    public Student getStudentByName(String name){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_name",name);
        List<Student> student = studentMapper.selectList(entityWrapper);
        return student.get(0);
    }

    public Boolean GetBooleanByName(String name){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_name",name);
        List<Student> student = studentMapper.selectList(entityWrapper);
        if(student.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加一名学生
     * @param name
     * @param password
     * @return
     */
    public int insertStudent(String name ,String password){
        Student student = new Student();
        student.setStudentName(name);
        student.setStudentPassword(password);
        return studentMapper.insert(student);
    }

    /**
     * 获取学生列表 分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String,Object> getStudentList(Integer pageNo, Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Student> student = studentMapper.selectPage(new Page<Course>(pageNo,pageSize),entityWrapper);
        int count = studentMapper.selectCount(entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",student);
        map.put("count",count);
        return map;
    }


    public List<Student> getStudentList(){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Student> student = studentMapper.selectPage(new Page<Course>(1,10),entityWrapper);
        return student;
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    public int deleteStudent(Integer id){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_id",id);
        return studentMapper.delete(entityWrapper);
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    public  int updateStudent(Student student){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("student_id",student.getStudentId());
        return studentMapper.update(student,entityWrapper);
    }

    /**
     * 根据教师的ClassID查询学生信息
     */
    public Map<String,Object> getStudentListByClassID(Integer classId ,Integer pageNo,Integer pageSize){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("class_id",classId);
        List<Student> studentList = studentMapper.selectPage(new Page<Student>(pageNo,pageSize),entityWrapper);
        int count = studentMapper.selectCount(entityWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("list",studentList);
        map.put("count",count);
        return map;
    }

    /**
     * 根据学生的ID查询学生的信息
     * @param sid
     * @return
     */
    public Student getStudentBySid(Integer sid){
        Student student = new Student();
        student.setStudentId(sid);
        return studentMapper.selectOne(student);
    }

    /**
     * 获取学生的总数量  ---传输给后台
     * @return
     */
    public Integer getStudentCount(){
        EntityWrapper entityWrapper = new EntityWrapper();
        return studentMapper.selectCount(entityWrapper);
    }

}
