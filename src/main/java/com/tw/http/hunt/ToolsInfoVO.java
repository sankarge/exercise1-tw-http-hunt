package com.tw.http.hunt;

public class ToolsInfoVO {

    private String name;
    private Long weight;
    private Long value;

    public ToolsInfoVO(String name, Long weight, Long value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Long getWeight() {
        return weight;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ToolsInfoVO{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
