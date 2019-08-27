package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Admin;
import com.test.demo.model.Student;
import com.test.demo.model.Teacher;
import com.test.demo.service.AdminService;
import com.test.demo.service.CourseService;
import com.test.demo.service.StudentService;
import com.test.demo.service.TeacherService;
import com.test.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/24
 */
@Api(tags ="管理员接口")
@RestController
@RequestMapping("admin")
public class AdminController {

    protected Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @ApiOperation(value="管理员登录检查")
    @PostMapping("checkAdminLogin")
    public ResultData<Boolean> checkLogin(HttpServletRequest request,@RequestParam("name") String name, @RequestParam("password") String password){
        ResultData<Boolean> resultData = new ResultData<>();
        if(Md5Utils.getSaltverifyMD5(password,adminService.getAdminByName(name).getAdminPassword())) {
            Admin admin = adminService.getAdminByName(name);
            //设置学生对象的session
            request.getSession().setAttribute("adminSession",admin);
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

    @ApiOperation(value="删除管理员")
    @GetMapping("deleteadmin")
    public ResultData<Boolean> deleteStudentById(@RequestParam("id") Integer id) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i = adminService.deleteAdmin(id);
        if(i==1){
            logger.info("删除管理员信息成功");
            resultData.setMsg("删除管理员信息成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("删除管理员信息失败");
            resultData.setMsg("删除管理员信息失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }


    @ApiOperation(value="修改管理员信息")
    @PostMapping("updateadmin")
    public ResultData<Boolean> updateStudentById(@RequestBody Admin admin) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i =adminService.updateAdmin(admin);
        if(i==1){
            logger.info("修改管理员信息成功");
            resultData.setMsg("修改管理员信息成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("修改管理员信息失败");
            resultData.setMsg("修改管理员信息失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="管理员列表")
    @GetMapping("adminList")
    public ResultData<Map<String,Object>> getAdminList() {
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        if(adminService.getAdminList().size()>0){
            resultData.setResult(adminService.getAdminList());
            resultData.setCode(200);
            logger.info("获取管理员列表成功");
            resultData.setMsg("获取管理员列表成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("获取管理员列表失败");
            resultData.setCode(500);
            logger.info("获取管理员列表失败");
            return resultData;
        }
    }

    @ApiOperation(value="管理员注册")
    @PostMapping("insertAdmin")
    public ResultData<Integer> insertAdmin(@RequestParam(value = "name" ,required = false) String name ,
                                           @RequestParam(value= "password",required = false) String password ) {
        ResultData<Integer> resultData = new ResultData<>();
        String md5Password = Md5Utils.getSaltMD5(password);
        int i = adminService.insertAdmin(name,md5Password );

        resultData.setResult(i);
        return resultData;

    }


    @ApiOperation(value="获取统计数据")
    @GetMapping("countForAdmin")
    public ResultData<Map<String,Integer>> insertStudent() {
        ResultData<Map<String,Integer>> resultData = new ResultData<>();

        Map<String,Integer> map = new HashMap<>();
        map.put("student",studentService.getStudentCount());
        map.put("teacher",teacherService.getTeacherCount());
        map.put("course",courseService.getCourseCount());

        resultData.setResult(map);
        resultData.setCode(200);
        resultData.setMsg("获取成功");
        return resultData;
    }

    @ApiOperation(value="获取管理员的session对象")
    @PostMapping("getAdminSession")
    public ResultData<Admin> getAdminSession(HttpServletRequest request, @RequestParam("adminBean") String adminBean){
        ResultData<Admin> resultData = new ResultData<>();
        Admin admin = (Admin) request.getSession().getAttribute(adminBean);
        resultData.setCode(200);
        resultData.setMsg("获取对象成功");
        resultData.setResult(admin);
        return resultData;
    }

    @ApiOperation(value="退出登录-清除adminSession")
    @PostMapping("AdminLoginOut")
    public Boolean getAdminSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("adminSession");
        System.out.println(session);
        if(session==null){
            return true;
        }else{
            return false;
        }
    }

}
