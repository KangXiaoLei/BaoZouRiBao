package com.example.kangxiaolei.BaoZouRiBao;

/**
 * Created by kangxiaolei on 2016/11/11.
 */

public class NewsItemBean {
    public String imagePath;
    public String title;
    public String desc;
    public String url;

    public NewsItemBean(String imagePath, String title, String desc, String url) {
        this.imagePath = imagePath;
        this.title = title;
        this.desc=desc;
        this.url = url;
    }
}
