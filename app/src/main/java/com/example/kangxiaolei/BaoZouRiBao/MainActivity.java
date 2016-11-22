package com.example.kangxiaolei.BaoZouRiBao;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.FrameLayout frameGroups;
    private android.support.design.widget.NavigationView navigationView;
    private FrameLayout fragmentGroups;
    private DrawerLayout activitymain1;
    private FragmentManager fm;
    private ShouYeFragment shouYeFragment;
    private MeiShi yongHuTouGao;
    private NewsFragment newsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            newsFragment = new NewsFragment();
            shouYeFragment =new ShouYeFragment();
            yongHuTouGao=new MeiShi();
            ShiPin shipin=new ShiPin();
            fm.beginTransaction().replace(R.id.fragmentGroups,newsFragment,NewsFragment.class.getSimpleName()).commit();
        }
        initView();
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT){
            StatusBarUtil.setColorForDrawerLayout(this,activitymain1,getResources().getColor(R.color.colorAccent));
        }

    }

    private void initView() {
        this.activitymain1 = (DrawerLayout) findViewById(R.id.activity_main1);
        this.navigationView = (NavigationView) findViewById(R.id.navigationView);
        this.fragmentGroups = (FrameLayout) findViewById(R.id.fragmentGroups);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activitymain1, toolbar, R.string.open, R.string.colse);
        activitymain1.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.inflateMenu(R.menu.tool);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("暴走日报");
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        activitymain1.closeDrawer(Gravity.LEFT);
        toolbar.setTitle(item.getTitle());
        FragmentTransaction fragmentTransaction= fm.beginTransaction();
        switch (item.getItemId()) {
            case R.id.shouye:
                fm.beginTransaction().replace(R.id.fragmentGroups,shouYeFragment,NewsFragment.class.getSimpleName()).commit();
                break;
            case R.id.paihangbang:

                break;
            case R.id.pindao:
                break;
            case R.id.sousuo:
                break;
            case R.id.shezhi:
                break;
            case R.id.lixianxiazai:
                break;
            case R.id.yejianmoshi:
                break;
            case R.id.biji:
                break;
            case R.id.xiaoxi:
                break;

        }
        fragmentTransaction.commit();
        return true;
    }
    public void denglu(View v){
        Snackbar.make(v,"登录成功",Snackbar.LENGTH_SHORT).show();
    }
    public void shoucang(View v) {
        Snackbar.make(v, "收藏", Snackbar.LENGTH_SHORT).show();
    }

    public void pinglun(View v) {
        Snackbar.make(v, "评论", Snackbar.LENGTH_SHORT).show();
    }

    public void yuedu(View v) {
        Snackbar.make(v, "阅读", Snackbar.LENGTH_SHORT).show();
    }


}
