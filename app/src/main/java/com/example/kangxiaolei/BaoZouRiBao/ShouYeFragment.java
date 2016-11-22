package com.example.kangxiaolei.BaoZouRiBao;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kangxiaolei on 2016/11/11.
 */

public class ShouYeFragment extends BaseNewsFragment {
    @Override
    public void initData() {
        super.initData();
        String s = SPUtils.get(getClass().getName());
        if(TextUtils.isEmpty(s)) {
            loadData("http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html", 0);
        }else{
            loadSuccess(s,0);
        }
    }


    int page=1;
    @Override
    public void loadSuccess(String con, int type) {
        if(type==0){
            SPUtils.save(this.getClass().getName(),con);
            relash(con);
        }else {
            load(con);
        }
    }

    private void load(String con) {
        page++;
        isLoad=false;
        addData(con);
    }

    private void relash(String con) {
        // 首次进来或者下拉刷新
        swip.setRefreshing(false);
        list.clear();
        addData(con);
    }

    private void addData(String con) {
        try {
            JSONArray arr = new JSONObject(con).getJSONArray("T1348647909107");
            if(arr.length()>2){
                setNormalState();
                for(int i=2;i<arr.length();i++){
                    try {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        String imgsrc = jsonObject.getString("imgsrc");
                        String title = jsonObject.getString("title");
                        String digest = jsonObject.getString("digest");
                        String postid = jsonObject.getString("postid");
                        list.add(new NewsItemBean(imgsrc,title,digest,postid));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else{
                setNoDataState();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        loadData("http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html",0);
    }

    @Override
    public void loadMore() {
        Log.e("tag","loadMore");
        loadData("http://c.m.163.com/nc/article/headline/T1348647909107/"+((page+1)*20)+"-20.html",1);
    }

    @Override
    public void tryAgain() {
        loadData("http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html",0);
    }


    @Override
    public void loadMoreAgain() {
        loadData("http://c.m.163.com/nc/article/headline/T1348647909107/"+((page+1)*20)+"-20.html",1);
    }

}
