package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.x.R;

public class SocketActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn;
    private Button btnSend;


    private SocketRunnable socketRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        socketRunnable = new SocketRunnable();


        btn = (Button) findViewById(R.id.btn);
        btnSend = (Button) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(socketRunnable);
                thread.start();

            }
        });
    }


    public void connection() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                socketRunnable.w();
                break;
        }
    }
}
