package com.example.kangxiaolei.BaoZouRiBao;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangxiaolei on 2016/11/11.
 */

public class NewsFragment extends BaseFragment{
    private android.support.design.widget.TabLayout tab;
    private android.support.v4.view.ViewPager pager;
    private List<Fragment> list;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View roo=inflater.inflate(R.layout.fragment_news,container,false);
        this.pager = (ViewPager) roo.findViewById(R.id.pager);
        this.tab = (TabLayout) roo.findViewById(R.id.tab);
        return roo;
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add(new ShouYeFragment());
        list.add(new MeiShi());
        list.add(new ShiPin());
        pager.setOffscreenPageLimit(list.size());
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        pager.setAdapter(new MyAdapter(getFragmentManager()));
       tab.setupWithViewPager(pager);
    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }


        @Override
        public int getCount() {
            return list.size();
        }
        String [] arr =new String[]{"新闻","暴走网","深夜美食"};

        @Override
        public CharSequence getPageTitle(int position) {
            return arr[position];
        }
    }
}
