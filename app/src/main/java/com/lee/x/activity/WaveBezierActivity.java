package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lee.x.R;
import com.lee.x.view.WaveBezierView;

public class WaveBezierActivity extends AppCompatActivity {

    WaveBezierView waveBezierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_bezier);
        waveBezierView = (WaveBezierView) findViewById(R.id.wbv);
        waveBezierView.startAnim();
    }
}
