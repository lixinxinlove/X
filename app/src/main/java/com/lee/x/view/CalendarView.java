package com.lee.x.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by android on 2017/4/27.
 * 时间日历
 */
public class CalendarView extends View {


    private String[] text = new String[]{"日", "一", "二", "三", "四", "五", "六"};

    private int cellWidth = 30;

    private Calendar calendar;

    private Paint mPaint;


    private int width;


    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0x88000000);
        mPaint.setTextSize(60);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 7; i++) {
            canvas.drawText(text[i], cellWidth * i + cellWidth * 0.5f, 50, mPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        cellWidth = width / 7;
    }
}
