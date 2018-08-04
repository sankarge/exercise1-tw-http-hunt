package com.tw.http.hunt;

import java.util.ArrayList;
import java.util.List;

public class ToolsFinder {

    private String hiddenTools;

    private List<String> inputTools;

    public ToolsFinder(String treasure, List<String> inputTools) {
        this.hiddenTools = treasure;
        this.inputTools = inputTools;
    }

    public ArrayList<String> find() {
        char[] chars = hiddenTools.toCharArray();
        ArrayList<String> outputTools = new ArrayList<String>();
        for (String tool : inputTools) {
            String copy = new String(tool);
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (copy.length() == 1) {
                    if (copy.equals(c+"")) {
                        outputTools.add(tool);
                        break;
                    }
                } else if (copy.startsWith(c + "")) {
                    copy = copy.substring(1, copy.length());
                }
            }
        }
        return outputTools;
    }
}
