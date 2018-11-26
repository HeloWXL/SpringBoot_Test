package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Select;
import com.test.demo.service.SelectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangxl
 * @date 2018/11/26 15:04
 */
@Api(tags ="选择题接口")
@RestController
@RequestMapping("select")
public class SelectController {

    protected Logger logger = LoggerFactory.getLogger(SelectController.class);

    @Resource
    private SelectService selectService;

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
        Select select = new Select();
        select.setSelectQuestion(selectquestion);
        select.setSelectA(a);
        select.setSelectB(b);
        select.setSelectC(c);
        select.setSelestD(d);
        select.setAnswer(answer);
        select.setTeacherId(teacherId);
        int i = selectService.insertSelest(select);
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

    @ApiOperation(value="删除填空题")
    @PostMapping("deleteSelect")
    public ResultData<Boolean> deleteBlank(@RequestParam("blankId") Integer selectId) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i = selectService.deleteSelect(selectId);
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

}
