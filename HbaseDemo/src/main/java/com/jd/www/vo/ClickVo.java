package com.jd.www.vo;

import java.io.Serializable;

/**
 * Created by qiuxiangu on 2016/6/3.
 */
public class ClickVo implements Serializable {
    private String mbaMuid;
    private String lv1EventId;
    private String lv1EventParam;
    private Double Lv1clickTime;
    private String lv3EventId;
    private String lv3EventParam;
    private Double Lv3clickTime;
    private String lv4EventId;
    private String lv4EventParam;
    private Double Lv4clickTime;

    @Override
    public String toString() {
        return "ClickVo{" +
                "mbaMuid='" + mbaMuid + '\'' +
                ", lv1EventId='" + lv1EventId + '\'' +
                ", lv1EventParam='" + lv1EventParam + '\'' +
                ", Lv1clickTime=" + Lv1clickTime +
                ", lv3EventId='" + lv3EventId + '\'' +
                ", lv3EventParam='" + lv3EventParam + '\'' +
                ", Lv3clickTime=" + Lv3clickTime +
                ", lv4EventId='" + lv4EventId + '\'' +
                ", lv4EventParam='" + lv4EventParam + '\'' +
                ", Lv4clickTime=" + Lv4clickTime +
                '}';
    }

    public String getMbaMuid() {
        return mbaMuid;
    }

    public void setMbaMuid(String mbaMuid) {
        this.mbaMuid = mbaMuid;
    }

    public String getLv1EventId() {
        return lv1EventId;
    }

    public void setLv1EventId(String lv1EventId) {
        this.lv1EventId = lv1EventId;
    }

    public String getLv1EventParam() {
        return lv1EventParam;
    }

    public void setLv1EventParam(String lv1EventParam) {
        this.lv1EventParam = lv1EventParam;
    }

    public Double getLv1clickTime() {
        return Lv1clickTime;
    }

    public void setLv1clickTime(Double lv1clickTime) {
        Lv1clickTime = lv1clickTime;
    }

    public String getLv3EventId() {
        return lv3EventId;
    }

    public void setLv3EventId(String lv3EventId) {
        this.lv3EventId = lv3EventId;
    }

    public String getLv3EventParam() {
        return lv3EventParam;
    }

    public void setLv3EventParam(String lv3EventParam) {
        this.lv3EventParam = lv3EventParam;
    }

    public Double getLv3clickTime() {
        return Lv3clickTime;
    }

    public void setLv3clickTime(Double lv3clickTime) {
        Lv3clickTime = lv3clickTime;
    }

    public String getLv4EventId() {
        return lv4EventId;
    }

    public void setLv4EventId(String lv4EventId) {
        this.lv4EventId = lv4EventId;
    }

    public String getLv4EventParam() {
        return lv4EventParam;
    }

    public void setLv4EventParam(String lv4EventParam) {
        this.lv4EventParam = lv4EventParam;
    }

    public Double getLv4clickTime() {
        return Lv4clickTime;
    }

    public void setLv4clickTime(Double lv4clickTime) {
        Lv4clickTime = lv4clickTime;
    }
}
