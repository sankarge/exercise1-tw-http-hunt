package com.tw.http.hunt;

import junit.framework.TestCase;
import org.json.simple.JSONArray;

import java.util.Arrays;

public class BestOfToolsFinderTest extends TestCase {

    public void testFind() {
        ToolsInfoVO toolsInfoVO1 = new ToolsInfoVO("knife", 1l, 80l);
        ToolsInfoVO toolsInfoVO2 = new ToolsInfoVO("guns", 5l, 90l);
        ToolsInfoVO toolsInfoVO3 = new ToolsInfoVO("rope", 10l, 60l);
        ToolsInfoVO toolsInfoVO4 = new ToolsInfoVO("water", 8l, 40l);
        ToolsInfoVO[] vos = new ToolsInfoVO[]{toolsInfoVO1, toolsInfoVO2, toolsInfoVO3, toolsInfoVO4};

        BestOfToolsFinder bestOfToolsFinder = new BestOfToolsFinder(Arrays.asList(vos), 15l);
        JSONArray jsonArray = bestOfToolsFinder.find();
        System.out.println(jsonArray.toJSONString());
    }
}