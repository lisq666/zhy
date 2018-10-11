package com.example.Exception;

import lombok.Data;


@Data
public class JsonResult {
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
        return success(new Object());
    }
    public static JsonResult success(Object data) {
        return new JsonResult(0, "解析成功", data);
    }

    public static JsonResult failed() {
        return failed("解析失败");
    }
    public static JsonResult failed(String msg) {
        return failed(-1, msg);
    }
    public static JsonResult failed(int code, String msg) {
        return new JsonResult(code, msg, new Object());
    }

}
