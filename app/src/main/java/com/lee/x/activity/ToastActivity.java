package com.lee.x.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.lee.x.R;
import com.lee.x._interface.ICalendrViewCallback;
import com.lee.x.fragment.MyDialogFragment;
import com.lee.x.view.CalendarView;

public class ToastActivity extends AppCompatActivity {


    MyDialogFragment dialogFragment;


    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);


        calendarView = (CalendarView) findViewById(R.id.cv);

        calendarView.setICalendrViewCallback(new ICalendrViewCallback() {
            @Override
            public void onClickeDay(int date) {

            }

            @Override
            public void onToday(int date) {

            }
        });

        dialogFragment = new MyDialogFragment();

    }

    public void onClickTopToast(View v) {
        Display display = getWindowManager().getDefaultDisplay();
        // 获取屏幕高度


        Point point = new Point();

        display.getSize(point);

        Log.e("Lee", point.x + "---" + point.y);


        int height = point.y;

        Toast toast = Toast.makeText(this, "居中上部位置的Toast", Toast.LENGTH_LONG);
        // 这里给了一个1/4屏幕高度的y轴偏移量
        toast.setGravity(Gravity.TOP, 0, height / 2);
        toast.show();
        dialogFragment.setCancelable(false);
        dialogFragment.show(getSupportFragmentManager(), "ll");

        //dialogFragment.dismiss();

    }
}
