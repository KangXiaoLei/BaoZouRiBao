package com.example.kangxiaolei.BaoZouRiBao;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kangxiaolei on 2016/11/13.
 */
public class ShiPin extends BaseNewsFragment {

    private ImageView iv;
    private TextView titleTv;
    private WebView web;

    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View rootiew = inflater.inflate(R.layout.fragment_tougao, container, false);
        this.web = (WebView) rootiew.findViewById(R.id.web);
        this.titleTv = (TextView) rootiew.findViewById(R.id.titleTv);
        this.iv = (ImageView) rootiew.findViewById(R.id.iv);
        return rootiew;
    }

    @Override
    public void initData() {
        super.initData();
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.loadUrl("http://dailyapi.ibaozou.com/sections/128");
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public void loadSuccess(String con, int type) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void tryAgain() {

    }

    @Override
    public void loadMoreAgain() {

    }
}
