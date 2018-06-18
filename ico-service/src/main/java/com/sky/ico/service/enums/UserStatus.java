package com.sky.ico.service.enums;

import com.sky.framework.common.mybatis.IntegerValuedEnum;

public enum UserStatus implements IntegerValuedEnum {
    /** 未启用 */
    NOT_USED(0),

    /** 已启用 */
    USED(1),
    ;

    private int value;

    private UserStatus(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
