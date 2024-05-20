package com.lazymarks.platform.api.enums;

public enum Operator {
    EQ("equal to"),
    GT("greater than"),
    GTEQ("greater than equal to"),
    LT("less than"),
    LTEQ("less than equal to");

    private String desc;

    private Operator(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
