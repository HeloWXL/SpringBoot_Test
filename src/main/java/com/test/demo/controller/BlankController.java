package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Blank;
import com.test.demo.service.BlankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangxl
 * @date 2018/11/26 14:59
 */
@Api(tags ="填空题接口")
@RestController
@RequestMapping("blank")
public class BlankController {

    protected Logger logger = LoggerFactory.getLogger(BlankController.class);

    @Resource
    private BlankService blankService;

    @ApiOperation(value="添加填空题")
    @GetMapping("insertBlank")
    public ResultData<Boolean> insertBlank(@RequestParam("blankQuestion") String blankquestion,
                                           @RequestParam("blankAnswer") String blankanswer,
                                           @RequestParam("teacherId") int teacherId) {
        ResultData<Boolean> resultData = new ResultData<>();
        Blank blank = new Blank();
        blank.setBlankQuestion(blankquestion);
        blank.setBlankAnswer(blankanswer);
        blank.setTeacherId(teacherId);
        int i = blankService.insertBlank(blank);
        if(i==1){
            logger.info("添加填空题成功");
            resultData.setMsg("添加填空题成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("添加填空题失败");
            resultData.setMsg("添加填空题失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

    @ApiOperation(value="删除填空题")
    @PostMapping("deleteBlank")
    public ResultData<Boolean> deleteBlank(@RequestParam("blankId") Integer blankId) {
        ResultData<Boolean> resultData = new ResultData<>();
        int i = blankService.deleteBlank(blankId);
        if(i==1){
            logger.info("删除填空题成功");
            resultData.setMsg("删除填空题成功");
            resultData.setCode(200);
            resultData.setResult(true);
            return resultData;
        }else{
            logger.info("删除填空题失败");
            resultData.setMsg("删除填空题失败");
            resultData.setCode(500);
            resultData.setResult(false);
            return resultData;
        }
    }

}
