package com.lee.x;

import android.app.Application;
import android.content.Context;

/**
 * Created by android on 2017/4/20.
 */
public class App extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
