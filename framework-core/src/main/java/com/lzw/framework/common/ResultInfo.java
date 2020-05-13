package com.lzw.framework.common;

import java.io.Serializable;
/**
* @author lijie
* @date 2020/5/13 16:33
*
**/
public class ResultInfo<T> implements Serializable {

    private int code;

    private String message;

    private T result;

    public ResultInfo() {
    }

    public ResultInfo(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
