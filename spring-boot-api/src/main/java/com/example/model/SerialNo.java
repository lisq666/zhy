package com.example.model;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "b2c_serial_no")
public class SerialNo implements Serializable{
    @Id
    private String serialName;
    private Long serialValue;
    private String serialDesc;
    private Integer serialPoolSize;
    private String serialFormat;

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName == null ? null : serialName.trim();
    }


    public Long getSerialValue() {
        return serialValue;
    }

    public void setSerialValue(Long serialValue) {
        this.serialValue = serialValue;
    }

    public String getSerialDesc() {
        return serialDesc;
    }

    public void setSerialDesc(String serialDesc) {
        this.serialDesc = serialDesc == null ? null : serialDesc.trim();
    }

    public Integer getSerialPoolSize() {
        if(this.serialPoolSize==null){
            return Integer.valueOf(5);
        }
        return serialPoolSize;
    }

    public void setSerialPoolSize(Integer serialPoolSize) {
        this.serialPoolSize = serialPoolSize;
    }

    public String getSerialFormat() {
        if(this.serialFormat==null){
            return "{##########}";
        }
        return serialFormat;
    }

    public void setSerialFormat(String serialFormat) {
        this.serialFormat = serialFormat == null ? null : serialFormat.trim();
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof SerialNo)) {
            return false;
        }
        SerialNo other = (SerialNo) that;
        return this.getSerialName() == null ? other == null : this.getSerialName().equals(other.getSerialName())
                && this.getSerialValue() == null ? other == null : this.getSerialValue().equals(other.getSerialValue())
                && this.getSerialDesc() == null ? other == null : this.getSerialDesc().equals(other.getSerialDesc())
                && this.getSerialPoolSize() == null ? other == null : this.getSerialPoolSize().equals(other.getSerialPoolSize())
                && this.getSerialFormat() == null ? other == null : this.getSerialFormat().equals(other.getSerialFormat());
    }


    public int hashCode() {
        int hash = 23;
        if (getSerialName() != null) {
            hash *= getSerialName().hashCode();
        }
        if (getSerialValue() != null) {
            hash *= getSerialValue().hashCode();
        }
        if (getSerialDesc() != null) {
            hash *= getSerialDesc().hashCode();
        }
        if (getSerialPoolSize() != null) {
            hash *= getSerialPoolSize().hashCode();
        }
        if (getSerialFormat() != null) {
            hash *= getSerialFormat().hashCode();
        }
        return hash;
    }

}