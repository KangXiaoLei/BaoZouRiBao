package com.example.kangxiaolei.BaoZouRiBao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kangxiaolei on 2016/11/11.
 */

public abstract class BaseFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=initView(inflater,container);
            initData();
            initAdapter();
        }

        return rootView;
    }

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    public void initData() {

    }

    public void initAdapter() {

    }


}
