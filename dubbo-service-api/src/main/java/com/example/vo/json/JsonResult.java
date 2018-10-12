package com.example.vo.json;

import lombok.Data;

import java.io.Serializable;


@Data
public class JsonResult implements Serializable{

    private static final String CONTENT_SUCCESS = "SUCCESS";
    private static final String CONTENT_FAIL = "FAIL";

    private int code;   //返回码 非0即失败
    private String msg; //消息提示
    private Object data; //返回的数据

    public JsonResult(){};

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success() {
        return success("");
    }
    public static JsonResult success(Object data) {
        return new JsonResult(0, CONTENT_SUCCESS, data);
    }

    public static JsonResult failed() {
        return failed(CONTENT_FAIL);
    }
    public static JsonResult failed(String msg) {
        return failed(-1, msg);
    }
    public static JsonResult failed(int code, String msg) {
        return new JsonResult(code, msg, "");
    }

}
