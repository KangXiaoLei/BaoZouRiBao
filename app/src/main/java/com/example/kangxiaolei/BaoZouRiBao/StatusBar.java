package com.example.kangxiaolei.BaoZouRiBao;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by kangxiaolei on 2016/11/12.
 */
public class StatusBar {
    public static void setColor(Activity a,int c){
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT){
            a.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup rootView = (ViewGroup) a.getWindow().getDecorView();
            int stanusBarId = a.getResources().getIdentifier("status_bar_height","dimen","android");
            float dimension=a.getResources().getDimension(stanusBarId);
            View statusView = new View(a);
            statusView.setLayoutParams(new FrameLayout.LayoutParams(a.getWindowManager().getDefaultDisplay().getWidth(), (int) dimension));
            statusView.setBackgroundColor(c);
            rootView.addView(statusView,0);


        }

    }
}
