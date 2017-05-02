package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.x.R;
import com.lee.x._interface.ICalendrViewCallback;
import com.lee.x.view.CalendarView;

public class CalenderActivity extends AppCompatActivity {


    private CalendarView calendarView;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        textView = (TextView) findViewById(R.id.tv);
        calendarView = (CalendarView) findViewById(R.id.cv);

        calendarView.setICalendrViewCallback(new ICalendrViewCallback() {
            @Override
            public void onClickeDay(int date) {
                Toast.makeText(CalenderActivity.this, "点击的是" + date + "号", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onToday(int date) {
                textView.setText("今天是" + date + "号");
            }
        });

    }
}
