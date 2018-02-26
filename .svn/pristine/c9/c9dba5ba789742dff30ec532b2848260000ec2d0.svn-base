package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.TestAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;
    private View mEmptyView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swipeRefreshColor1, R.color.swipeRefreshColor2,
                R.color.swipeRefreshColor3, R.color.swipeRefreshColor4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEmptyView = getLayoutInflater().inflate(R.layout.layout_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter = new TestAdapter();
        mAdapter.setPreLoadNumber(2);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        List<Values> mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mList.add(new Values());
        }
        mAdapter.addData(mList);
    }
}
