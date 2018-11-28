package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Student;
import com.test.demo.service.StudentService;

import com.test.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;


/**
 * @author wangxl
 * @date 2018/11/24
 */
@Api(tags ="学生接口")
@RestController
@RequestMapping("student")
public class StudentController {

    protected Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private StudentService studentService;

    @ApiOperation(value="登录检查")
    @PostMapping("checkStudentLogin")
    public ResultData<Boolean> checkLogin(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("password") String password){
        ResultData<Boolean> resultData = new ResultData<>();
        if(Md5Utils.getSaltverifyMD5(password,studentService.getStudentByName(name).getStudentPassword())) {
            Student student = studentService.getStudentByName(name);
            //获取学生对象，将其保存在session
            request.getSession().setAttribute("studentsession",student);
            logger.info("学生登录成功");
            resultData.setResult(true);
            resultData.setCode(200);
            resultData.setMsg("学生登录成功");
            return resultData;
        }else{
            logger.info("学生登录失败");
            resultData.setResult(false);
            resultData.setCode(500);
            resultData.setMsg("学生登录失败");
            return resultData;
        }
    }

    @ApiOperation(value="学生注册")
    @PostMapping("insertStudent")
    public ResultData<Boolean> insertStudent(@RequestParam("name") String name,@RequestParam("password") String password) {
        ResultData<Boolean> resultData = new ResultData<>();
        String md5Password = Md5Utils.getSaltMD5(password);
        int i = studentService.insertStudent(name,md5Password);
        if(i==1){
            logger.info("注册成功");
            resultData.setMsg("注册成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("注册失败");
            resultData.setMsg("注册失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="删除学生")
    @GetMapping("deletestudent")
    public ResultData<Boolean> deleteStudentById(@RequestParam("id") Integer id) {
        ResultData<Boolean> resultData = new ResultData<>();
        ;
        int i = studentService.deleteStudent(id);
        if(i==1){
            logger.info("删除成功");
            resultData.setMsg("删除成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("删除失败");
            resultData.setMsg("删除失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }


    @ApiOperation(value="修改学生信息")
    @GetMapping("updatestudent")
    public ResultData<Boolean> updateStudentById(@RequestBody(required = false) Student student) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i = studentService.updateStudent(student);
        if(i==1){
            logger.info("修改成功");
            resultData.setMsg("修改成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("修改失败");
            resultData.setMsg("修改失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="学生列表")
    @GetMapping("studentList")
    public ResultData<Map<String,Object>> getStudentList(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        if(studentService.getStudentList(pageNo,pageSize).size()>0){
            resultData.setResult(studentService.getStudentList(pageNo,pageSize));
            resultData.setCode(200);
            logger.info("获取学生列表成功");
            resultData.setMsg("获取学生列表成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("获取学生列表失败");
            resultData.setCode(500);
            logger.info("获取学生列表失败");
            return resultData;
        }
    }

    @ApiOperation(value="根据教师的ClassID查询学生")
    @PostMapping("getStudentListByClassID")
    public ResultData<Map<String,Object>> getStudentListByTid(@RequestParam("classId") Integer classId ,
                                                              @RequestParam("pageNo") Integer pageNo,
                                                              @RequestParam("pageSize") Integer pageSize){
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        if(studentService.getStudentListByClassID(classId, pageNo, pageSize).size()>0){
            resultData.setResult(studentService.getStudentListByClassID(classId, pageNo, pageSize));
            resultData.setCode(200);
            logger.info("获取学生列表成功");
            resultData.setMsg("获取学生列表成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("获取学生列表失败");
            resultData.setCode(500);
            logger.info("获取学生列表失败");
            return resultData;
        }
    }
    @ApiOperation(value="获取学生的session对象")
    @PostMapping("getStudentSession")
    public ResultData<Student> getTeacherSession(HttpServletRequest request,@RequestParam("studentBean") String teacherBean){
        ResultData<Student> resultData = new ResultData<>();
        Student student = (Student) request.getSession().getAttribute(teacherBean);
        if(StringUtils.isEmpty(student)){
            resultData.setCode(500);
            resultData.setMsg("获取对象失败");
            resultData.setResult(null);
            return resultData;
        }else{
            resultData.setCode(200);
            resultData.setMsg("获取对象成功");
            resultData.setResult(student);
            return resultData;
        }


    }

    @ApiOperation(value="根据学生的ID查询学生信息")
    @PostMapping("getStudentBySid")
    public ResultData<Student> getStudentBySid(@RequestParam("sid") Integer sid){
        ResultData<Student> resultData = new ResultData<>();
        resultData.setResult(studentService.getStudentBySid(sid));
        resultData.setMsg("成功获取学生的信息");
        resultData.setCode(200);
        return resultData;
    }

}
