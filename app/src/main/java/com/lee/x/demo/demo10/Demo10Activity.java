package com.lee.x.demo.demo10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.x.R;


public class Demo10Activity extends AppCompatActivity {

    Demo10View mSwitchView;
    Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo10);
        mSwitchView = (Demo10View) findViewById(R.id.view_search);
        btnSwitch = (Button) findViewById(R.id.btn_search);
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitchView.start();
            }
        });
    }
}
