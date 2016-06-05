package com.jd.www.vo;

import java.io.Serializable;

/**
 * Created by qiuxiangu on 2016/6/3.
 */
public class ClickVo implements Serializable {
    private String mbaMuid;
    private String eventId;
    private String eventParam;
    private Double clickTime;

    public ClickVo(String mbaMuid, String eventId, String eventParam, Double clickTime) {
        this.mbaMuid = mbaMuid;
        this.eventId = eventId;
        this.eventParam = eventParam;
        this.clickTime = clickTime;
    }

    public String getMbaMuid() {
        return mbaMuid;
    }

    public void setMbaMuid(String mbaMuid) {
        this.mbaMuid = mbaMuid;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventParam() {
        return eventParam;
    }

    public void setEventParam(String eventParam) {
        this.eventParam = eventParam;
    }

    public Double getClickTime() {
        return clickTime;
    }

    public void setClickTime(Double clickTime) {
        this.clickTime = clickTime;
    }

    @Override
    public String toString() {
        return "ClickVo{" +
                "mbaMuid='" + mbaMuid + '\'' +
                ", eventId='" + eventId + '\'' +
                ", eventParam='" + eventParam + '\'' +
                ", clickTime=" + clickTime +
                '}';
    }
}
