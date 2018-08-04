package com.tw.http.hunt;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class MaxUsedToolFinder {

    private List<ToolsUsageVO> usageVOList;

    public MaxUsedToolFinder(List<ToolsUsageVO> usageVOList) {
        this.usageVOList = usageVOList;
    }

    public JSONArray sortByUsage() {
        Map<String, Long> durationMap = new HashMap<>();
        for (ToolsUsageVO usageVO : usageVOList) {
            String name = usageVO.getName();
            long usage = usageVO.getEndTime().getMinuteOfHour() - usageVO.getStartTime().getMinuteOfHour();
            usage = usage == 0 ? 60 : usage;
            if (!durationMap.containsKey(name)) {
                durationMap.put(name, usage);
            } else {
                Long diff = durationMap.get(name);
                durationMap.put(name, diff + usage);
            }
        }

        Map<Long, String> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        durationMap.keySet().forEach( e -> sortedMap.put(durationMap.get(e), e));

        JSONArray jsonArray = new JSONArray();
        sortedMap.keySet().forEach(e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", sortedMap.get(e));
            jsonObject.put("timeUsedInMinutes", e);
            jsonArray.add(jsonObject);
        });

        return jsonArray;
    }
}
