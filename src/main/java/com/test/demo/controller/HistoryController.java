package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.controller.resp.RespHistory;
import com.test.demo.model.History;
import com.test.demo.service.CourseService;
import com.test.demo.service.HistoryService;
import com.test.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangxl
 * @date 2018/12/3 9:10
 */
@Api(tags ="历史记录接口")
@RestController
@RequestMapping("history")
public class HistoryController {
    protected Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @Resource
    private HistoryService historyService;
    @Resource
    private StudentService studentService;
    @Resource
    private CourseService courseService;
    @ApiOperation(value="添加历史记录")
    @PostMapping("insertHistory")
    public ResultData<Boolean> insertHistory(@RequestParam("studentId") Integer studentId,
                                             @RequestParam("courseId") Integer courseId){
        ResultData<Boolean> resultData = new ResultData<>();
        if(historyService.insertHistory(studentId,courseId)>0){
            resultData.setCode(200);
            resultData.setMsg("添加成功");
            resultData.setResult(true);
            return resultData;
        }else{
            resultData.setMsg("添加失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }
    @ApiOperation(value="获取历史记录")
    @PostMapping("getAllHistory")
    public ResultData<List<RespHistory>> getAllHistory(){
        ResultData<List<RespHistory>> resultData = new ResultData<>();
        List<RespHistory> respHistoryList = new ArrayList<>();
        List<History> historyList = historyService.getAllHistory();
        for (History h: historyList
             ) {
            RespHistory respHistory = new RespHistory();
            String studentName = studentService.getStudentBySid(h.getStudentId()).getStudentName();
            String courserName = courseService.getCourseByCid(h.getCourseId()).getCourseName();
            respHistory.setCourseName(courserName);
            respHistory.setStudentName(studentName);
            respHistory.setCreateTime(h.getCreatetime());
            respHistoryList.add(respHistory);
        }
        resultData.setResult(respHistoryList);
        resultData.setCode(200);
        resultData.setMsg("获取成功");
        return resultData;
    }
    @ApiOperation(value="根據學生的ID查詢課程ID列表")
    @PostMapping("getCourseByStudentId")
    public List<Integer> getCourseByStudentId(@RequestParam("sid") Integer sid){
        return historyService.getCourseByStudentId(sid);
    }
    @ApiOperation(value="返回歷史表中學生的ID")
    @PostMapping("getAllStudentId")
    public List<Integer> getAllStudentId(){
        return historyService.getAllStudentId();
    }

    @ApiOperation(value="根据学生的ID查询学生的历史记录")
    @PostMapping("getHistoryByStudentId")
    public ResultData<List<RespHistory>> getHistoryByStudentId(@RequestParam("studentId") Integer studentId){
        ResultData<List<RespHistory>> resultData = new ResultData<>();
        List<History> historyList = historyService.getHistoryByStudentId(studentId);
        List<RespHistory> respHistoryList = new ArrayList<>();

        for (History h: historyList
             ) {
            RespHistory respHistory = new RespHistory();
            int sid = h.getStudentId();
            int cid = h.getCourseId();
          respHistory.setStudentName(studentService.getStudentBySid(sid).getStudentName());
          respHistory.setCourseName(courseService.getCourseByCid(cid).getCourseName());
          respHistory.setCreateTime(h.getCreatetime());
          respHistoryList.add(respHistory);
        }

        if(respHistoryList.size()>0){
            resultData.setResult(respHistoryList);
            resultData.setCode(200);
            resultData.setMsg("查询成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setCode(500);
            resultData.setMsg("空空如也");
            return resultData;
        }

    }


}
