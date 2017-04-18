package com.lee.x.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.db.DbUtils;

/**
 * Created by android on 2017/4/11.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv;

    private Button btn;
    private Button btn1;
    private Button btn2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getResLayout() {
        return R.layout.my_fragment;
    }

    @Override
    protected void findView() {
        tv = (TextView) rootView.findViewById(R.id.tv);
        btn = (Button) rootView.findViewById(R.id.btn);
        btn1 = (Button) rootView.findViewById(R.id.btn1);
        btn2 = (Button) rootView.findViewById(R.id.btn2);
    }

    @Override
    protected void initData() {

        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DbUtils dbUtils = new DbUtils();
        switch (v.getId()) {

            case R.id.btn:

                dbUtils.openDB();
                dbUtils.createTable();
                break;
            case R.id.btn1:
                dbUtils.openDB();
                dbUtils.insent();
                break;
            case R.id.btn2:
                dbUtils.openDB();
                String str = dbUtils.select();
                tv.setText(str);
                break;
        }

    }
}
