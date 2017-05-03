package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.view.CalendarView;

import java.util.Date;

public class CalenderActivity extends AppCompatActivity {


    private CalendarView calendarView;

    private TextView textView;


    private LinearLayout rootView;


    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        textView = (TextView) findViewById(R.id.tv);
        calendarView = (CalendarView) findViewById(R.id.cv);


        rootView = (LinearLayout) findViewById(R.id.root_view);


        date = new Date();
        rootView.removeAllViews();
        for (int i = 0; i < 5; i++) {
            CalendarView cv = new CalendarView(this);
           // cv.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            date.setMonth(date.getMonth() + i);
            cv.setmDate(date);
            rootView.addView(cv);
        }


//        calendarView.setmDate(new Date("2017/4/4"));
//
//        calendarView.setICalendrViewCallback(new ICalendrViewCallback() {
//            @Override
//            public void onClickeDay(int date) {
//                Toast.makeText(CalenderActivity.this, "点击的是" + date + "号", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onToday(int date) {
//                textView.setText("今天是" + date + "号");
//            }
//        });

    }
}
