package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Score;
import com.test.demo.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation(value="根据课程的ID查询课程成绩")
    @PostMapping("getScoreByCourseId")
    public ResultData<List<Score>> getScoreByCourseId(@RequestParam("cid") Integer cid) {
        ResultData<List<Score>> resultData = new ResultData<>();

        if (scoreService.getScoreByCourseId(cid).size() > 0) {
            resultData.setCode(200);
            resultData.setMsg("查询成功");
            resultData.setResult(scoreService.getScoreByCourseId(cid));
            return resultData;
        } else {
            resultData.setCode(500);
            resultData.setMsg("查询失败");
            resultData.setResult(null);
            return resultData;
        }
    }

}
