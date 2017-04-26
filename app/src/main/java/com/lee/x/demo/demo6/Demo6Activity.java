package com.lee.x.demo.demo6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lee.x.R;


public class Demo6Activity extends AppCompatActivity {

    Demo6View dotsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo6);
        dotsTextView = (Demo6View) findViewById(R.id.dots);
        dotsTextView.start();
    }
}
