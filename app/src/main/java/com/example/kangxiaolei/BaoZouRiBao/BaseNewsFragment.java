package com.example.kangxiaolei.BaoZouRiBao;


import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/12.
 */

public abstract class BaseNewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    public android.support.v7.widget.RecyclerView recycler;
    public android.support.v4.widget.SwipeRefreshLayout swip;
    public List<NewsItemBean> list;
    public MyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    public boolean isLoad;
    private View emptyView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View rootiew = inflater.inflate(R.layout.fragment_basenews, container, false);
        this.swip = (SwipeRefreshLayout) rootiew.findViewById(R.id.swip);

        swip.setColorSchemeColors(Color.parseColor("#FF4081"));
        swip.setRefreshing(true);
        this.recycler = (RecyclerView) rootiew.findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (linearLayoutManager.findLastVisibleItemPosition() == list.size()) {
                        if (!isLoad) {
                            isLoad = true;
                            loadMore();
                        }
                    }
                }
            }
        });
        adapter = new MyAdapter();
        swip.setOnRefreshListener(this);
        list = new ArrayList<>();
        emptyView = rootiew.findViewById(R.id.emptyView);
        emptyView.setOnClickListener(this);
        recycler.setAdapter(adapter);
        return rootiew;
    }


    @Override
    public void initAdapter() {
        super.initAdapter();


    }

    public void loadData(String url, final int type) {
        HttpUtils.getInstance().loadGetData(url, new HttpUtils.OnLoadDataListener() {
            @Override
            public void success(String con) {
                loadSuccess(con, type);

            }

            @Override
            public void faild(String msg) {

                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                swip.setRefreshing(false);
                if(type==0){

                    if(list.size()==0)
                        emptyView.setVisibility(View.VISIBLE);
                }else{
                    normalFooter.setVisibility(View.GONE);
                    noDataFooter.setVisibility(View.GONE);
                    errorFooter.setVisibility(View.VISIBLE);
                }
            }
        });
    }

// con
// type 0表示加载的是第一页数据，1就是上啦加载
    public abstract void loadSuccess(String con, int type);

    // 下拉刷新 加载数据
    @Override
    public abstract void onRefresh();

    public abstract void loadMore();
    public abstract  void tryAgain();

    @Override
    public void onClick(View v) {
        swip.setRefreshing(true);
        emptyView.setVisibility(View.GONE);
        tryAgain();
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                return new ItemViewHolder(View.inflate(getActivity(), R.layout.item_basenews, null));
            }
            return new FooterViewHolder(View.inflate(getActivity(), R.layout.footer, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (position < list.size()) {
                NewsItemBean newsItemBean = list.get(position);
                ((ItemViewHolder) (holder)).descTv.setText(newsItemBean.desc);
                ((ItemViewHolder) (holder)).titleTv.setText(newsItemBean.title);
                ImageView iv = ((ItemViewHolder) (holder)).iv;
                Glide.with(getActivity()).load(newsItemBean.imagePath).placeholder(R.drawable.jianbian).into(iv);
            }
        }

        @Override
        public int getItemCount() {
            if(list.size()==0)return 0;
            return list.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == list.size()) {
                return 1;
            }
            return 0;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }

    private class ItemViewHolder extends MyViewHolder {
        ImageView iv;
        TextView titleTv;
        TextView descTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            descTv = (TextView) itemView.findViewById(R.id.descTv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recycler.getChildAdapterPosition(v)>=0)
                        onItemClick(recycler,v,recycler.getChildAdapterPosition(v));
                }
            });
        }
    }
    public  void onItemClick(RecyclerView recycler,View itemView,int position){
        Intent intent=new Intent(getActivity(),DeatilsActivity.class);
        intent.putExtra("url","http://c.m.163.com/nc/article/"+list.get(position).url+"/full.html");
        intent.putExtra("postId",list.get(position).url);
        intent.putExtra("image",list.get(position).imagePath);
        intent.putExtra("title",list.get(position).title);
        startActivity(intent);
    };

    private class FooterViewHolder extends MyViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
            RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(getActivity().getWindowManager().getDefaultDisplay().getWidth(),RecyclerView.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(params);
            noDataFooter=itemView.findViewById(R.id.noDataFooter);
            errorFooter=itemView.findViewById(R.id.errorFooter);
            normalFooter=itemView.findViewById(R.id.normalFooter);
            errorFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    normalFooter.setVisibility(View.VISIBLE);
                    errorFooter.setVisibility(View.GONE);
                    noDataFooter.setVisibility(View.GONE);
                    loadMore();
                }
            });
        }
    }

    public abstract void loadMoreAgain();

            View normalFooter;
            View errorFooter;
            View noDataFooter;

    public void setNoDataState() {
        if (normalFooter != null) {
            noDataFooter.setVisibility(View.VISIBLE);
            errorFooter.setVisibility(View.GONE);
            normalFooter.setVisibility(View.GONE);
        }
    }
    public void setNormalState(){
        if(normalFooter!=null) {
            noDataFooter.setVisibility(View.GONE);
            errorFooter.setVisibility(View.GONE);
            normalFooter.setVisibility(View.VISIBLE);
        }
    }
}