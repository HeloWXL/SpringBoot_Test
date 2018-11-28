package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Teacher;
import com.test.demo.service.TeacherService;
import com.test.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/24
 */
@Api(tags ="教师接口")
@RestController
@RequestMapping("teacher")
public class TeacherController {
    protected Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Resource
    private TeacherService teacherService;

    @ApiOperation(value="登录检查")
    @PostMapping("checkTeacherLogin")
    public ResultData<Boolean> checkLogin(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("password") String password){
        ResultData<Boolean> resultData = new ResultData<>();
        Teacher t= new Teacher();
        t.setTeacherName(name);
        if(Md5Utils.getSaltverifyMD5(password,teacherService.getTeacherPassword(t).getTeacherPassword())) {
            Teacher teacher = teacherService.getTeacherPassword(t);
            //获取教师对象session
            request.getSession().setAttribute("teachersession",teacher);
            logger.info("登录成功");
            resultData.setResult(true);
            resultData.setCode(200);
            resultData.setMsg("登录成功");
            return resultData;
        }else{
            logger.info("登录失败");
            resultData.setResult(false);
            resultData.setCode(500);
            resultData.setMsg("登录失败");
            return resultData;
        }
    }

    @ApiOperation(value="教师注册")
    @PostMapping("insertTeacher")
    public ResultData<Boolean> insertTeacher(@RequestParam("name") String name,@RequestParam("password") String password) {
        ResultData<Boolean> resultData = new ResultData<>();
        String md5Password = Md5Utils.getSaltMD5(password);
        int i = teacherService.insertTeahcer(name,md5Password);
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

    @ApiOperation(value="删除教师")
    @GetMapping("deleteteacher")
    public ResultData<Boolean> deleteTeacherById(@RequestParam("id") Integer id) {
        ResultData<Boolean> resultData = new ResultData<>();
        ;
        int i = teacherService.deleteTeacher(id);
        if(i==1){
            logger.info("删除教师成功");
            resultData.setMsg("删除教师成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("删除教师失败");
            resultData.setMsg("删除失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="修改教师信息")
    @PostMapping("updateteacher")
    public ResultData<Boolean> updateTeacherById(@RequestBody Teacher teacher) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i = teacherService.updateTeacher(teacher);
        if(i==1){
            logger.info("修改教师信息成功");
            resultData.setMsg("修改教师信息成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("修改教师信息失败");
            resultData.setMsg("修改教师信息失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }


    @ApiOperation(value="教师列表")
    @GetMapping("teachertList")
    public ResultData<Map<String,Object>> getTeacherList(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {
        ResultData<Map<String,Object>> resultData = new ResultData<>();

        if(teacherService.getTeacherList(pageNo,pageSize).size()>0){
            resultData.setResult(teacherService.getTeacherList(pageNo,pageSize));
            resultData.setCode(200);
            logger.info("获取学生列表成功");
            resultData.setMsg("获取学生列表成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("获取用户列表失败");
            resultData.setCode(500);
            logger.info("获取用户列表失败");
            return resultData;
        }
    }

    @ApiOperation(value="获取教师的session对象")
    @PostMapping("getTeacherSession")
    public ResultData<Teacher> getTeacherSession(HttpServletRequest request,@RequestParam("teacherBean") String teacherBean){
        ResultData<Teacher> resultData = new ResultData<>();
        Teacher teacher = (Teacher) request.getSession().getAttribute(teacherBean);
        resultData.setCode(200);
        resultData.setMsg("获取对象成功");
        resultData.setResult(teacher);
        return resultData;
    }

    @ApiOperation(value="根据教师的ID获取教师对象")
    @PostMapping("getTeacherByTid")
    public ResultData<Teacher> getTeacherByTid(@RequestParam("tid") Integer tid) {
        ResultData<Teacher> resultData = new ResultData<>();
        Teacher teacher = teacherService.getTeacher(tid);
        logger.info("获取成功");
        resultData.setMsg("获取成功");
        resultData.setCode(200);
        resultData.setResult(teacher);
        return resultData;
    }



}
