package com.example.kangxiaolei.BaoZouRiBao;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by kangxiaolei on 2016/11/12.
 */

public class LoadView extends Dialog {
    public LoadView(Context context) {
        super(context);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setCancelable(false);
        View contentView = View.inflate(context,R.layout.loadview, null);
        setContentView(contentView,new ViewGroup.LayoutParams(dp2px(context,150),dp2px(context,150)));
        ImageView iv = (ImageView) contentView.findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.laod);
        AnimationDrawable ani = (AnimationDrawable) iv.getBackground();
        ani.start();
        getWindow().getAttributes().dimAmount=0.3f;
    }
    private int dp2px(Context ctx,int dp){
        return (int) (ctx.getResources().getDisplayMetrics().density*dp);
    }
}
