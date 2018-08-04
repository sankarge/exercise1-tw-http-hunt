package com.tw.http.hunt;

import junit.framework.TestCase;
import org.json.simple.JSONArray;

import java.util.Arrays;

public class MaxUsedToolFinderTest extends TestCase {

    public void testFind() {
        ToolsUsageVO toolsUsageVO1 = new ToolsUsageVO("knife", "2017-01-30 10:00:00", "2017-01-30 10:10:00");
        ToolsUsageVO toolsUsageVO2 = new ToolsUsageVO("guns", "2017-01-30 10:15:00", "2017-01-30 10:20:00");
        ToolsUsageVO toolsUsageVO3 = new ToolsUsageVO("guns", "2017-01-30 11:00:00", "2017-01-30 11:10:00");
        ToolsUsageVO toolsUsageVO4 = new ToolsUsageVO("knife", "2017-01-30 11:10:00", "2017-01-30 10:20:00");
        ToolsUsageVO toolsUsageVO5 = new ToolsUsageVO("rope", "2017-01-30 13:00:00", "2017-01-30 14:00:00");

        ToolsUsageVO[] vos = new ToolsUsageVO[]{toolsUsageVO1, toolsUsageVO2, toolsUsageVO3, toolsUsageVO4, toolsUsageVO5};
        MaxUsedToolFinder maxUsedToolFinder = new MaxUsedToolFinder(Arrays.asList(vos));
        JSONArray jsonArray = maxUsedToolFinder.sortByUsage();
        System.out.println(jsonArray.toJSONString());
    }
}