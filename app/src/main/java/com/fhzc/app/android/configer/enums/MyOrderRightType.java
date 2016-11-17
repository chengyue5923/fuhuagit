package com.fhzc.app.android.configer.enums;

/**
 * Created by yanbo on 2016/8/1.
 */
public enum  MyOrderRightType {
    ORDER_RIGHT_TYPE_ORDER(0),
    ORDER_RIGHT_TYPE_SUCCESS(1),
    ORDER_RIGHT_TYPE_FINISH(2);
    private int type;
    MyOrderRightType(int method) {
        this.type = method;
    }
    public int getType() {
        return type;
    }
}
