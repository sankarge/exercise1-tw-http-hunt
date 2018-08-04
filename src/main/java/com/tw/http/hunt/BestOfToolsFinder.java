package com.tw.http.hunt;

import org.json.simple.JSONArray;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class BestOfToolsFinder {

    private List<ToolsInfoVO> infoVOList;

    private Long maximumWeight;

    public BestOfToolsFinder(List<ToolsInfoVO> infoVOList, Long maximumWeight) {
        this.infoVOList = infoVOList;
        this.maximumWeight = maximumWeight;
    }

    public JSONArray find() {
        int currentWeight = 0;
        sort(infoVOList, (v1, v2) -> Long.compare(v2.getValue(), v1.getValue()));

        JSONArray jsonArray = new JSONArray();
        for (ToolsInfoVO toolsInfoVO : infoVOList) {
            if (currentWeight + toolsInfoVO.getWeight() <= maximumWeight) {
                currentWeight += toolsInfoVO.getWeight();
                jsonArray.add(toolsInfoVO.getName());
            }
        }
        sort(jsonArray);
        return jsonArray;
    }
}
