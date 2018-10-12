package com.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class CouponDispatch {
    private String dispatchedId;

    private String promotionId;

    private BigDecimal dispatchedTotalAmount;

    private BigDecimal dispatchedSuccess;

    private BigDecimal dispatchedFailed;

    private Date dispatchedTime;

    private String dispatchedExecutor;

    private String dispatchedStatus;

    private String remark;

    private String mallId;

    public String getDispatchedId() {
        return dispatchedId;
    }

    public void setDispatchedId(String dispatchedId) {
        this.dispatchedId = dispatchedId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public BigDecimal getDispatchedTotalAmount() {
        return dispatchedTotalAmount;
    }

    public void setDispatchedTotalAmount(BigDecimal dispatchedTotalAmount) {
        this.dispatchedTotalAmount = dispatchedTotalAmount;
    }

    public BigDecimal getDispatchedSuccess() {
        return dispatchedSuccess;
    }

    public void setDispatchedSuccess(BigDecimal dispatchedSuccess) {
        this.dispatchedSuccess = dispatchedSuccess;
    }

    public BigDecimal getDispatchedFailed() {
        return dispatchedFailed;
    }

    public void setDispatchedFailed(BigDecimal dispatchedFailed) {
        this.dispatchedFailed = dispatchedFailed;
    }

    public Date getDispatchedTime() {
        return dispatchedTime;
    }

    public void setDispatchedTime(Date dispatchedTime) {
        this.dispatchedTime = dispatchedTime;
    }

    public String getDispatchedExecutor() {
        return dispatchedExecutor;
    }

    public void setDispatchedExecutor(String dispatchedExecutor) {
        this.dispatchedExecutor = dispatchedExecutor;
    }

    public String getDispatchedStatus() {
        return dispatchedStatus;
    }

    public void setDispatchedStatus(String dispatchedStatus) {
        this.dispatchedStatus = dispatchedStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }
}