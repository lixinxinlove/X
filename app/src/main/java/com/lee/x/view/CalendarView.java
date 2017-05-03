package com.lee.x.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lee.x._interface.ICalendrViewCallback;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lixinxin on 2017/4/27.
 * 时间日历
 */
public class CalendarView extends View implements View.OnTouchListener {


    private Date mDate;

    private int selectedYear;

    private int selectedMonth;

    private int todayDate;

    private int selectedDay = -1;
    //单个日期的 宽
    private float cellWidth;
    //单个日期的 高
    private float cellHeight;
    //绘制日期
    private Paint mDayPaint;
    //绘制点击的日期
    private Paint mCirclePaint;
    //绘制今天背景
    private Paint mTodayPaint;
    //小文本
    private Paint mSmallTextPaint;
    //矩形
    private Paint mRectanglePaint;

    //TODO
    private Paint mRedPaint;
    //字体大小
    private float textSize;
    //屏幕的宽
    private int width;
    //高
    private int height;
    //当前月一共有多少天
    private int countDay;
    //当前月一号是周几
    private int dayOfWeek;
    //共需要多少行
    private int row;
    //放每一天
    private int[] calenderDays = new int[42];

    private Calendar calendar;
    private Calendar mCalendar;
    private Calendar tempCalendar;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        setOnTouchListener(this);

        mDayPaint = new Paint();
        mDayPaint.setAntiAlias(true);
        mDayPaint.setColor(0xff000000);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(0xffff0000);

        mTodayPaint = new Paint();
        mTodayPaint.setAntiAlias(true);
        mTodayPaint.setColor(0xffff0000);
        mTodayPaint.setStrokeWidth(1);
        mTodayPaint.setStyle(Paint.Style.STROKE);


        mSmallTextPaint = new Paint();
        mSmallTextPaint.setAntiAlias(true);
        mSmallTextPaint.setColor(0xff00ff00);
        mSmallTextPaint.setStyle(Paint.Style.STROKE);


        mRedPaint = new Paint();
        mRedPaint.setAntiAlias(true);
        mRedPaint.setColor(0xffff0000);
        mRedPaint.setStrokeWidth(1);
        mRedPaint.setStyle(Paint.Style.STROKE);

