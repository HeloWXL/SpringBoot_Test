package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.controller.resp.ScoreVo;
import com.test.demo.model.Score;
import com.test.demo.service.CourseService;
import com.test.demo.service.ScoreService;
import com.test.demo.service.StudentService;
import com.test.demo.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/24
 */
@Api(tags ="学生成绩接口")
@RestController
@RequestMapping("score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;


    @Resource
    private CourseService courseService;

    @ApiOperation(value="根据课程的ID查询课程成绩")
    @PostMapping("getScoreByCourseId")
    public ResultData<List<Score>> getScoreByCourseId(@RequestParam("cid") Integer cid) {
        ResultData<List<Score>> resultData = new ResultData<>();
        if (scoreService.getScoreByCourseId(cid).size() > 0) {
            resultData.setCode(200);
            resultData.setMsg("查询课程成绩成功");
            resultData.setResult(scoreService.getScoreByCourseId(cid));
            return resultData;
        } else {
            resultData.setCode(500);
            resultData.setMsg("查询课程成绩失败");
            resultData.setResult(null);
            return resultData;
        }
    }

    @ApiOperation(value="统计直方图数据")
    @PostMapping("getCountByCourseId")
    public ResultData<List<Integer>> getCountByCourseId(@RequestParam("cid") Integer cid) {
        ResultData<List<Integer>> resultData = new ResultData<>();
        if (scoreService.getCountByCourseId(cid).size() > 0) {
            resultData.setCode(200);
            resultData.setMsg("success");
            resultData.setResult(scoreService.getCountByCourseId(cid));
            return resultData;
        } else {
            resultData.setCode(500);
            resultData.setMsg("fail");
            resultData.setResult(null);
            return resultData;
        }
    }

    @ApiOperation(value="根据学生的ID查询学生的成绩")
    @PostMapping("getScoreBySid")
    public ResultData<List<Score>> getScoreBySid(@RequestParam("sid") Integer sid){
        ResultData<List<Score>> resultData = new ResultData<>();
        if(scoreService.getScoreBySid(sid).size()>0){
            resultData.setCode(200);
            resultData.setMsg("成功查询到学生的成绩");
            resultData.setResult(scoreService.getScoreBySid(sid));
            return  resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("该学生暂无成绩");
            return  resultData;
        }
    }

    @ApiOperation(value="获取所有的考试成绩")
    @PostMapping("getScoreList")
    public ResultData<List<ScoreVo>> getScoreList() {
        ResultData<List<ScoreVo>> resultData = new ResultData<>();
        List<ScoreVo> list = new ArrayList<>();
        System.out.println(scoreService.getAllScore().size());
        if(scoreService.getAllScore().size()>0){
            List<Score> scoreList = scoreService.getAllScore();
            for (Score score:scoreList
                 ) {
                ScoreVo scoreVo = new ScoreVo();
                //学生的姓名
                String   studentName = studentService.getStudentBySid(score.getStudentId()).getStudentName();
                //学生的学号
                Integer studentSno = studentService.getStudentBySid(score.getStudentId()).getStudentSno();
                //教师姓名
                String teacherName = teacherService.getTeacher(courseService.getCourseByCid(score.getCourseId()).getTeacherId()).getTeacherName();
                //课程名
                String courseName = courseService.getCourseByCid(score.getCourseId()).getCourseName();
                scoreVo.setStudentName(studentName);
                scoreVo.setCourseName(courseName);
                scoreVo.setStudentSno(studentSno);
                scoreVo.setTeacherName(teacherName);
                list.add(scoreVo);
            }
            resultData.setCode(200);
            resultData.setResult(list);
            resultData.setMsg("获取成功");
            return  resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("获取失败");
            resultData.setCode(500);

            return  resultData;
        }
    }



    }
