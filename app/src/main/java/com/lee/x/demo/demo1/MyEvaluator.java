package com.lee.x.demo.demo1;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by android on 2017/4/27.
 */
public class MyEvaluator implements TypeEvaluator<PointF> {


    private PointF pointF1;
    private PointF pointF2;

    public MyEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

        PointF point = new PointF();
        point.x = startValue.x + fraction;
        point.y = endValue.y + fraction;

        return point;
    }
}
