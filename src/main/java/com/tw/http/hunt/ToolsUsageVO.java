package com.tw.http.hunt;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ToolsUsageVO {

    private String name;
    private DateTime startTime;
    private DateTime endTime;

    public ToolsUsageVO(String name, String startTime, String endTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        this.name = name;
        this.startTime = dtf.parseDateTime(startTime);
        this.endTime = dtf.parseDateTime(endTime);
    }

    public String getName() {
        return name;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }
}
