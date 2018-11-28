package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Course;
import com.test.demo.model.Teacher;
import com.test.demo.service.CourseService;
import com.test.demo.service.TeacherService;
import com.test.demo.utils.UploadFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/24
 */
@Api(tags ="课程接口")
@RestController
@RequestMapping("course")
public class CourseController {
    protected Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Resource
    private CourseService courseService;
    @Resource
    private TeacherService teacherService;

    @ApiOperation(value="课程列表")
    @GetMapping("courseList")
    public ResultData<Map<String,Object>> getCourseList(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        if(courseService.getCourseList(pageNo,pageSize).size()>0){
            resultData.setResult(courseService.getCourseList(pageNo,pageSize));
            resultData.setCode(200);
            logger.info("获取课程列表成功");
            resultData.setMsg("获取课程列表成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("获取课程列表失败");
            resultData.setCode(500);
            logger.info("获取课程列表失败");
            return resultData;
        }
    }

    @ApiOperation(value="添加一门课程")
    @PostMapping("insertCourse")
    public ResultData<Boolean> insertCourseList(@RequestParam("file") MultipartFile file,
                                                @RequestParam("courseName") String courserName,
                                                @RequestParam("courseIntroduce") String courseIntroduce,
                                                @RequestParam("teacherId") Integer teacherId) {
        ResultData<Boolean> resultData = new ResultData<>();

        Course course = new Course();
        UploadFileUtils uploadFileUtils = new UploadFileUtils();
        // 设置course对象的值
        course.setCourseName(courserName);
        course.setCourseIntroduce(courseIntroduce);
        course.setCoursePicture("images/"+uploadFileUtils.uploadImage(file));
        course.setTeacherId(teacherId);

        if(courseService.insertCourse(course)==1){
            resultData.setResult(true);
            resultData.setCode(200);
            logger.info("添加成功");
            resultData.setMsg("添加成功");
            return resultData;
        }else{
            resultData.setResult(false);
            resultData.setMsg("添加失败");
            resultData.setCode(500);
            logger.info("添加失败");
            return resultData;
        }
    }
    @ApiOperation(value="修改课程")
    @PostMapping("updateCourse")
    public ResultData<Boolean> updateCourse(@RequestBody Course course) {
        ResultData<Boolean> resultData = new ResultData<>();
        if(courseService.updateCourse(course)==1){
            resultData.setResult(true);
            resultData.setCode(200);
            logger.info("修改成功");
            resultData.setMsg("修改成功");
            return resultData;
        }else{
            resultData.setResult(false);
            resultData.setMsg("修改失败");
            resultData.setCode(500);
            logger.info("修改失败");
            return resultData;
        }
    }

    @ApiOperation(value="删除课程")
    @GetMapping("deleteCourse")
    public ResultData<Boolean> deleteCourse(@RequestParam("id") Integer id) {
        ResultData<Boolean> resultData = new ResultData<>();
        if(courseService.deleteCourse(id)==1){
            resultData.setResult(true);
            resultData.setCode(200);
            logger.info("删除成功");
            resultData.setMsg("删除成功");
            return resultData;
        }else{
            resultData.setResult(false);
            resultData.setMsg("删除失败");
            resultData.setCode(500);
            logger.info("删除失败");
            return resultData;
        }
    }


    @ApiOperation(value="根据教师的ID查询课程")
    @GetMapping("getCourseByTid")
    public ResultData<Map<String,Object>> getCourseList(@RequestParam("pageNo") Integer pageNo,
                                                        @RequestParam("pageSize") Integer pageSize,
                                                        @RequestParam("tid") Integer tid){
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        if(courseService.getCourseByTid(pageNo,pageSize,tid).size()>0){
            resultData.setResult(courseService.getCourseByTid(tid,pageNo,pageSize));
            resultData.setCode(200);
            logger.info("获取教师课程列表成功");
            resultData.setMsg("获取教师课程列表成功");
            return resultData;
        }else{
            resultData.setResult(null);
            return resultData;
        }
    }

    @ApiOperation(value="根据课程名查询课程信息")
    @PostMapping("getCourseByCoursrName")
    public ResultData<Course> getCourseByCoursrName(@RequestParam("courseName") String courseName){
        ResultData<Course> resultData = new ResultData<>();
        if(courseService.getCourseByCoursrName(courseName)!=null){
            resultData.setMsg("查询课程信息成功");
            resultData.setCode(200);
            resultData.setResult(courseService.getCourseByCoursrName(courseName));
            return resultData;
        }else{
            resultData.setMsg("查询课程信息失败，课程可能不存在");
            resultData.setCode(500);
            resultData.setResult(null);
            return resultData;
        }
    }

    @ApiOperation(value="根据教师的ID得到教师所教授的课程列表")
    @PostMapping("getCourseByTeacherID")
    public ResultData<List<Course>> getCourseByTeacherID(@RequestParam("teacherId") Integer teacherId){
        ResultData<List<Course>> resultData = new ResultData<>();
        if(courseService.getCourseByTid(teacherId).size()>0){
            resultData.setMsg("查询课程信息成功");
            resultData.setCode(200);
            resultData.setResult(courseService.getCourseByTid(teacherId));
            return resultData;
        }else{
            resultData.setMsg("该教师暂时还没有教授课程");
            resultData.setCode(500);
            resultData.setResult(null);
            return resultData;
        }
    }

    @ApiOperation(value="根据学生的班级ID得到课程列表")
    @PostMapping("getCourseByCid")
    public ResultData<Map<String,Object>> getCourseByCid(@RequestParam("cid") Integer cid){
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        List<Teacher> teachers = teacherService.getTeacherByCid(cid);
        Map<String,Object> map = new HashMap<>();
        for (Teacher t:teachers
             ) {
            map.put(t.getTeacherName(),courseService.getCourseByTid(t.getTeacherId()));
        }
        if(map.size()>0){
            resultData.setMsg("查询成功");
            resultData.setResult(map);
            resultData.setCode(200);
            return resultData;
        }else{
            resultData.setMsg("没有加入课程");
            resultData.setCode(200);
            resultData.setResult(null);
            return resultData;
        }

    }

}
