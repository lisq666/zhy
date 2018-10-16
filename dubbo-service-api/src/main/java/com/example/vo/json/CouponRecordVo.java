package com.example.vo.json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CouponRecordVo implements Serializable {

    private static final long serialVersionUID = -1243413595012637834L;

    private String couponId;

    private String promotionId;

    private BigDecimal couponAmount;

    private BigDecimal denominationAmount;

    private Date couponGenTime;

    private Date couponExpireDate;

    private String acceptedStatus;

    private String memberId;

    private String couponStatus;

    private BigDecimal couponUsedAmount;

    private String dispatchedStatus;

    private String chenel;

    private String recordType;

    private Date couponPutwayTime;

    /**
     * 系统生成来源 0供销 1智慧云
     */
    private int sysSource;

    /**
     * 使用来源 1供销 2智慧云
     */
    private String usedSource;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getDenominationAmount() {
        return denominationAmount;
    }

    public void setDenominationAmount(BigDecimal denominationAmount) {
        this.denominationAmount = denominationAmount;
    }

    public Date getCouponGenTime() {
        return couponGenTime;
    }

    public void setCouponGenTime(Date couponGenTime) {
        this.couponGenTime = couponGenTime;
    }

    public Date getCouponExpireDate() {
        return couponExpireDate;
    }

    public void setCouponExpireDate(Date couponExpireDate) {
        this.couponExpireDate = couponExpireDate;
    }

    public String getAcceptedStatus() {
        return acceptedStatus;
    }

    public void setAcceptedStatus(String acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public BigDecimal getCouponUsedAmount() {
        return couponUsedAmount;
    }

    public void setCouponUsedAmount(BigDecimal couponUsedAmount) {
        this.couponUsedAmount = couponUsedAmount;
    }

    public String getDispatchedStatus() {
        return dispatchedStatus;
    }

    public void setDispatchedStatus(String dispatchedStatus) {
        this.dispatchedStatus = dispatchedStatus;
    }

    public String getChenel() {
        return chenel;
    }

    public void setChenel(String chenel) {
        this.chenel = chenel;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Date getCouponPutwayTime() {
        return couponPutwayTime;
    }

    public void setCouponPutwayTime(Date couponPutwayTime) {
        this.couponPutwayTime = couponPutwayTime;
    }

    public int getSysSource() {
        return sysSource;
    }

    public void setSysSource(int sysSource) {
        this.sysSource = sysSource;
    }

    public String getUsedSource() {
        return usedSource;
    }

    public void setUsedSource(String usedSource) {
        this.usedSource = usedSource;
    }
}