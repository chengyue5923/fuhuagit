package com.fhzc.app.android.utils.android;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by yanbo on 2016/8/5.
 */
public class MathUtil {
    /**
     * 计算百分比
     *
     * @param num
     * @param total
     * @return
     */
    public static float getPercent(int num, int total) {

        // 设置精确到小数点后2位
        float f= (float)num/total;
        // 创建一个数值格式化对象
        NumberFormat numberFormat =new DecimalFormat("0.00");
        String s = numberFormat.format(f*100);
        return Float.parseFloat(s);
    }
}
