package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.controller.resp.RespHistory;
import com.test.demo.controller.resp.RespRecommend;
import com.test.demo.model.History;
import com.test.demo.recommender.recommend;
import com.test.demo.service.CourseService;
import com.test.demo.service.HistoryService;
import com.test.demo.service.StudentService;
import com.test.demo.service.TeacherService;
import com.test.demo.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private TeacherService teacherService;
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
    @ApiOperation(value="根据学生的的ID查询课程ID列表")
    @PostMapping("getCourseByStudentId")
    public List<Integer> getCourseByStudentId(@RequestParam("sid") Integer sid){
        return historyService.getCourseByStudentId(sid);
    }
    @ApiOperation(value="返回历史表中学生的ID")
    @PostMapping("getAllStudentId")
    public List<Integer> getAllStudentId(){
        return historyService.getAllStudentId();
    }

    @ApiOperation(value="根据学生的ID查询学生的历史记录")
    @PostMapping("getHistoryByStudentId")
    public ResultData<List<RespHistory>> getHistoryByStudentId(@RequestParam("studentId") int studentId){
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


    @ApiOperation(value="推荐列表")
    @PostMapping("recommendList")
    public ResultData<List<RespRecommend>>  recommendList(@RequestParam("studentId") Integer studentId){
        ResultData<List<RespRecommend>> resultData = new ResultData<>();
        //创建推荐对象
        recommend rec =  new recommend();

        //我的课程列表
       List<Integer> mylist = historyService.getCourseByStudentId(studentId);
       StringUtil stringUtil = new StringUtil();
       String mystr = stringUtil.listToString(mylist,' ');

        //返回历史表中学生的ID
        List<Integer> integerList =historyService.getAllStudentId() ;
        //声明两个数组  products 是每个学生的浏览课程  person是学生的ID
        String[] products = new String[integerList.size()-1];
        int[] person = new int[integerList.size()-1];

        for (int i = 0 ,j=0; i<integerList.size();i++){
            if(integerList.get(i).equals(studentId)){
                continue;
            }else{
                person[j]=integerList.get(j);
                products[j]=stringUtil.listToString(historyService.getCourseByStudentId(integerList.get(j)),' ');
                j++;
            }
        }

        System.out.println(Arrays.toString(person));
        System.out.println(Arrays.toString(products));

        rec.fit(products);
        double[] doubles = rec.recommendFun(mystr);

        List<String> stringList = new ArrayList<>();
        for (String str : rec.getProduct()){
            stringList.add(str);
        }

        System.out.println(Arrays.toString(doubles));
        System.out.println(rec.getProduct());

        List<RespRecommend> respRecommendList = new ArrayList<>();
//        double max = doubles[0];
//        for (int i = 1; i < doubles.length; i++) {
//            if (doubles[i] > max) {
//                max=doubles[i];
//                System.out.println(max);
//                RespRecommend respRecommend = new RespRecommend();
//                respRecommend.setCourseName(courseService.getCourseByCid(Integer.parseInt(stringList.get(i))).getCourseName());
//                respRecommend.setCoursePicture(courseService.getCourseByCid(Integer.parseInt(stringList.get(i))).getCoursePicture());
//                int tid = courseService.getCourseByCid(Integer.parseInt(stringList.get(i))).getTeacherId();
//                respRecommend.setTeacherName(teacherService.getTeacher(tid).getTeacherName());
//                respRecommendList.add(respRecommend);
//            }
//        }
        resultData.setResult(respRecommendList);
        return resultData;
    }
}
