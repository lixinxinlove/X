package com.lee.x.demo.demo9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lee.x.R;


public class Demo9Activity extends AppCompatActivity {

    Demo9View mSuperLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo9);
        mSuperLoadingProgress = (Demo9View) findViewById(R.id.pro);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(10);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishFail();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(10);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishSuccess();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
