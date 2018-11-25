package com.test.demo.common;

import java.io.Serializable;

/**
 * @author wangxl
 * @date 2018/11/22 16:14
 */
public class ResultData<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4250719891313555820L;
    /**
     * 返回结果集
     */
    private T result;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 响应码
     */
    private Integer code;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
