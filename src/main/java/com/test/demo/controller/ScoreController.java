package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.controller.resp.RespScoreVo;
import com.test.demo.controller.resp.ScoreVo;
import com.test.demo.model.Score;
import com.test.demo.service.CourseService;
import com.test.demo.service.ScoreService;
import com.test.demo.service.StudentService;
import com.test.demo.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    @GetMapping("getScoreByCourseId")
    public ResultData<List<RespScoreVo>> getScoreByCourseId(@RequestParam("cid") Integer cid) {
        ResultData<List<RespScoreVo>> resultData = new ResultData<>();
        if (scoreService.getScoreByCourseId(cid).size() > 0) {
            List<Score> scores = scoreService.getScoreByCourseId(cid);
            List<RespScoreVo> respScoreVos = new ArrayList<>();
            for (Score s : scores
                 ) {
                RespScoreVo respScoreVo = new RespScoreVo();
                //获取学生的姓名
                String studentName = studentService.getStudentBySid(s.getStudentId()).getStudentName();
                String courseName = courseService.getCourseByCid(s.getCourseId()).getCourseName();
                Integer studentSno = studentService.getStudentBySid(s.getStudentId()).getStudentSno();
                Integer score = s.getScore();
                respScoreVo.setCourseName(courseName);
                respScoreVo.setScore(score);
                respScoreVo.setStudentName(studentName);
                respScoreVo.setStudentSno(studentSno);
                respScoreVos.add(respScoreVo);
            }
            resultData.setCode(200);
            resultData.setMsg("查询课程成绩成功");
            resultData.setResult(respScoreVos);
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
    public ResultData<List<ScoreVo>> getScoreBySid(@RequestParam("sid") Integer sid){
        ResultData<List<ScoreVo>> resultData = new ResultData<>();
        List<Score> scores = scoreService.getScoreBySid(sid);
        List<ScoreVo> scoreVoList = new ArrayList<>();
        for (Score s : scores
             ) {
            ScoreVo scoreVo = new ScoreVo();
            scoreVo.setTeacherName(teacherService.getTeacher(courseService.getCourseByCid(s.getCourseId()).getTeacherId()).getTeacherName());
            scoreVo.setCourseName(courseService.getCourseByCid(s.getCourseId()).getCourseName());
            scoreVo.setStudentSno(studentService.getStudentBySid(s.getStudentId()).getStudentSno());
            scoreVo.setScore(s.getScore());
            scoreVoList.add(scoreVo);
        }
        if(scoreVoList.size()>0){
            resultData.setCode(200);
            resultData.setMsg("成功查询到学生的成绩");
            resultData.setResult(scoreVoList);
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


    @ApiOperation(value="获取教师质量报告")
    @PostMapping("getClassLevel")
    public ResultData<Map<String,Object>> getClassLevel(@RequestParam("courseId") Integer courseId){
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        Map<String,Object> map = new HashMap<>();
        map = scoreService.getClassLevel(courseId);
        map.put("courseName",courseService.getCourseByCid(courseId).getCourseName());
        map.put("teacherName",teacherService.getTeacher(courseService.getCourseByCid(courseId).getTeacherId()).getTeacherName());
        resultData.setResult(map);
        return resultData;
    }

    @ApiOperation(value="学生添加课程")
    @PostMapping("insertCourse")
    public ResultData<Boolean> insertCourse(@RequestParam("studentId") Integer sid,
                                            @RequestParam("courserId") Integer cid){
        ResultData<Boolean> resultData = new ResultData<>();

        if(scoreService.insertCourse(sid,cid)>0){
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="根据学生的ID查询课程的ID")
    @PostMapping("getCourseId")
    public ResultData<List<Integer>> getCourseId(@RequestParam("studentId") Integer sid){
        ResultData<List<Integer>> resultData = new ResultData<>();
        resultData.setResult(scoreService.getCourseId(sid));
        resultData.setMsg("success");
        resultData.setCode(200);
        return resultData;
    }


}
