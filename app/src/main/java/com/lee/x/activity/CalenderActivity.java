package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.view.CalendarView;

import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity {


    private CalendarView calendarView;

    private TextView textView;

    private LinearLayout rootView;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        textView = (TextView) findViewById(R.id.tv);
        calendarView = (CalendarView) findViewById(R.id.cv);

        rootView = (LinearLayout) findViewById(R.id.root_view);
        rootView.removeAllViews();

        for (int i = 0; i < 10; i++) {
            calendar = Calendar.getInstance();
            CalendarView cv = new CalendarView(this);
            cv.setBackgroundColor(0xffffffff);
            calendar.add(Calendar.MONTH, i);
            cv.setCalendar(calendar);
            rootView.addView(cv);
        }
    }
}
