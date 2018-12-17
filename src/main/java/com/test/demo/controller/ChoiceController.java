package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Choice;
import com.test.demo.service.ChoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/26 15:04
 */
@Api(tags ="选择题接口")
@RestController
@RequestMapping("select")
public class ChoiceController {
    protected Logger logger = LoggerFactory.getLogger(ChoiceController.class);
    @Resource
    private ChoiceService choiceService;
    @ApiOperation(value="添加选择题")
    @GetMapping("insertSelect")
    public ResultData<Boolean> insertBlank(@RequestParam("selectQuestion") String selectquestion,
                                           @RequestParam("selectA") String a,
                                           @RequestParam("selectB") String b,
                                           @RequestParam("selectC") String c,
                                           @RequestParam("selectD") String d,
                                           @RequestParam("Answer") String answer,
                                           @RequestParam("teacherId") Integer teacherId) {
        ResultData<Boolean> resultData = new ResultData<>();
        Choice choice = new Choice();
        choice.setSelectQuestion(selectquestion);
        choice.setSelectA(a);
        choice.setSelectB(b);
        choice.setSelectC(c);
        choice.setSelectD(d);
        choice.setAnswer(answer);
        choice.setTeacherId(teacherId);
        int i = choiceService.insertSelest(choice);
        if(i==1){
            logger.info("添加选择题成功");
            resultData.setMsg("添加选择题成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("添加选择题失败");
            resultData.setMsg("添加选择题失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="删除选择题")
    @PostMapping("deleteSelect")
    public ResultData<Boolean> deleteBlank(@RequestParam("blankId") Integer selectId) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i = choiceService.deleteSelect(selectId);
        if(i==1){
            logger.info("删除选择题成功");
            resultData.setMsg("删除选择题成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("删除选择题失败");
            resultData.setMsg("删除选择题失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="根据教师的id获取选择题列表")
    @PostMapping("getSelectByTid")
    public ResultData<Map<String,Object>> getSelectByTid(@RequestParam("tid") Integer tid){
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        Map<String,Object>  map = choiceService.getSelectByTid(tid);
        resultData.setMsg("成功获取选择题");
        resultData.setCode(200);
        resultData.setResult(map);
        return resultData;
    }



    @ApiOperation(value="获取选择题列表")
    @GetMapping("getAllChoices")
    public ResultData<List<Choice>> getAllChoices(){
        ResultData<List<Choice>> resultData = new ResultData<>();
        resultData.setMsg("获取选择题列表成功");
        resultData.setCode(200);
        resultData.setResult(choiceService.getAllChoices());
        return resultData;
    }
}
