package com.techcam.type;

public enum DiscountType {

    PERCENT(0),
    MONEY(1);

    int value;

    DiscountType(int value) {
    }

    public int getValue() {
        return value;
    }

}
