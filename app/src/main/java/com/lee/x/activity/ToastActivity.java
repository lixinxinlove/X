package com.lee.x.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.lee.x.R;
import com.lee.x.fragment.MyDialogFragment;

public class ToastActivity extends AppCompatActivity {


    MyDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        dialogFragment = new MyDialogFragment();

    }

    public void onClickTopToast(View v) {
        Display display = getWindowManager().getDefaultDisplay();
        // 获取屏幕高度
        int height = display.getHeight();
        Toast toast = Toast.makeText(this, "居中上部位置的Toast", Toast.LENGTH_LONG);
        // 这里给了一个1/4屏幕高度的y轴偏移量
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
        dialogFragment.show(getSupportFragmentManager(), "ll");
        dialogFragment.dismiss();

    }
}
