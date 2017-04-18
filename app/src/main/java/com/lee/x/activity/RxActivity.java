package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.rxbus.Action;
import com.lee.x.rxbus.RxBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textView;
    private Button brn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        textView = (TextView) findViewById(R.id.tv);
        brn = (Button) findViewById(R.id.btn_rx);
        brn.setOnClickListener(this);

        RxBus.getDefault().toObservable(Action.class).subscribeWith(new Observer<Action>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Action value) {
                textView.setText(value.data.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_rx:
                String data = "100";
                Action action = new Action(1, data);
                RxBus.getDefault().post(action);
                break;
        }
    }
}
