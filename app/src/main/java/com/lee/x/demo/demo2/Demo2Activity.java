package com.lee.x.demo.demo2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.x.R;


public class Demo2Activity extends AppCompatActivity {

    private Demo2View loadingView;
    private int colors[] = new int[]{
            Color.parseColor("#ff0000"), Color.parseColor("#00ff00"),
            Color.parseColor("#0000ff"), Color.parseColor("#ffff00")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);
        Button btn_changeColor = (Button) findViewById(R.id.btn_changeColor);
        loadingView = (Demo2View) findViewById(R.id.loadingView);
        btn_changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) (Math.random() * 4);
                loadingView.setColor(colors[index]);
            }
        });
    }
}