        mRectanglePaint = new Paint();
        mRectanglePaint.setAntiAlias(true);
        mRectanglePaint.setColor(0xffFFC2B8);
        mRectanglePaint.setStyle(Paint.Style.FILL);


    }

    private void init() {

        //Date date = new Date("2017/04/04");

        calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(mDate);


        todayDate = calendar.get(Calendar.DATE);

        //1.获取当月一共有多少天
        countDay = getCurrentMonthDays();
        //2.获取一号是周几
        // calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.set(calendar.DAY_OF_MONTH, 1);  //设置为一号
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        // calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        row = getRow();

        Log.e("Date", "本月有多少天--" + countDay);
        Log.e("Date", "1号是周几--" + dayOfWeek);
        Log.e("Date", "一个月的日历的高度--" + row);
        /**
         * 初始化 一个月的数据
         */
        for (int i = 0; i < countDay; i++) {
            calenderDays[dayOfWeek + i] = i + 1;
        }
    }


    /**
     * 设置 calendar
     *
     * @param calendar
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * 设置一个时间
     *
     * @param date
     */
    public void setmDate(Date date) {
        this.mDate = date;
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        cellWidth = width / 7;
        cellHeight = cellWidth;
        // cellHeight = cellWidth * 0.7f;
        textSize = cellWidth * 0.3f;
        mDayPaint.setTextSize(textSize);
        height = (int) (row * cellHeight);

        mSmallTextPaint.setTextSize(cellHeight * 0.2f);

        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < calenderDays.length; i++) {

            if (calenderDays[i] == selectedDay) {
                //drawCircle(canvas, i, mCirclePaint, cellHeight / 2);
                drawRectangle(canvas, i);
            }

            if (todayDate == calenderDays[i]) {
                drawCircle(canvas, i, mTodayPaint, (float) (cellHeight * 0.5));
            }

            drawDayText(canvas, i, mDayPaint, calenderDays[i]);

            // drawSmallText(canvas, i, mSmallTextPaint, calenderDays[i]);

            // drawCoordinate(canvas, i);

        }
    }

    /**
     * 绘制 日期
     *
     * @param canvas
     * @param index
     */
    private void drawDayText(Canvas canvas, int index, Paint paint, int t) {
        if (isIllegalIndex(index)) {
            return;
        }
        int x = getXByIndex(index);
        int y = getYByIndex(index);


        String text = t + "";

        Rect bounds = new Rect();// 矩形
        paint.getTextBounds(text, 0, text.length(), bounds);
        int textWidth = bounds.width();
        int textHeight = bounds.height();

        float startX = cellWidth * x + cellWidth / 2 - textWidth / 2;
        float startY = cellHeight * y + cellHeight / 2 + textHeight / 2;


        tempCalendar.set(calendar.DAY_OF_MONTH, t);  //  获取指定日期是星期几

        if (tempCalendar.get(Calendar.DAY_OF_WEEK) == 1 || tempCalendar.get(Calendar.DAY_OF_WEEK) == 7) {
            paint.setColor(0xff0000ff);
        } else {
            paint.setColor(0xff000000);
        }

        canvas.drawText(text, startX, startY, paint);
    }


    /**
     * 绘制 班 和 休
     *
     * @param canvas
     * @param index
     */
    private void drawSmallText(Canvas canvas, int index, Paint paint, int t) {
        if (isIllegalIndex(index)) {
            return;
        }
        int x = getXByIndex(index);
        int y = getYByIndex(index);
        String text = "班";
        Rect bounds = new Rect();// 矩形
        paint.getTextBounds(text, 0, text.length(), bounds);
        int textWidth = bounds.width();
        int textHeight = bounds.height();

        float startX = cellWidth * x + cellWidth / 4 * 3 - textWidth / 2;
        float startY = cellHeight * y + cellHeight / 4 + textHeight / 2;

        tempCalendar.set(calendar.DAY_OF_MONTH, t);  //  获取指定日期是星期几

        if (tempCalendar.get(Calendar.DAY_OF_WEEK) == 1 || tempCalendar.get(Calendar.DAY_OF_WEEK) == 7) {
            paint.setColor(0xffff0000);
            text = "休";
        } else {
            paint.setColor(0xffca71fa);
            text = "班";
        }

        canvas.drawText(text, startX, startY, paint);
    }


    private void drawCircle(Canvas canvas, int index, Paint paint, float radius) {
        if (isIllegalIndex(index)) {
            return;
        }
        int x = getXByIndex(index);
        int y = getYByIndex(index);

        float centreX = cellWidth * x + cellWidth / 2;
        float centreY = cellHeight * y + cellHeight / 2;
        canvas.drawCircle(centreX, centreY, radius, paint);
    }


    /**
     * 绘制矩形背景
     *
     * @param canvas
     * @param index
     */
    private void drawRectangle(Canvas canvas, int index) {
        if (isIllegalIndex(index)) {
            return;
        }
        int x = getXByIndex(index);
        int y = getYByIndex(index);

        float startX = cellWidth * x;
        float startY = cellHeight * y;
        canvas.drawRect(startX, startY, startX + cellWidth, startY + cellHeight, mRectanglePaint);
    }


    /**
     * 绘制 坐标
     *
     * @param canvas
     * @param index
     */
    private void drawCoordinate(Canvas canvas, int index) {
        if (isIllegalIndex(index)) {
            return;
        }
        int x = getXByIndex(index);
        int y = getYByIndex(index);

        float startX = cellWidth * x;
        float startY = cellHeight * y;
        canvas.drawLine(startX + cellWidth / 2, startY, startX + cellWidth / 2, startY + cellHeight, mRedPaint);
        canvas.drawLine(startX, startY + cellHeight / 2, startX + cellWidth, startY + cellHeight / 2, mRedPaint);
    }


    /**
     * 判断无效的的数据
     *
     * @param index
     * @return
     */
    private boolean isIllegalIndex(int index) {
        if (index < dayOfWeek || index > dayOfWeek + countDay - 1) {
            return true;
        }
        return false;
    }


    /**
     * 计算控件的高度  一共有几行
     */
    private int getRow() {
        int h = (countDay + dayOfWeek) % 7;
        int c = (countDay + dayOfWeek) / 7;
        if (h > 0) {
            c++;
        }
        return c;
    }


    /**
     * 获取当月天数
     */
    private int getCurrentMonthDays() {
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 获取x 坐标
     *
     * @param i
     * @return
     */
    private int getXByIndex(int i) {
        return i % 7;
    }

    /**
     * 获取Y 坐标
     *
     * @param i
     * @return
     */
    private int getYByIndex(int i) {
        return i / 7;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int i = getIndexByCoordinate(x, y);
                if (i < dayOfWeek || i >= dayOfWeek + countDay) {
                    Log.e("Date", "点击的是" + i);
                } else {
                    selectedDay = calenderDays[i];
                    invalidate();
                    if (callback != null) {
                        callback.onClickeDay(selectedDay);
                    }
                }
                Log.e("Date", "点击的是" + i);

                break;

            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 获取点击对应的日期
     *
     * @param x
     * @param y
     * @return
     */
    private int getIndexByCoordinate(float x, float y) {
        int m = (int) (x / cellWidth);
        int n = (int) (y / cellHeight);
        return n * 7 + m;
    }


    //=======================接口========================================


    private ICalendrViewCallback callback;

    public void setICalendrViewCallback(ICalendrViewCallback callback) {
        this.callback = callback;

        if (callback != null) {
            callback.onToday(selectedDay);
        }

    }
}
