package com.lee.x.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lee.x.R;
import com.lee.x.demo.demo1.Demo1Activity;
import com.lee.x.demo.demo10.Demo10Activity;
import com.lee.x.demo.demo2.Demo2Activity;
import com.lee.x.demo.demo3.Demo3Activity;
import com.lee.x.demo.demo4.Demo4Activity;
import com.lee.x.demo.demo5.Demo5Activity;
import com.lee.x.demo.demo6.Demo6Activity;
import com.lee.x.demo.demo7.Demo7Activity;
import com.lee.x.demo.demo8.Demo8Activity;
import com.lee.x.demo.demo9.Demo9Activity;


public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }

    public void demo1(View view) {
        startActivity(new Intent(this, Demo1Activity.class));
    }

    public void demo2(View view) {
        startActivity(new Intent(this, Demo2Activity.class));
    }

    public void demo3(View view) {
        startActivity(new Intent(this, Demo3Activity.class));
    }

    public void demo4(View view) {
        startActivity(new Intent(this, Demo4Activity.class));
    }

    public void demo5(View view) {
        startActivity(new Intent(this, Demo5Activity.class));
    }

    public void demo6(View view) {
        startActivity(new Intent(this, Demo6Activity.class));
    }

    public void demo7(View view) {
        startActivity(new Intent(this, Demo7Activity.class));
    }

    public void demo8(View view) {
        startActivity(new Intent(this, Demo8Activity.class));
    }

    public void demo9(View view) {
        startActivity(new Intent(this, Demo9Activity.class));
    }

    public void demo10(View view) {
        startActivity(new Intent(this, Demo10Activity.class));
    }

}
