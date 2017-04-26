package com.lee.x.view;

/**
 * Created by android on 2017/4/24.
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


public class WaveBezierView extends View implements View.OnClickListener {


    private Path mPath;
    private Path mPath1;
    private Path mPath2;

    private Paint mPaintBezier;
    private Paint mPaintBezier1;
    private Paint mPaintBezier2;

    private int mWaveLength;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mCenterY;
    private int mWaveCount;


    private int mCenterH = 80;    //控制点高度


    private ValueAnimator mValueAnimator;
    private ValueAnimator mValueAnimator1;
    private ValueAnimator mValueAnimator2;
    private int mOffset;
    private int mOffset1;
    private int mOffset2;

    public WaveBezierView(Context context) {
        super(context);
    }

    public WaveBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setColor(0xffff0000);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);


        mPaintBezier1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier1.setColor(0xffff00ff);
        mPaintBezier1.setStrokeWidth(8);
        mPaintBezier1.setStyle(Paint.Style.FILL_AND_STROKE);


        mPaintBezier2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier2.setColor(0xff00ffff);
        mPaintBezier2.setStrokeWidth(8);
        mPaintBezier2.setStyle(Paint.Style.FILL_AND_STROKE);


        mWaveLength = 600;
    }

    public WaveBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath = new Path();
        mPath1 = new Path();
        mPath2 = new Path();

        setOnClickListener(this);

        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h / 5 * 4;

        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath1.reset();
        mPath2.reset();

        mPath.moveTo(-mWaveLength + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset, mCenterY + mCenterH, -mWaveLength / 2 + i * mWaveLength + mOffset, mCenterY);
            mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset, mCenterY - mCenterH, i * mWaveLength + mOffset, mCenterY);
        }
       // mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaintBezier);


        mPath1.moveTo(-mWaveLength + mOffset1, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath1.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset1, mCenterY + 70, -mWaveLength / 2 + i * mWaveLength + mOffset1, mCenterY);
            mPath1.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset1, mCenterY - 70, i * mWaveLength + mOffset1, mCenterY);
        }
        mPath1.lineTo(mScreenWidth, mScreenHeight);
        mPath1.lineTo(0, mScreenHeight);
        mPath1.close();
       // canvas.drawPath(mPath1, mPaintBezier1);


        mPath2.moveTo(-mWaveLength + mOffset2, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath2.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset2, mCenterY + 75, -mWaveLength / 2 + i * mWaveLength + mOffset2, mCenterY);
            mPath2.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset2, mCenterY - 75, i * mWaveLength + mOffset2, mCenterY);
        }
        mPath2.lineTo(mScreenWidth, mScreenHeight);
        mPath2.lineTo(0, mScreenHeight);
        mPath2.close();
       // canvas.drawPath(mPath2, mPaintBezier2);

    }


    public void startAnim() {
        mValueAnimator = ValueAnimator.ofInt(mWaveLength,0);
        mValueAnimator.setDuration(3000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset = (int) valueAnimator.getAnimatedValue();
            }
        });
        mValueAnimator.start();

        mValueAnimator1 = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator1.setDuration(3500);
        mValueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator1.setInterpolator(new LinearInterpolator());
        mValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset1 = (int) valueAnimator.getAnimatedValue();
            }
        });
        mValueAnimator1.start();

        mValueAnimator2 = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator2.setDuration(2500);
        mValueAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator2.setInterpolator(new LinearInterpolator());
        mValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset2 = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator2.start();


    }


    public void stopAnim() {
        mValueAnimator.cancel();
        mValueAnimator1.cancel();
        mValueAnimator2.cancel();
    }


    @Override
    public void onClick(View v) {
        mCenterY -= 10;
    }
}
