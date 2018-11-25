package com.test.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author wangxl
 * @date 2018/11/22 16:16
 */
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResultData<String> myExceptionErrorHandler(MyException ex) throws Exception {
        logger.error("myExceptionErrorHandler info:{}", ex.getMessage());
        ResultData<String> r = new ResultData<>();
        r.setMsg(ex.getMsg());
        r.setCode(ex.getCode());
        return r;
    }
}
