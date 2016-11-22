package com.example.kangxiaolei.BaoZouRiBao;

import android.app.Application;

/**
 * Created by kangxiaolei on 2016/11/12.
 */
public class MyApplication extends Application{
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
