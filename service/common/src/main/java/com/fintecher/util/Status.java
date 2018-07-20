package com.fintecher.util;

import java.util.Objects;

/**
 * 状态的枚举
 * Created by ChenChang on 2017/3/9.
 */
public enum Status {
    Enable(10002), Disable(10003);
    private Integer value;

    Status(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * 判断参数合法性
     */
    public static boolean isValidValue(Integer value) {
        for (Status status : Status.values()) {
            if (Objects.equals(status.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
