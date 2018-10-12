package com.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class CouponDispatchDetail {
    private String dispatchedDetailId;

    private String promotionId;

    private String dispatchedMemberId;

    private String dispatchedMemberName;

    private BigDecimal dispatchedAmount;

    private String dispatchedId;

    private String dispatchedDetailStatus;

    private BigDecimal dispatchedDonomination;

    private BigDecimal dispatchedCount;

    private String errorDesc;

    private String status;

    private String isSendAlready;

    private Date createDate;

    private Date lastModifyDate;

    private String creatorId;

    private String reviewerId;

    private String remark;

    private String mallId;

    private Date getCouponDate;

    private String couponOverdueStatus;

    private String deleteCouponStatus;

    public String getDispatchedDetailId() {
        return dispatchedDetailId;
    }

    public void setDispatchedDetailId(String dispatchedDetailId) {
        this.dispatchedDetailId = dispatchedDetailId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getDispatchedMemberId() {
        return dispatchedMemberId;
    }

    public void setDispatchedMemberId(String dispatchedMemberId) {
        this.dispatchedMemberId = dispatchedMemberId;
    }

    public String getDispatchedMemberName() {
        return dispatchedMemberName;
    }

    public void setDispatchedMemberName(String dispatchedMemberName) {
        this.dispatchedMemberName = dispatchedMemberName;
    }

    public BigDecimal getDispatchedAmount() {
        return dispatchedAmount;
    }

    public void setDispatchedAmount(BigDecimal dispatchedAmount) {
        this.dispatchedAmount = dispatchedAmount;
    }

    public String getDispatchedId() {
        return dispatchedId;
    }

    public void setDispatchedId(String dispatchedId) {
        this.dispatchedId = dispatchedId;
    }

    public String getDispatchedDetailStatus() {
        return dispatchedDetailStatus;
    }

    public void setDispatchedDetailStatus(String dispatchedDetailStatus) {
        this.dispatchedDetailStatus = dispatchedDetailStatus;
    }

    public BigDecimal getDispatchedDonomination() {
        return dispatchedDonomination;
    }

    public void setDispatchedDonomination(BigDecimal dispatchedDonomination) {
        this.dispatchedDonomination = dispatchedDonomination;
    }

    public BigDecimal getDispatchedCount() {
        return dispatchedCount;
    }

    public void setDispatchedCount(BigDecimal dispatchedCount) {
        this.dispatchedCount = dispatchedCount;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsSendAlready() {
        return isSendAlready;
    }

    public void setIsSendAlready(String isSendAlready) {
        this.isSendAlready = isSendAlready;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
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

    public Date getGetCouponDate() {
        return getCouponDate;
    }

    public void setGetCouponDate(Date getCouponDate) {
        this.getCouponDate = getCouponDate;
    }

    public String getCouponOverdueStatus() {
        return couponOverdueStatus;
    }

    public void setCouponOverdueStatus(String couponOverdueStatus) {
        this.couponOverdueStatus = couponOverdueStatus;
    }

    public String getDeleteCouponStatus() {
        return deleteCouponStatus;
    }

    public void setDeleteCouponStatus(String deleteCouponStatus) {
        this.deleteCouponStatus = deleteCouponStatus;
    }
}