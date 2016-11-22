package com.example.kangxiaolei.BaoZouRiBao;

/**
 * Created by kangxiaolei on 2016/11/12.
 */
public class SPUtils {
    public  static  void save(String key,String value){
        MyApplication.app.getSharedPreferences("store",0).edit().putString(key,value).commit();
    }
    public static String get(String key){
        return MyApplication.app.getSharedPreferences("store",0).getString(key,"");
    }
}
