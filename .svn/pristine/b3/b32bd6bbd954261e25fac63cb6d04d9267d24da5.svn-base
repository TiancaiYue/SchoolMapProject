package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.MyPlanningAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.keyuan.schoolmap.tool.RxTabLayoutTool;
import com.keyuan.schoolmap.widget.RxEmptyLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的活动
 */
public class MyPlanningActivity extends BaseActivity {
    private TabLayout tabLayout;
    private MyPlanningAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RxEmptyLayout mEmptyLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_activities;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("未开始活动"));
        tabLayout.addTab(tabLayout.newTab().setText("历时活动"));
        RxTabLayoutTool.setIndicator(this, tabLayout, 40, 40);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swipeRefreshColor1, R.color.swipeRefreshColor2,
                R.color.swipeRefreshColor3, R.color.swipeRefreshColor4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyPlanningAdapter();
        mAdapter.setPreLoadNumber(2);
        mRecyclerView.setAdapter(mAdapter);

        mEmptyLayout = (RxEmptyLayout) findViewById(R.id.empty_layout);
        mEmptyLayout.setOnEmptyLayoutClickListener(new RxEmptyLayout.OnEmptyLayoutClickListener() {
            @Override
            public void onEmptyLayoutClick(int status) {
                if(status != RxEmptyLayout.NETWORK_LOADING){
                    getData();
                }
            }
        });
    }

    @Override
    public void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        List<Values> mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mList.add(new Values());
        }
        mAdapter.addData(mList);
    }

    private void getData(){
        String url = "https://www.baidu.com";;
        OkGo.<String>get(url).tag(mContext).execute(new StringCallback() {

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                RxLogTool.e("onStart");
                mEmptyLayout.show(RxEmptyLayout.NETWORK_LOADING);
            }

            @Override
            public void onSuccess(Response<String> response) {
                RxLogTool.e("onSuccess");
                //mEmptyLayout.hide();
                mEmptyLayout.setEmptyDataContent("亲，没有更多数据了");
                mEmptyLayout.show(RxEmptyLayout.EMPTY_DATA);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                RxLogTool.e("onError");
                mEmptyLayout.show(RxEmptyLayout.NETWORK_ERROR);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                RxLogTool.e("onFinish");
            }
        });
    }
}
