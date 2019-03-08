package com.example.vo.json;

import lombok.Data;
import java.io.Serializable;

@Data
public class JsonResult implements Serializable {

    private static final String MSG_SUCCESS = "SUCCESS";
    public static final String MSG_FAIL = "FAIL";

    /**
     * 成功状态code
     */
    private static final int CODE_SUCCESS = 0;

    /**
     * 系统失败状态code
     */
    public static final int CODE_FAIL = -1;


    /**
     * 验签失败code
     */
    private static final int CODE_SIGN = 99999;

    /**
     * 请求参数为空msg
     */
    private static final String MSG_SIGN = "验签失败";

    /**
     * 请求参数为空code
     */
    private static final int CODE_PARAMETEREMPTY = 10000;
    /**
     * 请求参数为空msg
     */
    private static final String MSG_PARAMETEREMPTY = "参数不能为空";

    /**
     * 商品出错
     */
    public static final int CODE_PORDUCT_NO = 10001;
    /**
     * 商品出错msg
     */
    public static final String MSG_PORDUCT_NO = "获取商品失败";

    /**
     * 商品已下架
     */
    public static final int CODE_PORDUCT_STATUS_NO = 10002;
    /**
     * 商品已下架msg
     */
    public static final String MSG_PORDUCT_STATUS_NO = "商品已下架";

    /**
     * 商品库存不足
     */
    public static final int CODE_PORDUCT_STORAGE_NO = 10003;
    /**
     * 请求参数为空msg
     */
    public static final String MSG_PORDUCT_STORAGE_NO  = "商品库存不足";

    /**
     * 收银台失败
     */
    public static final int CODE_CASHIER = 30000;
    /**
     * 收银台失败msg
     */
    public static final String MSG_CASHIER  = "收银台维护中请稍后重试";

    /**
     * 请求成功 数据为空code
     */
    private static final int CODE_DATAEMPTY = 20000;

    /**
     * 请求成功 数据为空msg
     */
    private static final String MSG_DATAEMPTY = "无符合条件数据";



    private int code;   //返回码 非0即失败
    private String msg; //消息提示
    private Object data; //返回的数据

    public JsonResult() {
    }

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static JsonResult failed() {
        return failed(MSG_FAIL);
    }

    public static JsonResult failed(String msg) {
        return failed(CODE_FAIL, msg);
    }

    public static JsonResult failed(int code, String msg) {
        return new JsonResult(code, msg, "");
    }

    public static JsonResult failed(int code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

    /**
     * 验签失败结果集
     *
     * @return
     */
    public static JsonResult failedSign() {
        return new JsonResult(CODE_SIGN, MSG_SIGN, "");
    }


    /**
     * 参数为空结果集
     *
     * @return
     */
    public static JsonResult failedParamaterEmpty() {
        return new JsonResult(CODE_PARAMETEREMPTY, MSG_PARAMETEREMPTY, "");
    }

    /**
     * 数据为空结果集
     *
     * @return
     */
    public static JsonResult failedDataEmpty() {
        return new JsonResult(CODE_DATAEMPTY, MSG_DATAEMPTY, "");
    }

    /**
     * 收银台获取失败结果
     */
    public static JsonResult failedCashier() {
        return new JsonResult(CODE_CASHIER,MSG_CASHIER,"");
    }
}
