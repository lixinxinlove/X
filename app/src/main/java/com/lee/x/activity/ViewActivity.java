package com.lee.x.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.lee.x.R;

public class ViewActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mImageView = (ImageView) findViewById(R.id.iv);
    }


    public void onClick1(View view) {
        mImageView.setVisibility(View.VISIBLE);

        int centerX = mImageView.getWidth() / 2;//获取组件的宽的一半
        int centerY = mImageView.getHeight() / 2;//获取组件的高的一半
        Animator animator = ViewAnimationUtils.createCircularReveal(mImageView, centerX, centerY, mImageView.getWidth(), 0);
        animator.setDuration(3000);
        //animator.setStartDelay(1000);//这里可以设置动画的延迟时间；
        //animator.setInterpolator(new LinearOutSlowInInterpolator());//out到in
        // animator.setInterpolator(new AccelerateInterpolator());//out到in
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mImageView.setVisibility(View.INVISIBLE);
            }
        });

    }


    public void onClick2(View view) {
        Animator animator1 = ViewAnimationUtils.createCircularReveal(
                mImageView, 0, 0, 0, (float) Math.hypot(mImageView.getWidth(), mImageView.getHeight()) + 20);//宽的平方加上高的平方的根号
        animator1.setInterpolator(new LinearInterpolator());//插补器有没有不影响
        animator1.setDuration(2000);
        animator1.start();
        mImageView.setVisibility(View.VISIBLE);
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //  mImageView.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void onClick3(View view) {
        int cenX = mImageView.getWidth() / 2;
        int cenY = mImageView.getHeight() / 2;
        Animator an = ViewAnimationUtils.createCircularReveal(mImageView, cenX, cenY, 0,  (float) Math.hypot(mImageView.getWidth(), mImageView.getHeight()) );
        an.setDuration(3000);
        an.start();
        mImageView.setVisibility(View.VISIBLE);
        an.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mImageView.setVisibility(View.VISIBLE);
            }
        });
    }
}
