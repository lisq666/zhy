package com.example.vo.json;

import java.io.Serializable;

public class ResultData implements Serializable {

    private static final String CONTENT_SUCCESS = "SUCCESS";
    private static final String CONTENT_ISEMPTY = "ISEMPTY";
    private String msg;
    private Object Data;
    private Boolean status;

    public static ResultData success(){
        ResultData resultData=new ResultData();
        resultData.setStatus(true);
        resultData.setMsg(CONTENT_ISEMPTY);
        return resultData;
    }

    public static ResultData success(Object data){
        ResultData resultData =success();
        if(data != null){
            resultData.setData(data);
            resultData.setMsg(CONTENT_SUCCESS);
        }
        return resultData;
    }

    public static ResultData failure(String msg){
        ResultData resultData=new ResultData();
        resultData.setStatus(false);
        resultData.setMsg(msg);
        return resultData;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
