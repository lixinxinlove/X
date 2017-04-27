package com.lee.x.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lee.x.R;

import java.util.Calendar;

/**
 * Created by android on 2017/4/27.
 */
public class MyDialogFragment extends DialogFragment implements View.OnClickListener {


    ImageView imageView;


    Calendar calendar;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendar = Calendar.getInstance();
        calendar.get(Calendar.DATE);

        Log.e("lee", "DATE" + calendar.get(Calendar.DATE));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = View.inflate(getActivity(), R.layout.view_dialog, null);

        imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                this.dismiss();
                break;
            default:
                break;
        }
    }
}
