package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Exam;
import com.test.demo.service.CourseService;
import com.test.demo.service.ExamService;
import com.test.demo.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/12/2
 */


@Api(tags ="试卷接口")
@RestController
@RequestMapping("exam")
public class ExamController {
    protected Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Resource
    private ExamService examService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private CourseService courseService;

    @ApiOperation(value="添加考试试卷")
    @GetMapping("insertExam")
    public ResultData<Boolean> insertExam(@RequestParam("selectcount") Integer selectCount,
                                          @RequestParam("blankcount") Integer blankCount,
                                          @RequestParam("teacherid") Integer teacherId,
                                          @RequestParam("courseId") Integer courseId) {
        ResultData<Boolean> resultData = new ResultData<>();

        Exam exam = new Exam();
        exam.setCourseId(courseId);
        exam.setExamName(courseService.getCourseByCid(courseId).getCourseName());
        exam.setTeacherId(teacherId);
        exam.setExamChoice(selectCount);
        exam.setExamBlank(blankCount);


        if(examService.insertExam(exam)==1){
            resultData.setResult(true);
            resultData.setCode(200);
            logger.info("获取课程列表成功");
            resultData.setMsg("添加成功");
            return resultData;
        }else{
            resultData.setResult(false);
            resultData.setMsg("添加试卷失败");
            resultData.setCode(500);
            logger.info("添加试卷失败");
            return resultData;
        }
    }

    @ApiOperation(value="获取考试信息")
    @GetMapping("getAllExam")
    public ResultData<List<Exam>> getAllExam(Integer tid){
        ResultData<List<Exam>> resultData = new ResultData<>();
        if(examService.getAllExams(tid).size()>0){
            resultData.setMsg("获取成功");
            resultData.setResult(examService.getAllExams(tid));
            resultData.setCode(200);
            return resultData;
        }else{
            resultData.setMsg("获取失败");
            resultData.setResult(null);
            resultData.setCode(500);
            return resultData;
        }
    }


    @ApiOperation(value="根据学生的ID查询考试信息")
    @PostMapping("getExamBySid")
    public ResultData<List<Exam>> getExamBySid(@RequestParam("sid") Integer sid){
        ResultData<List<Exam>> resultData = new ResultData<>();
        List<Integer> integerList = scoreService.getExamId(sid);
        List<Exam> examList = new ArrayList<>();
        for (int i = 0 ;i<integerList.size();i++){
            examList.add(examService.getExamsByExamId(integerList.get(i)));
        }
        if(examList.size()>0){
            resultData.setResult(examList);
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setMsg("暂无考试....");
            return resultData;
        }
    }

}
