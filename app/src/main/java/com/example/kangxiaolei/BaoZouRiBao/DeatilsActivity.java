package com.example.kangxiaolei.BaoZouRiBao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kangxiaolei on 2016/11/12.
 */

public class DeatilsActivity extends Activity {

    private android.support.design.widget.CollapsingToolbarLayout collpaseBar;
    private android.support.design.widget.CoordinatorLayout activitydeatils;
    private android.widget.ImageView iv;
    private android.support.v7.widget.Toolbar toolbar;
    private TextView tv;
    private android.support.v4.widget.NestedScrollView nest;
    private String image;
    private String title;
    private LoadView loadView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);
        loadView = new LoadView(this);
        loadView.show();
        this.activitydeatils = (CoordinatorLayout) findViewById(R.id.activity_deatils);
        this.nest = (NestedScrollView) findViewById(R.id.nest);
        this.tv = (TextView) findViewById(R.id.tv);
        this.collpaseBar = (CollapsingToolbarLayout) findViewById(R.id.collpaseBar);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.iv = (ImageView) findViewById(R.id.iv);
        collpaseBar.setExpandedTitleColor(Color.WHITE);
        collpaseBar.setCollapsedTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String postId = intent.getStringExtra("postId");
        image = intent.getStringExtra("image");
        title = intent.getStringExtra("title");
        loadData(url, postId);


    }

    private void loadData(String url, final String postId) {
        HttpUtils.getInstance().loadGetData(url, new HttpUtils.OnLoadDataListener() {
            @Override
            public void success(String con) {
                try {
                    JSONObject jsonObject = new JSONObject(con).getJSONObject(postId);
                    String body = jsonObject.getString("body");
                    Glide.with(DeatilsActivity.this).load(image).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            iv.setImageBitmap(resource);
                            Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    int darkVibrantColor = palette.getVibrantColor(Color.parseColor("#3F51B5"));
                                    int lightMutedColor = palette.getDarkVibrantColor(getResources().getColor(R.color.colorPrimaryDark));
                                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

                                        StatusBar.setColor(DeatilsActivity.this, lightMutedColor);
                                    }
                                    loadView.dismiss();
                                    collpaseBar.setContentScrimColor(lightMutedColor);
                                }
                            });
                        }
                    });

                    collpaseBar.setTitle(title);
                    tv.setText(Html.fromHtml(body));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void faild(String msg) {
                Toast.makeText(DeatilsActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
