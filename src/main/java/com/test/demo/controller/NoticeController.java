package com.test.demo.controller;

import com.test.demo.common.ResultData;
import com.test.demo.model.Admin;
import com.test.demo.model.Notice;
import com.test.demo.service.NoticeService;
import com.test.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.image.IntegerInterleavedRaster;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/27 9:11
 */
@Api(tags ="公告接口")
@RestController
@RequestMapping("notice")
public class NoticeController {
    protected Logger logger = LoggerFactory.getLogger(NoticeController.class);
    @Resource
    private NoticeService noticeService;
    @ApiOperation(value="添加一条公告信息")
    @PostMapping("insertOneNotice")
    public ResultData<Boolean> insertOneNotice(@RequestParam("title") String title,
                                               @RequestParam("content") String content,
                                               @RequestParam("tid") Integer tid){
        ResultData<Boolean> resultData = new ResultData<>();
        Notice notice = new Notice();
        notice.setNoticeContent(content);
        notice.setTeacherId(tid);
        notice.setNoticeTitle(title);
        if(noticeService.insertNotice(notice)==1){
            resultData.setResult(true);
            resultData.setCode(200);
            resultData.setMsg("添加成功");
            return resultData;
        }else{
            resultData.setResult(null);
            resultData.setCode(500);
            resultData.setMsg("添加失败");
            return resultData;
        }
    }

    @ApiOperation(value="获取公告列表")
    @PostMapping("getAllNotice")
    public ResultData<Map<String,Object>> insertOneNotice(@RequestParam("pageNo")Integer pageNo,
                                                          @RequestParam("pageSize") Integer pageSize,
                                                          @RequestParam("tid") Integer tid) {
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        resultData.setResult(noticeService.getAllNotice(tid,pageNo,pageSize));
        resultData.setCode(200);
        resultData.setMsg("获取公告列表成功");
        return resultData;
    }

}
