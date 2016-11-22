package com.example.kangxiaolei.BaoZouRiBao;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kangxiaolei on 2016/11/11.
 */

public class HttpUtils {
    private HttpUtils(){

    }
    private  static Handler handlder;
    ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
    private static HttpUtils h;

    public static HttpUtils getInstance(){
        if(h==null){
            synchronized (HttpUtils.class){
                if(h==null){
                    h=new HttpUtils();
                    handlder=new Handler(Looper.myLooper().getMainLooper());
                }
            }
        }
        return h;
    }
    public  void loadGetData(final String path, final OnLoadDataListener listener){
        if(listener==null)throw new RuntimeException("OnloadDataListener 不能为空");
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                   HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(60*1000);
                    con.setReadTimeout(60*1000);
                    int responseCode=con.getResponseCode();
                    if(responseCode==200){
                        BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                        final StringBuilder sb=new StringBuilder();
                        String line=null;
                        while((line=br.readLine())!=null){
                            sb.append(line);
                        }
                        br.close();
                        con.disconnect();
                        handlder.post(new Runnable() {
                            @Override
                            public void run() {
                                //成功
                                listener.success(sb.toString());
                            }
                        });

                    }else{
                        handlder.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.faild("请求失败");
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    handlder.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.faild("请求失败");
                        }
                    });
                }
            }
        });
    }


    public interface OnLoadDataListener {
        void success(String con);

        void faild(String msg);
    }
}
