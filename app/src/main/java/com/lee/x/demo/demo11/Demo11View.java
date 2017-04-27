package com.lee.x.demo.demo11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.lee.x.R;

/**
 * Created by android on 2017/4/27.
 */
public class Demo11View extends View {

    private Paint mPaint;  //画笔
    private Bitmap bitmap;  //原图

    private int width;
    private int height;  //宽高

    private int wHeight = 0;  //水波纹高度


    private Path path;// 画贝塞尔曲线需要用到
    private Canvas mCanvas;// 在该画布上绘制目标图片
    private Bitmap bg;// 目标图片
    private float controlX;
    private float controlY;

    private PorterDuffXfermode porterDuffXfermode;

    private float waveY;// 上升的高度
    private boolean isIncrease;// 用于控制控制点水平移动
    private boolean isReflesh = true;// 是否刷新并产生填充效果，默认为true

    public Demo11View(Context context) {
        super(context);
        init();
    }


    public Demo11View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Demo11View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ffc93940"));

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setXfermode(porterDuffXfermode);

        width = bitmap.getWidth();
        height = bitmap.getHeight();


        controlX = width;
        controlY = height;


        path = new Path();
        // 初始化画布
        mCanvas = new Canvas();
        // 创建bitmap
        bg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 将新建的bitmap注入画布
        mCanvas.setBitmap(bg);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bg, getPaddingLeft(), getPaddingTop(), null);
        // canvas.drawLine(100, 100, 200, 200, mPaint);
        drawTargetBitmap();
        postInvalidateDelayed(100);
    }

    private void drawTargetBitmap() {
        // 重置path
        path.reset();
        bg.eraseColor(Color.parseColor("#00ffffff"));
        // 贝塞尔曲线的生成
        path.moveTo(0, wHeight);
        // 两个控制点通过controlX，controlY生成
        path.cubicTo(controlX / 3, controlY / 3 + wHeight, controlX / 3 * 2, controlY / 3 + wHeight, width, wHeight);
        // 与下下边界闭合
        path.lineTo(width, 0);
        path.lineTo(0, 0);
        // 进行闭合
        path.close();
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);// 画慕课网logo
        //  mPaint.setXfermode(porterDuffXfermode);// 设置Xfermode
        mCanvas.drawPath(path, mPaint);// 画三阶贝塞尔曲线
        mPaint.setXfermode(null);// 重置Xfermode


        wHeight += 1;
        if (wHeight >= height) {
            wHeight = 1;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // width = getMeasuredWidth();
        // height = getMeasuredHeight();
        setMeasuredDimension(width, height);
    }
}
